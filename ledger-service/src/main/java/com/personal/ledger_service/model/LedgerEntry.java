package com.personal.ledger_service.model;

import lombok.Data;

import java.time.Instant;

@Data
public class LedgerEntry {
    String transactionId;
    String customerId;
    String merchantId;
    double amount;
    String status;
    Instant timestamp;
}
