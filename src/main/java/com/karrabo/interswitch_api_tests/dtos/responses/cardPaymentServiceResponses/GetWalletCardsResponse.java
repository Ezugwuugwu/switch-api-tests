package com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetWalletCardsResponse {
    private List<PaymentMethod> paymentMethods;
    private User user;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentMethod {
        private String cardTypeCode;
        private String cardTypeName;
        private String maskedPan;
        private String accountNumber;
        private String cardIdentifier;
        private String cardHash;
        private String walletInstrumentIdentifier;
        private String status;
        private String expiryMonth;
        private String expiryYear;
        private Issuer issuer;
        private String name;
        private String cookieKey;
        private boolean loyaltyRedemptionAllowed;
        private boolean maskToken;
        private boolean enableFingerPrint;
        private CardTypeGatewayConfiguration cardTypeGatewayConfiguration;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Issuer {
        private int id;
        private String name;
        private String code;
        private int domainTypeId;
        private String countryCode;
        private String cbnCode;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CardTypeGatewayConfiguration {
        private int id;
        private boolean supportsPin;
        private boolean supportsCvv2;
        private boolean supportsExpiryDate;
        private boolean enabled;
        private String cardTypeCode;
        private boolean supportsOtp;
        private boolean supportsCardinalAuthentication;
        private boolean loyaltyRedeemEnabled;
        private boolean loyaltyEnabled;
        private boolean supportsFingerPrintAuthorization;
        private boolean supportsAccountType;
        private boolean supportsVisaAcceleratedConnectionPlatform;
        private boolean supportsCybersource20;
        private boolean cybersourceEnabled;
        private String cybersourceCardId;

        // Getters and setters
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private String id;
        private String username;
        private String email;
        private String mobileNo;
        private String firstName;
        private String lastName;
        private String passportId;
        private boolean active;
        private boolean admin;
        private String domainId;
        private String domainName;
        private String domainCode;
        private String roleId;
        private List<String> roles;
        private List<String> domains;
        private List<String> apps;
    }
}
