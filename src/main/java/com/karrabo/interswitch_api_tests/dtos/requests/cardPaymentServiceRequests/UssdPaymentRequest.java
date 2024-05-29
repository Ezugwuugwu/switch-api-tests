package com.karrabo.interswitch_api_tests.dtos.requests.cardPaymentServiceRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UssdPaymentRequest {

    private String amount;
    private String bankCode;
    private String surcharge;
    private String currencyCode;
    private String merchantTransactionReference;
}
