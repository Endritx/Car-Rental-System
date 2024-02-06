package com.RentalCars.service;

import com.RentalCars.domain.CreditCard;
import com.RentalCars.domain.User;
import com.RentalCars.dto.CreditCardDto;
import com.RentalCars.exception.NoCreditCardException;
import com.RentalCars.repository.CreditCardRepository;
import com.RentalCars.repository.UserRepository;
import com.RentalCars.security.LoggedInUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

        @Mock
        UserRepository userRepository;

        @Mock
        CreditCardRepository creditCardRepository;

        @Mock
        LoggedInUser loggedInUser;

        @InjectMocks
        PaymentService paymentService;

        @Test
        void itShouldAddCreditCardToUser() {
                User user = User.builder()
                        .firstName("Mickey")
                        .lastName("Rourke")
                        .build();

                CreditCardDto creditCardDto = CreditCardDto.builder()
                        .cardNumber(8888943300781111L)
                        .build();

                CreditCard creditCard = CreditCard.builder()
                        .cardNumber(8888943300781111L)
                        .accountBalance(0L)
                        .build();


                when(loggedInUser.getUser()).thenReturn(user);
                when(creditCardRepository.save(creditCard)).thenReturn(creditCard);
                when(userRepository.save(user)).thenReturn(user);


                paymentService.addCreditCard(creditCardDto);

                assertThat(user.getCreditCard()).isEqualTo(creditCard);
        }

        @Test
        void itShouldMakeMoneyTransfer() {
                CreditCard creditCard = CreditCard.builder()
                        .accountBalance(0L)
                        .build();

                User user = User.builder()
                        .firstName("Richard")
                        .lastName("Hammond")
                        .creditCard(creditCard)
                        .build();


                when(loggedInUser.getUser()).thenReturn(user);
                when(userRepository.save(user)).thenReturn(user);


                paymentService.moneyTransfer(700L);

                assertThat(user.getCreditCard().getAccountBalance()).isEqualTo(700L);
        }

        @Test
        void itShouldThrowNoCreditCardException() {
                User user = User.builder()
                        .username("MeekMill765")
                        .creditCard(null)
                        .build();

                when(loggedInUser.getUser()).thenReturn(user);

                assertThrows(NoCreditCardException.class, () -> paymentService.moneyTransfer(400L));
        }

}