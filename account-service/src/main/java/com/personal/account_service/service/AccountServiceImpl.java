package com.personal.account_service.service;

import com.personal.account_service.exception.InsufficientBalanceException;
import com.personal.account_service.model.DebitRequest;
import com.personal.account_service.model.DebitResponse;
import com.personal.account_service.model.IdempotencyKey;
import com.personal.account_service.repository.IdempotencyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final IdempotencyRepository idempotencyRepository;

    public AccountServiceImpl(IdempotencyRepository idempotencyRepository) {
        this.idempotencyRepository = idempotencyRepository;
    }

    @Override
    @Transactional
    public DebitResponse debit(DebitRequest request) {

        // 1️⃣ Check if this transaction was already processed
        Optional<IdempotencyKey> existing =
                idempotencyRepository.findByIdempotencyKey(request.getTransactionId());

        if (existing.isPresent()) {
            // 2️⃣ Return the OLD response (no re-debit!)
            return deserialize(existing.get().getResponse());
        }

        // 3️⃣ Normal business validation
        validate(request);

        double currentBalance = 10_000.00;

        if (request.getAmount() > currentBalance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        // 4️⃣ Perform debit (only once)
        System.out.println(
                "Debited " + request.getAmount() + " from customer " + request.getCustomerId()
        );

        DebitResponse response = new DebitResponse(
                request.getTransactionId(),
                "DEBIT_SUCCESS"
        );

        // 5️⃣ Save result for future retries
        idempotencyRepository.save(
                new IdempotencyKey(
                        request.getTransactionId(),
                        "SUCCESS",
                        serialize(response)
                )
        );

        return response;
    }

    private void validate(DebitRequest request) {
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (request.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID missing");
        }
    }

    // ---- helpers ----

    private String serialize(DebitResponse response) {
        return response.getTransactionId() + "|" + response.getStatus();
    }

    private DebitResponse deserialize(String data) {
        String[] parts = data.split("\\|");
        return new DebitResponse(parts[0], parts[1]);
    }
}
