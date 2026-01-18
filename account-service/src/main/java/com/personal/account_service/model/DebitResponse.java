package com.personal.account_service.model;

import lombok.Data;

@Data
public class DebitResponse {
    String transactionId;
    String status;

    public DebitResponse(String transactionId, String debitSuccess) {
    }
}
