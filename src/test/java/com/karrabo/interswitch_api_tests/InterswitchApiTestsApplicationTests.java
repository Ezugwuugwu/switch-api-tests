//package com.karrabo.interswitch_api_tests;
//
//import com.karrabo.interswitch_api_tests.configPackage.RestTemplateConfig;
//import com.karrabo.interswitch_api_tests.service.authService.AuthenticationService;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.*;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Map;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//class InterswitchApiTestsApplicationTests {
//
//	@Autowired
//	private AuthenticationService authenticationService;
//
//	@Test
//	void testAuthenticate() {
//		String accessToken = authenticationService.authenticate();
//		assertNotNull(accessToken);
//		System.out.println("Access Token: " + accessToken);
//	}
//
//
//}
