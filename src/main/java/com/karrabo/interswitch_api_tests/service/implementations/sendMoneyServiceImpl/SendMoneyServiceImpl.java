package com.karrabo.interswitch_api_tests.service.implementations.sendMoneyServiceImpl;

import com.karrabo.interswitch_api_tests.exception.TokenAPIException;
import com.karrabo.interswitch_api_tests.service.sendMoneyService.SendMoneyService;
import com.karrabo.interswitch_api_tests.utils.ExceptionMessageConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class SendMoneyServiceImpl implements SendMoneyService {

    String RAW_BODY = "";
    private final RestTemplate restTemplate;

    @Value("${interswitch.agency.banking.auth.token}")
    private String agencyBankingAuthToken;

    @Value("${interswitch.token.api.url}")
    private String tokenProcessUrl;

    @Value("${interswitch.agency.banking.api.url}")
    private String agencyBankingApiUrl;

    @Value("${interswitch.agency.banking.requery.api.url}")
    private String agencyBankingRequeryApiUrl;

    public SendMoneyServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public String tokenAPI() throws Exception {
        String TOKEN_API_URL = tokenProcessUrl;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

            HttpEntity<String> entity = new HttpEntity<>(RAW_BODY, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    TOKEN_API_URL,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            return response.getBody();
        } catch (Exception message) {
            throw new TokenAPIException(ExceptionMessageConstants.FAILED_TO_GENERATE_TOKEN, message);
        }
    }

    @Override
    public String agencyBanking() throws Exception {
        String AGENCY_BANKING_URL =  agencyBankingApiUrl;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("Authorization", agencyBankingAuthToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    AGENCY_BANKING_URL,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            return response.getBody();
        } catch (Exception message) {
            throw new Exception("Failed to call AMEX endpoint", message);
        }
    }

    @Override
    public String agencyBankingRequery() throws Exception {

        String AGENCY_BANKING_REQUERY_URL = agencyBankingRequeryApiUrl;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    AGENCY_BANKING_REQUERY_URL,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            return response.getBody();
        } catch (Exception e) {
            throw new Exception("Failed to perform requery", e);
        }
    }

}
