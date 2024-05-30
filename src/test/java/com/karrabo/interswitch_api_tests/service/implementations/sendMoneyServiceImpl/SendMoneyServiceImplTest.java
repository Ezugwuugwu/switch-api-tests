package com.karrabo.interswitch_api_tests.service.implementations.sendMoneyServiceImpl;

import com.karrabo.interswitch_api_tests.service.sendMoneyService.SendMoneyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SendMoneyServiceImplTest {

    @Autowired
    private SendMoneyService sendMoneyService;

    @Test
    public void testPerformTokenProcessSuccess() throws Exception {
        String response = sendMoneyService.tokenAPI();
        assertNotNull(response);
    }

    @Test
    public void testAgencyBankingAPI_Success() throws Exception {
        String response = sendMoneyService.agencyBanking();
        assertNotNull(response);
    }

    @Test
    public void testPerformRequerySuccess() throws Exception {

        String response = sendMoneyService.agencyBankingRequery();
        assertNotNull(response);
        System.out.println(response);
    }
}