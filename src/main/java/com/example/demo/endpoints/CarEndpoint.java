package com.example.demo.endpoints;

import com.example.demo.entities.Car;
import com.example.demo.mapper.CarMapper;
import com.example.demo.services.CarService;
import com.example.demo.xml.school.car.GetAllCarsRequest;
import com.example.demo.xml.school.car.GetAllCarsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CarEndpoint {

    private static final String NAMESPACE_URI = "http://www.demo.example.com/xml/school/car";

    private CarService service;

    @Autowired
    public CarEndpoint(CarService service){
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllCarsRequest")
    @ResponsePayload
    public GetAllCarsResponse getAllCarsResponse (@RequestPayload GetAllCarsRequest request){
        GetAllCarsResponse response = new GetAllCarsResponse();
        List<Car> carList = service.getAll();

//        for (Car c : carList){
//            com.example.demo.xml.school.car.Car c1 = new com.example.demo.xml.school.car.Car();
//            c1.setId(c.getId());
//            c1.setName(c.getName());
//            response.getCar().add(c1);
//        }

        for (Car c : carList) {
            response.getCar().add(CarMapper.INSTANCE.carToXmlCar(c));
        }

        return response;
    }
}
