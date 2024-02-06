package com.RentalCars.repository;

import com.RentalCars.domain.CarParameters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarParametersRepository extends JpaRepository<CarParameters, Long> {
}
