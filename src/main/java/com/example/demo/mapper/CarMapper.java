package com.example.demo.mapper;

import com.example.demo.xml.school.car.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    Car carToXmlCar(com.example.demo.entities.Car car);
    com.example.demo.entities.Car xmlCarToCar(Car car);
}
