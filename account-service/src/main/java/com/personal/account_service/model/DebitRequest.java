package com.personal.account_service.model;

import lombok.Data;

@Data
public class DebitRequest {

    String transactionId;
    String customerId;
    double amount;
}
