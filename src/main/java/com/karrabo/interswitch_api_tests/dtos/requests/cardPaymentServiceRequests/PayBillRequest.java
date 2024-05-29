package com.karrabo.interswitch_api_tests.dtos.requests.cardPaymentServiceRequests;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayBillRequest {

    private String merchantCode;
    private String payableCode;
    private String amount;
    private String code;
    private String redirectUrl;
    private String transactionReference;
    private String customerId;
    private String customerEmail;
    private String currencyCode;

}
