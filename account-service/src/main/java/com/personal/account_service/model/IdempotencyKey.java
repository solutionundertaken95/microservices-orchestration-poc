package com.personal.account_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "idempotency_keys", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"idempotencyKey"})
})
@Data
public class IdempotencyKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idempotencyKey;

    private String requestHash;

    private String response;

    private Instant createdAt = Instant.now();

    public IdempotencyKey(String transactionId, String success, String serialize) {
    }

    public IdempotencyKey() {

    }
}
