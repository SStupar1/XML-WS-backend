package com.example.demo.soap;

import com.example.demo.dto.soap.CreateFuelTypeRequestSOAP;
import com.example.demo.services.IFuelTypeService;
import com.example.demo.soap.model.FuelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import javax.xml.bind.JAXBElement;

@Endpoint
public class CarEndpoint implements com.example.demo.soap.WSEndpoint {

    private com.example.demo.soap.ObjectFactory objectFactory;

    private final IFuelTypeService _fuelTypeService;
    @Autowired
    public CarEndpoint(IFuelTypeService fuelTypeService) {
        _fuelTypeService = fuelTypeService;
        this.objectFactory = new com.example.demo.soap.ObjectFactory();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createFuelType")
    @ResponsePayload
    public void createFuelType(@RequestPayload JAXBElement<FuelType> request) throws Exception {
        CreateFuelTypeRequestSOAP requestDTO = new CreateFuelTypeRequestSOAP();
        requestDTO.setType(request.getValue().getType());
        requestDTO.setTankCapacity(request.getValue().getTankCapacity());
        requestDTO.setId(request.getValue().getId());
        _fuelTypeService.createFuelTypeViaSOAP(requestDTO);
        System.out.println("Uspeo sam");
    }

}
