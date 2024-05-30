package com.karrabo.interswitch_api_tests.service.implementations.authServiceImpl;

import com.karrabo.interswitch_api_tests.dtos.requests.authentication.InterswitchAuthenticationRequest;
import com.karrabo.interswitch_api_tests.dtos.responses.authentication.InterswitchAuthenticationResponse;
import com.karrabo.interswitch_api_tests.exception.InterswitchAuthenticationException;
import com.karrabo.interswitch_api_tests.service.authService.AuthenticationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RestClient restClient;

    @Value("${interswitch.authUrl}")
    private String AUTH_URL;

    @Value("${interswitch.authHeader}")
    private String AUTH_HEADER_VALUE;

    private static String token;

    @PostConstruct
    @Scheduled(cron = "0 0 0 * * ?")
    private void renewAuthenticationToken() throws InterswitchAuthenticationException {
        InterswitchAuthenticationResponse response;
        try {
            response = restClient
                    .post()
                    .uri(AUTH_URL)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", AUTH_HEADER_VALUE)
                    .body(InterswitchAuthenticationRequest
                            .builder()
                            .grant_type("client_credentials")
                            .scope("profile")
                            .build())
                    .retrieve()
                    .body(InterswitchAuthenticationResponse.class);
        } catch (Exception e) {
            throw new InterswitchAuthenticationException("Unable to authorize with interswitch service provider");
        }
        assert response != null;
        token = response.getAccess_token();
    }

    @Override
    public String getToken() {
        return token;
    }

}
