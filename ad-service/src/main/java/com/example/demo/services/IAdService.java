package com.example.demo.services;

import com.example.demo.dto.request.CreateAdRequest;
import com.example.demo.dto.request.UpdateAdRequest;
import com.example.demo.dto.response.AdResponse;
import com.example.demo.dto.response.PictureResponse;
import com.example.demo.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface IAdService {
    AdResponse getAdById(Long id);

    boolean deleteAdById(Long id);

    AdResponse createAd(List<MultipartFile> fileList, CreateAdRequest request) throws Exception;

    List<AdResponse> getAllAds();

    boolean updateAdById(Long id, UpdateAdRequest request);

    List<AdResponse> getAllPublisherAds(Long id);

    PictureResponse getPicture(Long adId);

    List<PictureResponse> getAllPictures(Long adID);

    void uploadPicture(MultipartFile file) throws IOException;

    PictureResponse getImage(String imageName);
}
