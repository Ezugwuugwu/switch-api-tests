package com.karrabo.interswitch_api_tests.exception;

public class TransactionNotFoundException extends KarraboException {
    public TransactionNotFoundException(String errorFetchingTransactions, Exception message) {
        super(message);
    }
}
