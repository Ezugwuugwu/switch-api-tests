package com.karrabo.interswitch_api_tests.exception;

public class QrPaymentException extends KarraboException {
    public QrPaymentException(String errorGeneratingQrPayment, Exception message) {
        super(message);
    }
}
