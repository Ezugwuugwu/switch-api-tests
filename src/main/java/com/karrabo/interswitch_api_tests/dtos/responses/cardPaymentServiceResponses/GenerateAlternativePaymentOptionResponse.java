package com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenerateAlternativePaymentOptionResponse {

    private Merchant merchant;
    private List<PaymentOption> paymentOptions;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class PaymentOption {
    private String payableCode;
    private String providerCode;
    private boolean enabled;
    private AdditionalInformation additionalInformation;
}


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class AdditionalInformation {
    private String supportedCountry;
    private List<Provider> providers;
    private GooglePay googlePay;
    private String categoryId;
    private String countryCode;
    private List<String> supportedCardTypeCodes;
    private RequiredPaymentFields requiredPaymentFields;
}


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Provider {
    private int id;
    private String paymentLabel;
    private boolean supportsCallback;
    private String providerId;
    private String categoryCode;
    private String esbRoute;
    private boolean enabled;
    private ProviderDetails provider;
    private String country;
    private String description;
    private String countryCode;
}


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class ProviderDetails {
    private int id;
    private String institutionCode;
    private String providerId;
    private int categoryId;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class GooglePay {
    private String gateway;
    private String gatewayMerchantId;
    private String environment;
    private List<String> allowedCardNetworks;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class RequiredPaymentFields {
    private String VALIDATION;
    private String PURCHASE;
}
