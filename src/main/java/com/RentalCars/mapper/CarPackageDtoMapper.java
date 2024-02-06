package com.RentalCars.mapper;

import com.RentalCars.domain.CarPackage;
import com.RentalCars.dto.CarPackageDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CarPackageDtoMapper {

        public static CarPackage mapToCarPackage(CarPackageDto carPackageDto) {

                return CarPackage.builder()
                        .packageName(carPackageDto.getPackageName())
                        .pricePerHour(carPackageDto.getPricePerHour())
                        .cars(new ArrayList<>())
                        .build();

        }

}
