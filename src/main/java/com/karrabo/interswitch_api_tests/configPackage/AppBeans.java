package com.karrabo.interswitch_api_tests.configPackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppBeans {

    @Bean
    public RestClient restClient() {
        RestTemplate oldRestTemplate = new RestTemplate();
        return  RestClient.create(oldRestTemplate);
    }

}
