package com.personal.ledger_service.service;

import com.personal.ledger_service.model.LedgerEntry;

public interface LedgerService {
    void record(LedgerEntry entry);
}
