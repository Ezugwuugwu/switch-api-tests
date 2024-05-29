package com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayWithVirtualAccountResponse {
    private String accountNumber;
    private String bankName;
    private int amount;
    private String transactionReference;
    private String responseCode;
    private int validityPeriodMins;
    private String accountName;
}
