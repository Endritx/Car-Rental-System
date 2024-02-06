package com.RentalCars.service;

import com.RentalCars.domain.Car;
import com.RentalCars.domain.PlacedOrder;
import com.RentalCars.domain.User;
import com.RentalCars.exception.InvalidPackageException;
import com.RentalCars.exception.NoAccessKeyException;
import com.RentalCars.exception.UnavailableCarException;
import com.RentalCars.repository.AccessKeyRepository;
import com.RentalCars.repository.CarRepository;
import com.RentalCars.repository.OrderRepository;
import com.RentalCars.security.LoggedInUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DeliveryService {

        public static final Long ID = null;
        private final CarRepository carRepository;
        private final OrderRepository orderRepository;
        private final AccessKeyRepository accessKeyRepository;
        private final LoggedInUser loggedInUser;

        public Car pickUpTheCar(Long carId) {

                Car car = carRepository.findById(carId)
                        .orElseThrow(() -> new EntityNotFoundException("Car With This ID Does Not Exists!"));
                User user = loggedInUser.getUser();
                if(user.getAccessKey() == null) {

                        throw new NoAccessKeyException("You Do Not Have An Access Key!");
                }
                else if(!user.getAccessKey().getCarPackage().equals(car.getCarPackage().getPackageName())) {

                        throw new InvalidPackageException("You Cannot Pick Car From This Package!");
                }
                else if(!car.getIsAvailable()) {

                        throw new UnavailableCarException("This Car Is Not Available!");
                } else {

                        accessKeyRepository.delete(user.getAccessKey());
                        car.setIsAvailable(false);
                        LocalDateTime start = LocalDateTime.now();
                        LocalDateTime end = LocalDateTime.now().plusHours(user.getAccessKey().getHours());
                        PlacedOrder order = new PlacedOrder(ID, user.getId(), car.getId(), car.getBrand(), car.getModel(), start, end);
                        orderRepository.save(order);

                        log.info("You rented a car, have a nice trip!");
                }
                return car;
        }

}
