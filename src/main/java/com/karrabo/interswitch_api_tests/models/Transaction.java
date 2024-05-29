package com.karrabo.interswitch_api_tests.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private String id;
    private String merchantCode;
    private String dateOfPayment;
    private double amount;
    private String transactionReference;
}
