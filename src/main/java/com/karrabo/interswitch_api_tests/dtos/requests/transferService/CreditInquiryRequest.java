package com.karrabo.interswitch_api_tests.dtos.requests.transferService;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreditInquiryRequest {

    private String destinationAccountNumber;
    private String sourceAccountNumber;
    private String sourceAccountName;
    private String destinationInstitutionCode;
    private Long transactionAmount;
    private String currencyCode;
    private String clientRef;
    private String mobileNumber;
    private String emailAddress;
    private Long channelCode;
    private String paymentLocation;

}
