package com.personal.ledger_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.ledger_service.model.IdempotencyKey;
import com.personal.ledger_service.repository.IdempotencyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdempotencyService {

    private final IdempotencyRepository repository;
    private final ObjectMapper objectMapper;

    public IdempotencyService(IdempotencyRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public Optional<String> checkDuplicate(String key, String requestHash) {
        return repository.findByIdempotencyKey(key)
                .map(IdempotencyKey::getResponse);
    }

    public void saveResponse(String key, String requestHash, Object response) {
        try {
            IdempotencyKey entity = new IdempotencyKey();
            entity.setIdempotencyKey(key);
            entity.setRequestHash(requestHash);
            entity.setResponse(objectMapper.writeValueAsString(response));

            repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save idempotent response");
        }
    }
}

