package com.karrabo.interswitch_api_tests.dtos.responses.cardPaymentServiceResponses;

import com.karrabo.interswitch_api_tests.models.Refund;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRefundsResponse {

    private int count;
    private List<Refund> content;
}
