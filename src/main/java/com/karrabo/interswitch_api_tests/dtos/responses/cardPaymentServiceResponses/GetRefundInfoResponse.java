package com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRefundInfoResponse {

    private long id;
    private String refundReference;
    private long parentPaymentId;
    private String parentTransactionReference;
    private String refundType;
    private double refundAmount;
    private long createdDate;
    private String merchantCustomerId;
    private String refundStatus;
    private long processedDate;
    private String createdBy;
    private String reasonForRefund;
}
