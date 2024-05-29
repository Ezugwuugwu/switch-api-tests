package com.karrabo.interswitch_api_tests.dtos.requests.cardPaymentServiceRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenizeCardRequest {
    private String transactionRef;
    private String authData;
}
