package com.karrabo.interswitch_api_tests.exception;

public class RefundInfoNotFoundException extends KarraboException {
    public RefundInfoNotFoundException(String errorFetchingRefundInfo, Exception message) {
        super(message);
    }
}
