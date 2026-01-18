package com.personal.ledger_service.service;

import com.personal.ledger_service.model.IdempotencyKey;
import com.personal.ledger_service.model.LedgerEntry;
import com.personal.ledger_service.repository.IdempotencyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LedgerServiceImpl implements LedgerService {

    private final IdempotencyRepository idempotencyRepository;

    public LedgerServiceImpl(IdempotencyRepository idempotencyRepository) {
        this.idempotencyRepository = idempotencyRepository;
    }

    @Override
    @Transactional
    public void record(LedgerEntry entry) {

        // 1️⃣ Check if this transaction was already recorded
        Optional<IdempotencyKey> existing =
                idempotencyRepository.findByIdempotencyKey(entry.getTransactionId());

        if (existing.isPresent()) {
            // Already recorded → do nothing
            System.out.println(
                    "Ledger already recorded for transaction: " + entry.getTransactionId()
            );
            return;
        }

        // 2️⃣ Normal validation
        validate(entry);

        // 3️⃣ Record ledger entry (append-only)
        System.out.println("Ledger recorded: " + entry);

        // 4️⃣ Save idempotency record
        IdempotencyKey key = new IdempotencyKey();
        key.setIdempotencyKey(entry.getTransactionId());
        key.setRequestHash(calculateHash(entry));
        key.setResponse("LEDGER_RECORDED");

        idempotencyRepository.save(key);
    }

    private void validate(LedgerEntry entry) {
        if (entry.getTransactionId() == null) {
            throw new IllegalArgumentException("Transaction ID missing");
        }
        if (entry.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
    }

    private String calculateHash(LedgerEntry entry) {
        return entry.getTransactionId() + "|" + entry.getAmount();
    }
}

