package com.karrabo.interswitch_api_tests.service.implementations;

import com.karrabo.interswitch_api_tests.service.authService.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationServiceImplTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    void testAuthenticate() {
        String accessToken = authenticationService.authenticate();
        assertNotNull(accessToken);
        System.out.println("Access Token: " + accessToken);
    }
}