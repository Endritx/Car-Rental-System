package com.RentalCars.service;

import com.RentalCars.domain.CreditCard;
import com.RentalCars.domain.User;
import com.RentalCars.dto.CreditCardDto;
import com.RentalCars.exception.NoCreditCardException;
import com.RentalCars.repository.CreditCardRepository;
import com.RentalCars.repository.UserRepository;
import com.RentalCars.security.LoggedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.RentalCars.mapper.CreditCardDtoMapper.mapToCreditCard;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PaymentService {

        private final UserRepository userRepository;
        private final CreditCardRepository creditCardRepository;
        private final LoggedInUser loggedInUser;

        public void addCreditCard(CreditCardDto creditCardDto) {

                log.info("Adding credit card to user");
                User user = loggedInUser.getUser();

                if(user.getCreditCard() != null) {

                        throw new IllegalCallerException("You Already Have Credit Card!");
                }
                CreditCard card = creditCardRepository.save(mapToCreditCard(creditCardDto));
                user.setCreditCard(card);
                card.setUser(user);
                userRepository.save(user);
        }

        public void moneyTransfer(Long moneyAmount) {

                User user = loggedInUser.getUser();

                if(user.getCreditCard() == null) {

                        throw new NoCreditCardException("You Do Not Have Credit Card!");

                } else {

                        log.info("Transfer for the amount of {}", moneyAmount);
                        CreditCard creditCard = user.getCreditCard();
                        creditCard.setAccountBalance(creditCard.getAccountBalance() + moneyAmount);
                        userRepository.save(user);

                }
        }

}
