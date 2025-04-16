package com.MiniContentGenerator.ContentGenerator.service;

import com.MiniContentGenerator.ContentGenerator.dto.PromptResponseDTO;
import com.MiniContentGenerator.ContentGenerator.model.TokenUsageEntity;
import com.MiniContentGenerator.ContentGenerator.repository.TokenUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenUsageService {

    @Autowired
    TokenUsageRepository tokenUsageRepository;


    public void saveTokenUsage(String ackID, int promptTokens, int completionTokens){
        TokenUsageEntity tokenUsageEntity = new TokenUsageEntity();
        int totalTokens = promptTokens + completionTokens;
        tokenUsageEntity.setPromptTokens(promptTokens);
        tokenUsageEntity.setCompletionTokens(completionTokens);
        tokenUsageEntity.setTotalTokens(totalTokens);
        tokenUsageEntity.setAckId(ackID);
        System.out.println("Token saved as: " + tokenUsageEntity);
        tokenUsageRepository.save(tokenUsageEntity);

    }

    public Optional<TokenUsageEntity> getByAckId(String ackId) {
        return tokenUsageRepository.findByAckId(ackId);
    }


}
