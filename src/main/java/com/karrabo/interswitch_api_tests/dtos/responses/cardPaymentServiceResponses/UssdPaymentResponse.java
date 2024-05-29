package com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UssdPaymentResponse {
    private String reference;
    private String bankShortCode;
    private String transactionReference;
    private String responseCode;
    private String defaultShortCode;
}
