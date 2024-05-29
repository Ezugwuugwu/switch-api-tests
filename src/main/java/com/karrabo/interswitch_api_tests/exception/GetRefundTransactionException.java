package com.karrabo.interswitch_api_tests.exception;

public class GetRefundTransactionException extends KarraboException {
    public GetRefundTransactionException(String errorFetchingRefunds, Exception message) {
        super(message);
    }
}
