package com.MiniContentGenerator.ContentGenerator.service;

import com.MiniContentGenerator.ContentGenerator.dto.*;
import com.MiniContentGenerator.ContentGenerator.model.ContentEntity;
import com.MiniContentGenerator.ContentGenerator.repository.ContentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    // Note: Highlight here I need to save the entity which is created and filled into DB
    // so we need instance of ContentRepository, and hence we need Autowire
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private PromptBuilderService promptBuilderService;
    @Autowired
    private GptSimulatorService gptSimulatorService;

    public String generateContent(ProductRequest request) {

        // feeding the request received from api (ProductRequest request and saving it into entity,
        // now this entity will be saved into db
        ContentEntity entity = new ContentEntity();
        entity.setEntityID(request.entityId());
        entity.setContent(request.concept());
        entity.setTemplateName(request.templateName());

        // here jsonResponse is being hard-coded to mimic the response that we wil be getting from GPT
        // TODO: Replace jsonResponse with actual OpenAI integration later.
        // NOTE: We’re simulating this until GPT is integrated

        String jsonResponse = """
        {
          "content": "This is the generated content",
          "summary": "Quick summary",
          "keywords": ["fashion", "style"],
          "meta": {
            "length": "250 words",
            "tone": "informative",
            "author": "AI"
          },
          "imageJSON": {
            "imageUrl": "http://example.com/image.png",
            "altText": "A fashion image"
          }
        }
        """;

        // now with the help of ObjectMapper, we will parse this jsonResponse
        ObjectMapper objectMapper = new ObjectMapper();
        // Now instead of doing all the step that we are doing below, we will make more modular
        try{
            // 1. Parse full response into your custom class
            AiResponse aiResponse = objectMapper.readValue(jsonResponse,AiResponse.class);

            // 2. Extract and set values from parsed object
            entity.setContent(aiResponse.getContent());
            entity.setSummary(aiResponse.getSummary());

            // 3. Convert List<String> → CSV
            String keywordStr = String.join(", ", aiResponse.getKeywords());
            entity.setKeywords(keywordStr);

            // 4. Serialize meta + image as JSON and store as-is
            String metsString = objectMapper.writeValueAsString(aiResponse.getMeta());
            String imageString = objectMapper.writeValueAsString(aiResponse.getImageJSON());

            entity.setMeta(metsString);
            entity.setImageJSON(imageString);

            // 5. Setting the time stamp
            entity.setCreatedTimestamp(LocalDateTime.now());





        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ContentEntity savedEntity = contentRepository.save(entity);
        return "Saved content with DB ID: " + savedEntity.getId();
    }

    public ContentResponseDTO getContentDetailsById(Long id){
        Optional<ContentEntity> optionalContentEntity = contentRepository.findById(id);
        if (optionalContentEntity.isPresent()) {
            ContentEntity entity = optionalContentEntity.get();
            ObjectMapper jsonResponseMapper = new ObjectMapper();
            try {
                Meta metaObject = jsonResponseMapper.readValue(entity.getMeta(), Meta.class);
                ImageJSON imageObject = jsonResponseMapper.readValue(entity.getImageJSON(), ImageJSON.class);
                ContentResponseDTO contentResponseDTO = new ContentResponseDTO();
                contentResponseDTO.setContent(entity.getContent());
                contentResponseDTO.setSummary(entity.getSummary());
                contentResponseDTO.setKeywords(entity.getKeywords());
                contentResponseDTO.setMeta(metaObject);
                contentResponseDTO.setImageJSON(imageObject);
                return contentResponseDTO;
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to parse JSON fields", e);
            }
        }
        else{
           return null;
        }
    }

    public ContentResponseDTO getContentByAckId(String ackId){
        Optional<ContentEntity> optionalContentEntity = contentRepository.findByAckId(ackId);
        if (optionalContentEntity.isPresent()) {
            ContentEntity entity = optionalContentEntity.get();
            ObjectMapper jsonResponseMapper = new ObjectMapper();
            try {
                Meta metaObject = jsonResponseMapper.readValue(entity.getMeta(), Meta.class);
                ImageJSON imageObject = jsonResponseMapper.readValue(entity.getImageJSON(), ImageJSON.class);
                ContentResponseDTO contentResponseDTO = new ContentResponseDTO();
                contentResponseDTO.setContent(entity.getContent());
                contentResponseDTO.setSummary(entity.getSummary());
                contentResponseDTO.setKeywords(entity.getKeywords());
                contentResponseDTO.setMeta(metaObject);
                contentResponseDTO.setImageJSON(imageObject);
                return contentResponseDTO;
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to parse JSON fields", e);
            }
        }
        else{
            return null;
        }
    }

    public String getContentById(Long id){
        Optional<ContentEntity> optionalContentEntity = contentRepository.findById(id);
        if (optionalContentEntity.isPresent()) {
            ContentEntity entity = optionalContentEntity.get();
            return "Fetched content for entity: " + entity.getEntityID();
        }
        else{
            return "Entity not found for ID: " + id;
        }
    }

    public String generateContentFromPrompt(PromptRequestDTO request) {
        PromptResponseDTO prompt = promptBuilderService.buildPrompt(request);
        ContentEntity savedEntity = gptSimulatorService.simulateAndStoreGptResponse(prompt.getAckID(), request);
        return "Content saved with AckID: " + savedEntity.getAckId();
    }


    public List<ContentEntity> getAllContent(){
        return contentRepository.findAll();
    }

}






