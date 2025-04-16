package com.MiniContentGenerator.ContentGenerator.service;


import com.MiniContentGenerator.ContentGenerator.dto.PromptRequestDTO;
import com.MiniContentGenerator.ContentGenerator.dto.PromptResponseDTO;
import com.MiniContentGenerator.ContentGenerator.dto.TaxonomyResponse;
import com.MiniContentGenerator.ContentGenerator.utility.AckIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromptBuilderService {

    @Autowired
    TaxonomyService taxonomyService;

        public PromptResponseDTO buildPrompt(PromptRequestDTO promptRequest) {
        TaxonomyResponse taxonomyResponse = taxonomyService.getTaxonomyResponse(promptRequest.getTemplateName());
        String finalPrompt = getFinalPrompt(promptRequest,taxonomyResponse);
        PromptResponseDTO promptResponse = new PromptResponseDTO(AckIdGenerator.generateAckId(),finalPrompt,taxonomyResponse.getTone(),taxonomyResponse.getLength());
        System.out.println("Final Prompt: " + finalPrompt);
        return promptResponse;

    }

    private String getFinalPrompt(PromptRequestDTO request, TaxonomyResponse taxonomy){
        String prompt = "Write a " + taxonomy.getLength() + ", " + taxonomy.getTone() +
                " product description for '" + request.getTitle() +
                "'. Include features: " + request.getFeatures();
        return prompt;
    }
}
