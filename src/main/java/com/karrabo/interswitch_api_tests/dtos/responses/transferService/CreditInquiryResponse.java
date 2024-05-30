package com.karrabo.interswitch_api_tests.dtos.responses.transferService;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreditInquiryResponse {

    private String accountName;
    private String clientRef;
    private String accountType;
    private String accountCurrency;
    private String responseCode;
    private String responseMessage;
    private String transactionReference;
    private String bankVerificationNumber;
    private String kycLevel;
    private Boolean canCredit;
    private String action;


    @Override
    public String toString() {
        return "CreditInquiryResponse{" +
                "accountName='" + accountName + '\'' +
                ", clientRef='" + clientRef + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountCurrency='" + accountCurrency + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", transactionReference='" + transactionReference + '\'' +
                ", bankVerificationNumber='" + bankVerificationNumber + '\'' +
                ", kycLevel='" + kycLevel + '\'' +
                ", canCredit=" + canCredit +
                ", action='" + action + '\'' +
                '}';
    }
}
