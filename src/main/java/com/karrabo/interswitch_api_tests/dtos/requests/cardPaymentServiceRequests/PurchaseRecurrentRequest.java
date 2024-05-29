package com.karrabo.interswitch_api_tests.dtos.requests.cardPaymentServiceRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseRecurrentRequest {
   private String customerId;
   private String amount;
   private String currency;
   private String token;
   private String tokenExpiryDate;
   private String transactionRef;
}
