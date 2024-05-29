package com.karrabo.interswitch_api_tests.exception;

public class CreateTransactionException extends KarraboException {
    public CreateTransactionException(String errorCreatingRefundTransaction, Exception message) {
        super(message);
    }
}
