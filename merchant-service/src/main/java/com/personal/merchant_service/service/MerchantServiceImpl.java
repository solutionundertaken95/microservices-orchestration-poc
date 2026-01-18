package com.personal.merchant_service.service;

import com.personal.merchant_service.model.CreditRequest;
import com.personal.merchant_service.model.CreditResponse;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService{

    @Override
    public CreditResponse credit(CreditRequest request) {

        validate(request);

        System.out.println(
                "Credited " + request.getAmount() + " to merchant " + request.getMerchantId()
        );

        return new CreditResponse(
                request.getTransactionId(),
                "CREDIT_SUCCESS"
        );
    }

    private void validate(CreditRequest request) {
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid credit amount");
        }
        if (request.getMerchantId() == null) {
            throw new IllegalArgumentException("Merchant ID missing");
        }
    }
}
