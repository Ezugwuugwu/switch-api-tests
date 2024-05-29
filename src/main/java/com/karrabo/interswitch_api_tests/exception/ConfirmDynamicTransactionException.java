package com.karrabo.interswitch_api_tests.exception;

public class ConfirmDynamicTransactionException extends KarraboException {

    public ConfirmDynamicTransactionException(String errorConfirmingDynamicTransfer, Exception message) {
        super(message);
    }
}
