package com.RentalCars.controller;

import com.RentalCars.domain.Car;
import com.RentalCars.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.List;


import com.RentalCars.domain.CarPackage;
import com.RentalCars.domain.CarParameters;
import com.RentalCars.dto.CarDto;
import com.RentalCars.dto.CarPackageDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public List<Car> getAllCars(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        return carService.getAllCars(page, sort);
    }

    @GetMapping("/cars/packages")
    public List<CarPackage> getCarPackages() {
        return carService.getCarPackages();
    }

    @GetMapping("/cars/available")
    public List<Car> getAvailableCars(@RequestParam(required = false) Integer page, Sort.Direction sort) {
        return carService.getAvailableCars(page, sort);
    }

    @GetMapping("/cars/{id}")
    public Car getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @PostMapping("/cars")
    public Car saveCar(@RequestBody CarDto carDto) {
        return carService.saveCar(carDto);
    }

    @PutMapping("/cars/{id}")
    public Car editCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        return carService.editCar(id, carDto);
    }

    @PutMapping("/cars/{id}/parameters")
    public Car setCarParameters(@PathVariable Long id, @RequestBody CarParameters carParameters) {
        return carService.setCarParameters(id, carParameters);
    }

    @PutMapping("/cars/{id}/packages")
    public Car setCarPackage(@PathVariable Long id, @RequestParam String packageName) {
        return carService.setCarPackage(id, packageName);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @PostMapping("/cars/packages")
    public CarPackage saveCarPackage(CarPackageDto carPackageDto) {
        return carService.saveCarPackage(carPackageDto);
    }

    @DeleteMapping("/cars/packages/{id}")
    public void deleteCarPackage(@PathVariable Long id) {
        carService.deleteCarPackage(id);
    }

}