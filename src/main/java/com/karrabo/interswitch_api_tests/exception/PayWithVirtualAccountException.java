package com.karrabo.interswitch_api_tests.exception;

public class PayWithVirtualAccountException extends KarraboException {
    public PayWithVirtualAccountException(String errorProcessingVirtualAccountPayment, Exception message) {
        super(message);
    }
}
