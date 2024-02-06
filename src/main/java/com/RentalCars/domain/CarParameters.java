package com.RentalCars.domain;

import com.RentalCars.constant.FuelType;
import com.RentalCars.constant.GearBoxType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CarParameters")

public class CarParameters {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    @Column(name = "gear_box_type", nullable = false)
    private GearBoxType gearBoxType;

    @Column(name = "number_of_doors", nullable = false)
    private Integer numberOfDoors;

    @Column(name = "number_of_seats", nullable = false)
    private Integer numberOfSeats;

    @Column(name = "is_air_conditioning_available", nullable = false)
    private Boolean isAirConditioningAvailable;

    @JsonIgnore
    @OneToOne(mappedBy = "carParameters")

    private Car car;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
