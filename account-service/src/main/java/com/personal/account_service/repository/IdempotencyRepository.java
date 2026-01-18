package com.personal.account_service.repository;

import com.personal.account_service.model.IdempotencyKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdempotencyRepository extends JpaRepository<IdempotencyKey,Long> {

    Optional<IdempotencyKey> findByIdempotencyKey(String idempotencyKey);
}
