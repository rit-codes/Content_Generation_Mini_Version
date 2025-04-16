package com.MiniContentGenerator.ContentGenerator.controller;

import com.MiniContentGenerator.ContentGenerator.dto.ContentResponseDTO;
import com.MiniContentGenerator.ContentGenerator.dto.ProductRequest;
import com.MiniContentGenerator.ContentGenerator.dto.PromptRequestDTO;
import com.MiniContentGenerator.ContentGenerator.model.ContentEntity;
import com.MiniContentGenerator.ContentGenerator.model.TokenUsageEntity;
import com.MiniContentGenerator.ContentGenerator.service.ContentService;
import com.MiniContentGenerator.ContentGenerator.service.TokenUsageService;
import com.MiniContentGenerator.ContentGenerator.service.WebhookPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/content")
public class ContentController {

    // content service instance
    @Autowired
    ContentService contentService;
    @Autowired
    TokenUsageService tokenUsageService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateContent(@RequestBody ProductRequest request) {
        // Call ContentService
        String content = contentService.generateContent(request);
        return ResponseEntity.ok(content);
    }
    @GetMapping("/{id}")
    public String getContentById(@PathVariable Long id){
        return contentService.getContentById(id);
    }
    @GetMapping("/all")
    public List<ContentEntity> getAllContent(){
        return contentService.getAllContent();
    }
    @GetMapping("/detailed/{id}")
    public ContentResponseDTO getContentDetailsById(@PathVariable Long id){
        return contentService.getContentDetailsById(id);
    }
    @GetMapping("/ack/{ackId}")
    public ContentResponseDTO getContentByAck(@PathVariable String ackId) {
    return contentService.getContentByAckId(ackId);
    }
    @GetMapping("/tokens/{ackId}")
    public ResponseEntity<TokenUsageEntity> getTokenUsageByAckId(@PathVariable String ackId) {
        Optional<TokenUsageEntity> tokenData = tokenUsageService.getByAckId(ackId);

        if (tokenData.isPresent()) {
            TokenUsageEntity data = tokenData.get();
            return ResponseEntity.ok(data); // returns 200 OK + data
        } else {
            return ResponseEntity.notFound().build(); // returns 404
        }
    }

    @PostMapping("/generate/final")
    public ResponseEntity<String> generateFinalContent(@RequestBody PromptRequestDTO request) {
        String response = contentService.generateContentFromPrompt(request);
        return ResponseEntity.ok(response);
    }



}
