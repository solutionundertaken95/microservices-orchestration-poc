package com.personal.account_service.dto;

import lombok.Data;

@Data
public class MerchantCreditRequest {
    private String transactionId;
    private String merchantId;
    private double amount;
}
