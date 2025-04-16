package com.MiniContentGenerator.ContentGenerator.repository;

import com.MiniContentGenerator.ContentGenerator.model.ContentEntity;
import com.MiniContentGenerator.ContentGenerator.model.TokenUsageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenUsageRepository extends JpaRepository<TokenUsageEntity,String> {
    Optional<TokenUsageEntity> findByAckId(String ackId);
}
