package com.personal.account_service.service;

import com.personal.account_service.model.DebitRequest;
import com.personal.account_service.model.DebitResponse;

public interface AccountService {

    DebitResponse debit(DebitRequest request);
}
