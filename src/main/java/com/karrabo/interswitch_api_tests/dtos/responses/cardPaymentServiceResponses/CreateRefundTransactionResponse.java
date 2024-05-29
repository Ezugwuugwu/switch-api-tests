package com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRefundTransactionResponse {

    private long id;
    private String refundReference;
    private String merchantCode;
    private long parentPaymentId;
    private String refundType;
    private double refundAmount;
    private long createdDate;
    private String refundStatus;
    private String createdBy;
    private String reasonForRefund;
}
