package com.personal.merchant_service.model;

import lombok.Data;

@Data
public class CreditRequest {

    String transactionId;
    String merchantId;
    double amount;
}
