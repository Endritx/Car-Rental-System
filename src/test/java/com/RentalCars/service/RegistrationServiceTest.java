package com.RentalCars.service;

import com.RentalCars.domain.User;
import com.RentalCars.dto.UserInDto;
import com.RentalCars.exception.ExistingEntityException;
import com.RentalCars.exception.WeakPasswordException;
import com.RentalCars.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

        @Mock
        UserRepository userRepository;

        @InjectMocks
        RegistrationService registrationService;

        @Test
        void itShouldThrowExistingEntityException() {
                UserInDto userInDto = UserInDto.builder()
                        .username("GreenJohn78")
                        .build();

                User user = User.builder()
                        .username("GreenJohn78")
                        .build();


                when(userRepository.findByUsername(userInDto.getUsername())).thenReturn(Optional.of(user));


                assertThrows(ExistingEntityException.class, () -> registrationService.registerUser(userInDto));
        }

        @Test
        void itShouldThrowWeakPasswordException() {
                UserInDto userInDto = UserInDto.builder()
                        .username("JohnBDP685")
                        .password("johnapple56")
                        .build();


                when(userRepository.findByUsername(userInDto.getUsername())).thenReturn(Optional.empty());


                assertThrows(WeakPasswordException.class, () -> registrationService.registerUser(userInDto));
        }

}