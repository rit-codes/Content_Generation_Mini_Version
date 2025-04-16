package com.MiniContentGenerator.ContentGenerator.repository;

import com.MiniContentGenerator.ContentGenerator.model.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContentRepository extends JpaRepository<ContentEntity, Long> {
    Optional<ContentEntity> findByAckId(String ackId);

}

