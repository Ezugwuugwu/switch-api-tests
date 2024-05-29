package com.karrabo.interswitch_api_tests.dtos.requests.cardPaymentServiceRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayWitVirtualAccountRequest {

    private String merchantCode;
    private String payableCode;
    private String currencyCode;
    private String amount;
    private String accountName;
    private String transactionReference;
}
