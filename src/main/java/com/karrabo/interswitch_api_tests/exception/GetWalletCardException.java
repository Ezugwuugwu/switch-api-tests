package com.karrabo.interswitch_api_tests.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GetWalletCardException extends KarraboException {

    private final HttpStatus status;

    public GetWalletCardException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
