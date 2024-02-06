package com.RentalCars.mapper;

import com.RentalCars.domain.Car;
import com.RentalCars.dto.CarDto;
import org.springframework.stereotype.Service;

@Service
public class CarDtoMapper {

        public static Car mapToCar(CarDto carDto) {

                return Car.builder()
                        .registrationNr(carDto.getRegistrationNr())
                        .brand(carDto.getBrand())
                        .model(carDto.getModel())
                        .isAvailable(carDto.getIsAvailable())
                        .build();

        }

}
