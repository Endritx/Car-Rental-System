package com.RentalCars.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "car_package")
public class CarPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "package_name", nullable = false)
    private String packageName;

    @Column(name = "price_per_hour", nullable = false)
    private Integer pricePerHour;

    @JsonIgnore
    @OneToMany(mappedBy = "carPackage")
    private Collection<Car> cars;

}
