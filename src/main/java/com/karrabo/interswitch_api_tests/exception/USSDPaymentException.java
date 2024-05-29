package com.karrabo.interswitch_api_tests.exception;

public class USSDPaymentException extends KarraboException {
    public USSDPaymentException(String errorGeneratingUssdPayment, Exception message) {
        super(message);
    }
}
