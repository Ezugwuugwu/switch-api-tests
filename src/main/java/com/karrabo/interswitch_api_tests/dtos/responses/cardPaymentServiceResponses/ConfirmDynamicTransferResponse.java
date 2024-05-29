package com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses;

import lombok.Data;

@Data
public class ConfirmDynamicTransferResponse {

    private String responseCode;
    private String responseDescription;
    private String transactionReference;
    private String channelTransactionReference;
    private int amount;
    private int remittanceAmount;
    private String customerName;
    private String bank;
}
