package com.karrabo.interswitch_api_tests.dtos.responses.transferService;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InstitutionResponse {

    private String institutionId;
    private String institutionCode;
    private String institutionName;

}
