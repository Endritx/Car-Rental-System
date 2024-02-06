package com.RentalCars.controller;

import com.RentalCars.dto.CreditCardDto;
import com.RentalCars.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment/addCreditCard")
    public void addCreditCard(@RequestBody CreditCardDto creditCardDto) {
        paymentService.addCreditCard(creditCardDto);
    }

    @PutMapping("/payment/moneyTransfer")
    public void moneyTransfer(@RequestParam Long moneyAmount) {
        paymentService.moneyTransfer(moneyAmount);
    }

}
