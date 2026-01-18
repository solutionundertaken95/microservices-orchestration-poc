package com.personal.ledger_service.repository;

import com.personal.ledger_service.model.IdempotencyKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdempotencyRepository extends JpaRepository<IdempotencyKey,Long> {

    Optional<IdempotencyKey> findByIdempotencyKey(String idempotencyKey);
}

