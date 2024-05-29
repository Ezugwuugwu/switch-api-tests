package com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Merchant {
    private String name;
    private String merchantCode;
    private String logoUrl;
    private int corporateApprovalStatus;
    private int settlementType;

}
