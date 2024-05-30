package com.karrabo.interswitch_api_tests.service.implementations.transferServiceImplementation;

import com.karrabo.interswitch_api_tests.service.transferService.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class TransferServiceImplementationTest {

    @Autowired
    private TransferService transferService;

    @Test
    void createCreditInquiry() {
    }

    @Test
    void getListOfReceivingInstitution() {
    }
}