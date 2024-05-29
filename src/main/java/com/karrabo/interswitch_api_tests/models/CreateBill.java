package com.karrabo.interswitch_api_tests.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBill {

    private String merchantCode;
    private String payableCode;
    private String amount;
    private String redirectUrl;
    private String customerId;
    private String currencyCode;
}
