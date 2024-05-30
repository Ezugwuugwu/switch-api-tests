package com.karrabo.interswitch_api_tests.dtos.responses.transferService;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ListOfReceivingInstitutionResponse {

    private Long total;
    private Long perPage;
    private Long page;
    private List<InstitutionResponse> institutions;

}
