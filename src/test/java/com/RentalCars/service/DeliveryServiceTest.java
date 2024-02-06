package com.RentalCars.service;

import com.RentalCars.domain.AccessKey;
import com.RentalCars.domain.Car;
import com.RentalCars.domain.CarPackage;
import com.RentalCars.domain.User;
import com.RentalCars.exception.InvalidPackageException;
import com.RentalCars.exception.NoAccessKeyException;
import com.RentalCars.exception.UnavailableCarException;
import com.RentalCars.repository.AccessKeyRepository;
import com.RentalCars.repository.CarRepository;
import com.RentalCars.repository.OrderRepository;
import com.RentalCars.security.LoggedInUser;
import jakarta.persistence.EntityNotFoundException;
import org.apache.el.parser.AstFalse;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

        @Mock
        CarRepository carRepository;

        @Mock
        OrderRepository orderRepository;

        @Mock
        AccessKeyRepository accessKeyRepository;

        @Mock
        LoggedInUser loggedInUser;

        @InjectMocks
        DeliveryService deliveryService;


        @Test
        void itShouldRentACar() {
                CarPackage sporty = CarPackage.builder()
                        .packageName("Sporty")
                        .pricePerHour(300)
                        .build();

                Car car = Car.builder()
                        .id(1L)
                        .brand("Audi")
                        .model("RS3")
                        .isAvailable(true)
                        .carPackage(sporty)
                        .build();

                AccessKey accessKey = AccessKey.builder()
                        .carPackage("Sporty")
                        .hours(2)
                        .build();

                User user = User.builder()
                        .id(1L)
                        .username("BlackJohn32")
                        .accessKey(accessKey)
                        .build();


                when(carRepository.findById(1L)).thenReturn(Optional.of(car));
                when(loggedInUser.getUser()).thenReturn(user);
                doNothing().when(accessKeyRepository).delete(user.getAccessKey());


                assertThat(deliveryService.pickUpTheCar(1L)).isEqualTo(car);

        }


        private <SELF extends AbstractBigDecimalAssert<SELF>> AbstractBigDecimalAssert assertThat(Car car) {
                return null;
        }

        @Test
        void itShouldThrowEntityNotFoundException() {
                when(carRepository.findById(1L)).thenReturn(Optional.empty());

                assertThrows(EntityNotFoundException.class, () -> deliveryService.pickUpTheCar(1L));
        }

        @Test
        void itShouldThrowNoAccessKeyException() {
                Car car = Car.builder()
                        .id(1L)
                        .brand("Honda")
                        .model("Civic")
                        .build();

                User user = User.builder()
                        .username("MaxVerstappen")
                        .accessKey(null)
                        .build();


                when(carRepository.findById(1L)).thenReturn(Optional.of(car));
                when(loggedInUser.getUser()).thenReturn(user);


                assertThrows(NoAccessKeyException.class, () -> deliveryService.pickUpTheCar(1L));
        }

        @Test
        void itShouldThrowInvalidPackageException() {
                CarPackage carPackage = CarPackage.builder()
                        .packageName("Sporty")
                        .pricePerHour(300)
                        .build();

                Car car = Car.builder()
                        .id(3L)
                        .brand("Fiat")
                        .model("Multipla")
                        .carPackage(carPackage)
                        .build();

                AccessKey accessKey = AccessKey.builder()
                        .carPackage("Luxury")
                        .hours(3)
                        .build();

                User user = User.builder()
                        .username("JohnMcChicken")
                        .accessKey(accessKey)
                        .build();


                when(carRepository.findById(3L)).thenReturn(Optional.of(car));
                when(loggedInUser.getUser()).thenReturn(user);


                assertThrows(InvalidPackageException.class, () -> deliveryService.pickUpTheCar(3L));
        }

        @Test
        void itShouldThrowUnavailableCarException() {
                CarPackage carPackage = CarPackage.builder()
                        .packageName("Ordinary")
                        .pricePerHour(100)
                        .build();

                Car car = Car.builder()
                        .id(2L)
                        .brand("Peugeot")
                        .model("206")
                        .isAvailable(false)
                        .carPackage(carPackage)
                        .build();

                AccessKey accessKey = AccessKey.builder()
                        .carPackage("Ordinary")
                        .hours(7)
                        .build();

                User user = User.builder()
                        .username("JulietBB56")
                        .accessKey(accessKey)
                        .build();


                when(carRepository.findById(2L)).thenReturn(Optional.of(car));
                when(loggedInUser.getUser()).thenReturn(user);


                assertThrows(UnavailableCarException.class, () -> deliveryService.pickUpTheCar(2L));
        }

}