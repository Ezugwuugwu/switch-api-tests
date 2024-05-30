package com.karrabo.interswitch_api_tests.dtos.requests.authentication;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InterswitchAuthenticationRequest {

    private String grant_type;
    private String scope;

}
