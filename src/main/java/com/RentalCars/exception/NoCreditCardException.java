package com.RentalCars.exception;

public class NoCreditCardException extends RuntimeException {

        public NoCreditCardException(String message) {
                super(message);
        }

}
