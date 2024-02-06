package com.RentalCars.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CarDto {

    private String registrationNr;
    private String brand;
    private String model;
    private Boolean isAvailable;

}
