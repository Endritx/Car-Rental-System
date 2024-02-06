package com.RentalCars.mapper;

import com.RentalCars.domain.AccessKey;
import com.RentalCars.dto.AccessKeyDto;
import org.springframework.stereotype.Service;

@Service
public class AccessKeyDtoMapper {

        public static AccessKeyDto mapToAccessKeyDto(AccessKey accessKey) {
                return AccessKeyDto.builder()
                        .id(accessKey.getId())
                        .carPackage(accessKey.getCarPackage())
                        .hours(accessKey.getHours())
                        .build();
        }

}
