package com.karrabo.interswitch_api_tests.dtos.requests.cardPaymentServiceRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRefundTransactionRequest {

    private String refundReference;
    private String parentTransactionReference;
    private String parentPaymentId;
    private String refundType;
    private String refundAmount;
    private String createdBy;
    private String reasonsForRefund;
}
