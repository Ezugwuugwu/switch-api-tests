package com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QrPaymentResponse {

    private String qrCodeId;
    private String qrCodeIdMasterPass;
    private String rawQRData;
    private String transactionReference;
}
