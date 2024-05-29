package com.karrabo.interswitch_api_tests.service.implementations.authServiceImpl;

import com.karrabo.interswitch_api_tests.service.authService.AuthenticationService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    private static final String AUTH_URL = "https://passport.k8.isw.la/passport/oauth/token";
    private static final String AUTH_HEADER_VALUE = "Basic SUtJQTk2MTRCODIwNjRENjMyRTlCNjQxOERGMzU4QTZBNEFFQTg0RDcyMTg6WENUaUJ0THkxRzljaEFueWcwejNCY2FGSzRjVnB3RGcvR1R3MkVtalRaOD0=";

    private final RestTemplate restTemplate;

    public AuthenticationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public String authenticate() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", AUTH_HEADER_VALUE);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.exchange(AUTH_URL, HttpMethod.POST, requestEntity, Map.class);

        return (String) response.getBody().get("access_token");
    }

}
