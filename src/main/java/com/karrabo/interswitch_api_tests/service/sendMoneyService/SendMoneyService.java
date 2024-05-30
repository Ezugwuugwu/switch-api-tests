package com.karrabo.interswitch_api_tests.service.sendMoneyService;

public interface SendMoneyService {

    String tokenAPI() throws Exception;

    String agencyBanking() throws Exception;

    String agencyBankingRequery() throws Exception;
}
