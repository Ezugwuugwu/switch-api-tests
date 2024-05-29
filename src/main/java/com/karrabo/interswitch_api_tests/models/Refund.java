package com.karrabo.interswitch_api_tests.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Refund {

    private long id;
    private String refundReference;
    private long parentPaymentId;
    private String parentTransactionReference;
    private String refundType;
    private double refundAmount;
    private long createdDate;
    private String merchantCustomerId;
    private String refundStatus;
    private String createdBy;
    private boolean validated;
}
