package com.karrabo.interswitch_api_tests.exception;

public class TokenAPIException extends KarraboException {
    public TokenAPIException(String failedToPerformTokenProcess, Exception message) {
        super(message);
    }
}
