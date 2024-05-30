package com.karrabo.interswitch_api_tests.dtos.responses.authentication;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InterswitchAuthenticationResponse {

    private String access_token;
    private String token_type;
    private String bearer;
    private Long expires_in;
    private String scope;
    private String merchant_code;
    private String production_payment_code;
    private Long productId;
    private String requestor_id;
    private String env;
    private String payable_id;
    private String client_description;
    private String institution_id;
    private String core_id;
    private List<String> api_resources;
    private String postcard_user;
    private String client_name;
    private String client_logo;
    private String payment_code;
    private String jti;

}
