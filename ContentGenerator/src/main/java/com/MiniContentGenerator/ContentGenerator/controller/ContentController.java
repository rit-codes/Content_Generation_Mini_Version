package com.MiniContentGenerator.ContentGenerator.controller;

import com.MiniContentGenerator.ContentGenerator.dto.ContentResponseDTO;
import com.MiniContentGenerator.ContentGenerator.dto.ProductRequest;
import com.MiniContentGenerator.ContentGenerator.model.ContentEntity;
import com.MiniContentGenerator.ContentGenerator.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/content")
public class ContentController {

    // content service instance
    @Autowired
    ContentService contentService;

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

}
