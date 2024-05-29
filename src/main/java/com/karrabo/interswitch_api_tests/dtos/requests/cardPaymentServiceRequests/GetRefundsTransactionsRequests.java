package com.karrabo.interswitch_api_tests.dtos.requests.cardPaymentServiceRequests;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetRefundsTransactionsRequests {
   private String merchantCode;
   private String startDate;
   private String endDate;
   private int pageSize;
   private int pageNum;

}
