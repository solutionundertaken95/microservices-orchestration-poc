package com.personal.merchant_service.service;

import com.personal.merchant_service.model.CreditRequest;
import com.personal.merchant_service.model.CreditResponse;

public interface MerchantService {
    CreditResponse credit(CreditRequest request);
}
