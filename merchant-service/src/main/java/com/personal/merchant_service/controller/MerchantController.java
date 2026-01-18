package com.personal.merchant_service.controller;

import com.personal.merchant_service.model.CreditRequest;
import com.personal.merchant_service.service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping("/credit")
    public ResponseEntity<?> credit(@RequestBody CreditRequest request) {
        return ResponseEntity.ok(merchantService.credit(request));
    }

    @PostMapping("/debit")
    public ResponseEntity<String> compensateCredit(@RequestBody CreditRequest request) {
        System.out.println(
                "Compensating credit for transaction " + request.getTransactionId()
        );
        return ResponseEntity.ok("MERCHANT_DEBITED");
    }


}
