package com.RentalCars.repository;

import com.RentalCars.domain.Car;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

        @Query("Select c From Car c")
        List<Car> findCars(Pageable page);

        @Query("Select c From Car c Where c.isAvailable = true")
        List<Car> findAvailableCars(Pageable page);

}
