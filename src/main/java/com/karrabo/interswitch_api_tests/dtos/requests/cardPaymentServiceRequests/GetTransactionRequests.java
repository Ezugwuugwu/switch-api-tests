package com.karrabo.interswitch_api_tests.dtos.requests.cardPaymentServiceRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetTransactionRequests {

    private String merchantCode;
    private String startDate;
    private String endDate;
    private int pageSize;
    private int pageNum;
    private String transactionReference;
}
