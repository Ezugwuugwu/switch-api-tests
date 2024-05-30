package com.karrabo.interswitch_api_tests.dtos.requests.transferService;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ListOfReceivingInstitutionsRequest {

    private Long perPage;
    private Long pageNumber;

}
