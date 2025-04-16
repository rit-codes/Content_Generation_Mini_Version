package com.MiniContentGenerator.ContentGenerator.service;

import com.MiniContentGenerator.ContentGenerator.dto.AiResponse;
import com.MiniContentGenerator.ContentGenerator.dto.PromptRequestDTO;
import com.MiniContentGenerator.ContentGenerator.model.ContentEntity;
import com.MiniContentGenerator.ContentGenerator.repository.ContentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GptSimulatorService {

    @Autowired
    ContentRepository contentRepository;
    public ContentEntity simulateAndStoreGptResponse(String ackId, PromptRequestDTO request) {
        // Step 1: Simulate GPT response (hardcoded JSON)
        String jsonResponse =
                """
                {
                  "content": "Introducing our stylish denim jacket...",
                  "summary": "A fashionable, durable denim jacket...",
                  "keywords": ["denim", "jacket", "fashion", "style"],
                  "imageJSON": {
                    "imageUrl": "http://example.com/denim.png",
                    "altText": "Denim Jacket Image"
                  },
                  "meta": {
                    "length": "250 words",
                    "tone": "informative",
                    "author": "AI"
                  }
                }
                """;
        // Step 2: Parse to AiResponse
        ObjectMapper objectMapper = new ObjectMapper();
        ContentEntity entity = new ContentEntity();

        // Now instead of doing all the step that we are doing below, we will make more modular
        try{
            // i. Parse full response into your custom class
            AiResponse aiResponse = objectMapper.readValue(jsonResponse,AiResponse.class);
            entity.setAckId(ackId);
            entity.setEntityID(request.getTitle());         // or request.getEntityId() if you modify DTO
            entity.setTemplateName(request.getTemplateName());

            // ii. Extract and set values from parsed object
            entity.setContent(aiResponse.getContent());
            entity.setSummary(aiResponse.getSummary());

            // iii. Convert List<String> → CSV
            String keywordStr = String.join(", ", aiResponse.getKeywords());
            entity.setKeywords(keywordStr);

            // iv. Serialize meta + image as JSON and store as-is
            String metsString = objectMapper.writeValueAsString(aiResponse.getMeta());
            String imageString = objectMapper.writeValueAsString(aiResponse.getImageJSON());

            entity.setMeta(metsString);
            entity.setImageJSON(imageString);

            // v. Setting the time stamp
            entity.setCreatedTimestamp(LocalDateTime.now());


        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Step 3: Map to ContentEntity
        ContentEntity savedEntity = contentRepository.save(entity);
        System.out.println("Saved content with DB ID: "+ savedEntity.getId());
        // Step 4: Save to DB with ackID
        return savedEntity;
    }


}
