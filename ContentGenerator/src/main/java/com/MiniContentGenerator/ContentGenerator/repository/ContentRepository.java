package com.MiniContentGenerator.ContentGenerator.repository;

import com.MiniContentGenerator.ContentGenerator.model.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<ContentEntity, Long> {

}

