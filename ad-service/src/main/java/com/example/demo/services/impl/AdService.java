package com.example.demo.services.impl;

import com.example.demo.client.AuthClient;
import com.example.demo.client.RentClient;
import com.example.demo.dto.client.Agent;
import com.example.demo.dto.client.Pricelist;
import com.example.demo.dto.client.SimpleUser;
import com.example.demo.dto.request.CreateAdRequest;
import com.example.demo.dto.request.UpdateAdRequest;
import com.example.demo.dto.response.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.services.IAdService;
import com.example.demo.util.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class AdService implements IAdService {

    private final IAdRepository _adRepository;
    private final ICarModelRepository _carModelRepository;
    private final IFuelTypeRepository _fuelTypeRepository;
    private final IGearshiftTypeRepository _gearshiftTypeRepository;
    private final ICarRepository _carRepository;
    private final AuthClient _authClient;
    private final CarService _carService;
    private final IPictureRepository _pictureRepository;
    private final RentClient _rentClient;

    public AdService(IAdRepository adRepository, ICarModelRepository carModelRepository, IFuelTypeRepository fuelTypeRepository, IGearshiftTypeRepository gearshiTypeRepository, ICarRepository carRepository, AuthClient authClient, CarService carService, IPictureRepository pictureRepository, RentClient rentClient){
        _adRepository = adRepository;
        _carModelRepository = carModelRepository;
        _fuelTypeRepository = fuelTypeRepository;
        _gearshiftTypeRepository = gearshiTypeRepository;
        _carRepository = carRepository;
        _authClient = authClient;
        _carService = carService;
        _pictureRepository = pictureRepository;
        _rentClient = rentClient;
    }

    @Override
    public AdResponse createAd(List<MultipartFile> fileList, CreateAdRequest request) throws GeneralException, IOException {
        if(request.isSimpleUser()) {
            SimpleUser simpleUser = _authClient.getSimpleUser(request.getPublisherId());
            if (simpleUser.getNumOfAds() >= 3) {
                throw new GeneralException("You can not create more than 3 advertisements.", HttpStatus.BAD_REQUEST);
            }
            _authClient.increaseNumOfAds(simpleUser.getId());
        }

        Car car = new Car();
        CarModel carModel = _carModelRepository.findOneById(request.getCarModelId());
        car.setCarModel(carModel);
        car.setKmTraveled(request.getKmTraveled());
        car.setFuelType(_fuelTypeRepository.findOneById(request.getFuelTypeId()));
        car.setGearshiftType(_gearshiftTypeRepository.findOneById(request.getGearshiftTypeId()));
        _carRepository.save(car);

        Ad ad = new Ad();
        ad.setName(carModel.getCarBrand().getName() + " " + carModel.getName() + " " + carModel.getCarClass().getName());
        ad.setCar(car);
        ad.setCdw(request.isCdw());
        ad.setCreationDate(request.getCreationDate());
        ad.setLimitedDistance(request.isLimitedDistance());
        ad.setLimitedKm(request.getLimitedKm());
        ad.setSeats(request.getSeats());
        ad.setPublisher(request.getPublisherId());
        ad.setSimpleUser(request.isSimpleUser());
        ad.setPricelistId(request.getPricelistId());
        List<Picture> pictures = new ArrayList<>();
        for (MultipartFile file : fileList) {
            Picture picture = new Picture();
            picture.setAd(ad);
            picture.setName(file.getOriginalFilename());
            picture.setType(file.getContentType());
            picture.setPicByte(compressBytes(file.getBytes()));
            pictures.add(picture);
            _pictureRepository.save(picture);
        }
        ad.setAdPictures(pictures);
        Ad savedAd = _adRepository.save(ad);

        return mapAdToAdResponse(savedAd);
    }

    @Override
    public List<AdResponse> getAllAds() {
        List<Ad> ads = _adRepository.findAll();

        return  ads.stream()
                .map(ad -> mapAdToAdResponse(ad))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateAdById(Long id, UpdateAdRequest request) {
        Ad ad = _adRepository.findOneById(id);
        if(ad != null) {
            ad.setLimitedKm(request.getLimitedKm());
            ad.setSeats(request.getSeats());
            ad.setLimitedDistance(request.isLimitedDistance());
            ad.setCdw(request.isCdw());

            _adRepository.save(ad);
            return true;
        }
        return false;
    }

    @Override
    public List<AdResponse> getAllPublisherAds(Long id, boolean request) {
        List<Ad> ads = _adRepository.findAllByPublisher(id);
        List<Ad> filteredAds = new ArrayList<>();
        for(Ad a: ads) {
            if(a.isSimpleUser() == request){
                filteredAds.add(a);
            }
        }
        return  filteredAds.stream()
                .map(ad -> mapAdToAdResponse(ad))
                .collect(Collectors.toList());
    }

    @Override
    public AdResponse getAdById(Long id) {
        Ad ad = _adRepository.findOneById(id);
        if(ad != null) {
            return mapAdToAdResponse(ad);
        }
        return null;
    }

    @Override
    public boolean deleteAdById(Long id) {
        Ad ad = _adRepository.findOneById(id);
        if(ad != null){
            Car car = _carRepository.findOneById(ad.getCar().getId());
            _carRepository.deleteById(car.getId());
            return true;
        }
        return false;
    }

    @Override
    public PictureResponse getPicture(Long adId) {
        Ad ad = _adRepository.findOneById(adId);
        Picture picture = ad.getAdPictures().iterator().next();
        Picture decompressedPicture = new Picture(picture.getName(), picture.getType(), decompressBytes(picture.getPicByte()), ad);
        return mapToPictureResponse(decompressedPicture);
    }

    private PictureResponse mapToPictureResponse(Picture decompressedPicture) {
        PictureResponse pictureResponse = new PictureResponse();
        pictureResponse.setId(decompressedPicture.getId());
        pictureResponse.setName(decompressedPicture.getName());
        pictureResponse.setType(decompressedPicture.getType());
        pictureResponse.setPicByte(decompressedPicture.getPicByte());
        return pictureResponse;
    }

    @Override
    public List<PictureResponse> getAllPictures(Long adID){
        List<PictureResponse> pictureResponses = new ArrayList<>();
        Ad ad = _adRepository.findOneById(adID);
        List<Picture> pictures = ad.getAdPictures();
        for(Picture picture : pictures){
            Picture decompressedPicture = new Picture(picture.getName(), picture.getType(), decompressBytes(picture.getPicByte()), ad);
            pictureResponses.add(mapToPictureResponse(decompressedPicture));
        }
        return pictureResponses;
    }

    @Override
    public void uploadPicture(MultipartFile file) throws IOException {
        Picture img = new Picture(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        _pictureRepository.save(img);
    }

    @Override
    public PictureResponse getImage(Long id) {
        Picture retrievedImage = _pictureRepository.findOneById(id);
        Picture img = new Picture(retrievedImage.getName(), retrievedImage.getType(),
                decompressBytes(retrievedImage.getPicByte()));
        return mapToPictureResponse(img);
    }

    private List<AdResponse> mapAdsToAdResponses(List<Ad> ads){
        List<AdResponse> adResponses = new ArrayList<>();
        for(Ad ad: ads){
            AdResponse response = mapAdToAdResponse(ad);
            adResponses.add(response);
        }
        return adResponses;
    }

    public AdResponse mapAdToAdResponse(Ad ad) {
        AdResponse adResponse = new AdResponse();
        adResponse.setId(ad.getId());
        adResponse.setCar(_carService.mapCarToResponse(ad.getCar()));
        adResponse.setCreationDate(ad.getCreationDate());
        adResponse.setName(ad.getName());
        adResponse.setLimitedKm(ad.getLimitedKm());
        adResponse.setSeats(ad.getSeats());
        adResponse.setCdw(ad.isCdw());
        adResponse.setLimitedDistance(ad.isLimitedDistance());
        adResponse.setSimpleUser(ad.isSimpleUser());
        Pricelist pricelist = _rentClient.getPricelist(ad.getPricelistId());
        adResponse.setPricelist(pricelist);
        PublisherResponse publisherResponse = new PublisherResponse();
        if(ad.isSimpleUser()){
            SimpleUser simpleUser = _authClient.getSimpleUser(ad.getPublisher());
            publisherResponse.setId(simpleUser.getId());
            publisherResponse.setAddress(simpleUser.getAddress());
            publisherResponse.setFirstName(simpleUser.getFirstName());
            publisherResponse.setLastName(simpleUser.getLastName());
            publisherResponse.setNumOfAds(simpleUser.getNumOfAds());
            publisherResponse.setUsername(simpleUser.getUsername());
            publisherResponse.setSsn(simpleUser.getSsn());
        }else{
            Agent agent = _authClient.getAgent(ad.getPublisher());
            publisherResponse.setAddress(agent.getAddress());
            publisherResponse.setName(agent.getName());
            publisherResponse.setDateFounded(agent.getDateFounded());
            publisherResponse.setId(agent.getId());
            publisherResponse.setBankAccountNumber(agent.getBankAccountNumber());
        }
        adResponse.setPublisher(publisherResponse);
        if(ad.getAdPictures() != null){
            List<PictureResponse> pictures = new ArrayList<>();
            for(Picture pic: ad.getAdPictures()){
                PictureResponse pictureResponse = mapToPictureResponse(pic);
                pictures.add(pictureResponse);
            }
            adResponse.setPictures(pictures);
        }
        return adResponse;
    }

    @Override
    public SearchResponse search(String address, String fromDateString, String toDateString, String fromTimeString, String toTimeString,
                                   Long carBrandId, Long carModelId, Long carClassId, Long fuelTypeId, Long gearshiftTypeId,
                                   int minPrice, int maxPrice, int limitedKm, int kmTraveled, int seats, boolean availableCDW) {

        if(!fromDateString.equals("")){
            LocalDate fromDate = LocalDate.parse(fromDateString);
            System.out.println("From date " + fromDate);
        }
        if(!toDateString.equals("")){
            LocalDate toDate = LocalDate.parse(toDateString);
            System.out.println("To date " + toDate);
        }
        if(!fromTimeString.equals("")){
            LocalTime fromTime = LocalTime.parse(fromTimeString);
            System.out.println("From time " + fromTime);
        }
        if(!toTimeString.equals("")){
            LocalTime toTime = LocalTime.parse(toTimeString);
            System.out.println("To time " + toTime);
        }

        List<Ad> filteredAds = filteredAds(address, carBrandId,carModelId,fuelTypeId,gearshiftTypeId,carClassId, minPrice,maxPrice,
                kmTraveled,limitedKm,availableCDW,seats);
        List<AdResponse> adResponses =  mapAdsToAdResponses(filteredAds);
        return mapToSearchResponse(adResponses);
    }

    private SearchResponse mapToSearchResponse(List<AdResponse> adResponses) {
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setAds(adResponses);
        return searchResponse;
    }

    private List<Ad> filteredAds(String address, Long carBrandId, Long carModelId, Long fuelTypeId,Long gearshiftTypeId,Long carClassId,
                                 int minPrice, int maxPrice,int kmTraveled, int limitedKm, boolean availableCDW, int seats) {
        List<Ad> allAds = _adRepository.findAll();
        return allAds
                .stream()
                .filter(ad -> {
                    if(ad.isSimpleUser()){
                        SimpleUser simpleUser = _authClient.getSimpleUser(ad.getPublisher());
                        return simpleUser.getAddress().contains(address);
                    }else {
                        Agent agent = _authClient.getAgent(ad.getPublisher());
                        return agent.getAddress().contains(address);
                    }
                })
                .filter(ad -> {
                    if(carBrandId != -1) {
                        return ad.getCar().getCarModel().getCarBrand().getId().equals(carBrandId);
                    } else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(carModelId != -1){
                        return ad.getCar().getCarModel().getId().equals(carModelId);
                    }else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(fuelTypeId != -1){
                        return ad.getCar().getFuelType().getId().equals(fuelTypeId);
                    }else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(gearshiftTypeId != -1){
                        return ad.getCar().getGearshiftType().getId().equals(gearshiftTypeId);
                    }else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(carClassId != -1){
                        return ad.getCar().getCarModel().getCarClass().getId().equals(carClassId);
                    }else {
                        return true;
                    }
                })
                .filter(ad -> {
                    if(minPrice != -1){
                        Pricelist pricelist = _rentClient.getPricelist(ad.getPricelistId());
                        return minPrice <= pricelist.getPricePerDay();
                    }else{
                        return true;
                    }
                })
                .filter(ad -> {
                    if(maxPrice != -1){
                        Pricelist pricelist = _rentClient.getPricelist(ad.getPricelistId());
                        return maxPrice >= pricelist.getPricePerDay();
                    }else{
                        return true;
                    }
                })
                .filter(ad -> {
                    if(limitedKm != -1){
                        return ad.getLimitedKm() <= limitedKm;
                    }else{
                       return true;
                    }
                })
                .filter(ad -> {
                    if(kmTraveled != -1){
                        return ad.getCar().getKmTraveled() <= kmTraveled;
                    }else{
                        return true;
                    }
                })
                .filter(ad -> {
                    if(limitedKm != -1){
                        return ad.getLimitedKm() <= limitedKm;
                    }else{
                        return true;
                    }
                })
                .filter(ad -> ad.isCdw() == availableCDW)
                .filter(ad -> {
                    if(seats != -1){
                        return ad.getSeats() == seats;
                    }else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
