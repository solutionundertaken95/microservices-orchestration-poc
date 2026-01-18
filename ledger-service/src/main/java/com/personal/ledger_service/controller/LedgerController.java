package com.personal.ledger_service.controller;

import com.personal.ledger_service.model.LedgerEntry;
import com.personal.ledger_service.service.LedgerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ledger")
public class LedgerController {

    private final LedgerService ledgerService;

    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @PostMapping("/record")
    public ResponseEntity<?> record(@RequestBody LedgerEntry entry) {
        ledgerService.record(entry);
        return ResponseEntity.ok("LEDGER_RECORDED");
    }
}
