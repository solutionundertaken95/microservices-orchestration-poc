package com.personal.account_service.controller;

import com.personal.account_service.model.DebitRequest;
import com.personal.account_service.model.DebitResponse;
import com.personal.account_service.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/debit")
    public ResponseEntity<DebitResponse> debit(@RequestBody DebitRequest request) {
        return ResponseEntity.ok(accountService.debit(request));
    }

    @PostMapping("/credit")
    public ResponseEntity<String> compensateDebit(@RequestBody DebitRequest request) {
        System.out.println(
                "Compensating debit for transaction " + request.getTransactionId()
        );
        return ResponseEntity.ok("ACCOUNT_CREDITED");
    }

}
