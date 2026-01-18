package com.personal.merchant_service.model;

import lombok.Data;

@Data
public class CreditResponse {

    String transactionId;
    String status;

    public CreditResponse(String transactionId, String creditSuccess) {
    }
}
