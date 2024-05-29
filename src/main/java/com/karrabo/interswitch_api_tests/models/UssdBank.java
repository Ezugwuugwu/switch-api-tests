package com.karrabo.interswitch_api_tests.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UssdBank {

    private String name;
    private String code;
    private String cbnCode;
}
