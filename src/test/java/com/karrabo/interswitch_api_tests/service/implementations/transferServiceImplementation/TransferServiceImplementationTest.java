package com.karrabo.interswitch_api_tests.service.implementations.transferServiceImplementation;

import com.karrabo.interswitch_api_tests.dtos.requests.transferService.CreditInquiryRequest;
import com.karrabo.interswitch_api_tests.dtos.requests.transferService.ListOfReceivingInstitutionsRequest;
import com.karrabo.interswitch_api_tests.dtos.responses.transferService.CreditInquiryResponse;
import com.karrabo.interswitch_api_tests.dtos.responses.transferService.ListOfReceivingInstitutionResponse;
import com.karrabo.interswitch_api_tests.exception.KarraboException;
import com.karrabo.interswitch_api_tests.service.transferService.TransferService;
import com.karrabo.interswitch_api_tests.utils.ExceptionMessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
@Slf4j
class TransferServiceImplementationTest {

    @Autowired
    private TransferService transferService;

    private Long transactionAmount;
    private String clientRef;

    @BeforeEach
    public void getTestData() {
        SecureRandom random = new SecureRandom();
        int min = 1;  // 1000 / 1000
        int max = 99; // 99000 / 1000
        int randomThousand = random.nextInt((max - min) + 1) + min;
        transactionAmount = randomThousand * 1000L;

        String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder stringBuilder = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(ALPHANUMERIC_CHARACTERS.length());
            stringBuilder.append(ALPHANUMERIC_CHARACTERS.charAt(randomIndex));
        }
        clientRef = stringBuilder.toString();
    }

    @Test
    void createCreditInquiry() {
        CreditInquiryResponse response = null;
        try {
             response = transferService.createCreditInquiry(
                    CreditInquiryRequest.builder()
                            .destinationAccountNumber("0123456789")
                            .sourceAccountNumber("8096080")
                            .sourceAccountName("Tolani Moshood")
                            .destinationInstitutionCode("ABP")
                            .transactionAmount(transactionAmount)
                            .currencyCode("566")
                            .clientRef(clientRef)
                            .mobileNumber("2348089546016")
                            .emailAddress("siryaya@gmail.com")
                            .channelCode(2L)
                            .paymentLocation("PBL, T, 10360057075012, Adubiaro, DeborLaNG").build()
             );
        } catch (KarraboException e) {
            log.error("Error: ", e);
        }
        assertNotNull(response);
    }

    @Test
    void getListOfReceivingInstitutionOutsidePage() {
        assertThrows(KarraboException.class, () -> transferService.getListOfReceivingInstitution(
                ListOfReceivingInstitutionsRequest.builder()
                        .perPage(10)
                        .pageNumber(10).build()
        ), ExceptionMessageConstants.PAGE_EXCEEDED);
    }

    @Test
    void getListOfReceivingInstitution() {
        ListOfReceivingInstitutionResponse response = null;
        try {
            response = transferService.getListOfReceivingInstitution(
                    ListOfReceivingInstitutionsRequest.builder()
                            .perPage(10)
                            .pageNumber(2).build()
            );
        } catch (KarraboException e) {
            log.error("Error: ", e);
        }
        assertNotNull(response);
        assertEquals(10L, response.getPerPage());
        assertEquals(10, response.getInstitutions().size());
    }

}
