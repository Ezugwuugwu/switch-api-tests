package com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses;

import lombok.Data;


@Data
public class TokenizeCardResponse {

    private String cardType;
    private String balance;
    private String token;
    private String tokenExpiryDate;
    private String panLast4Digits;
    private String transactionRef;
}
