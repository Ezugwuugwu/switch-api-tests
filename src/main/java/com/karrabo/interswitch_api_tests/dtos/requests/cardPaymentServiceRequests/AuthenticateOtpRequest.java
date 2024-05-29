package com.karrabo.interswitch_api_tests.dtos.requests.cardPaymentServiceRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateOtpRequest {
   private String paymentId;
   private String otp;
   private String accessToken;
}
