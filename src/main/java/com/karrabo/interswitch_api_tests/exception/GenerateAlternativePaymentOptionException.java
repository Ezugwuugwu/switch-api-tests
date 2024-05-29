package com.karrabo.interswitch_api_tests.exception;

import org.springframework.web.client.HttpClientErrorException;

public class GenerateAlternativePaymentOptionException extends KarraboException {
    public GenerateAlternativePaymentOptionException(String failedToFetchWalletCards, HttpClientErrorException message) {
        super(message);
    }
}
