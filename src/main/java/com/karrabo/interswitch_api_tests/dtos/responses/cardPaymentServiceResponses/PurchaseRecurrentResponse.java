package com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses;

import lombok.Data;

@Data
public class PurchaseRecurrentResponse {

   private String transactionRef;
   private String paymentId;
   private String bankCode;
   private String message;
   private String amount;
   private String responseCode;
   private String plainTextSupportMessage;
}
