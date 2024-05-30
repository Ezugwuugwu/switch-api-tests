package com.karrabo.interswitch_api_tests.dtos.requests.transferService;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ListOfReceivingInstitutionsRequest {

    private int perPage;
    private int pageNumber;

}
