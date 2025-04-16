package com.MiniContentGenerator.ContentGenerator.service;

import com.MiniContentGenerator.ContentGenerator.dto.ContentResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Service
public class WebhookPushService {
    private static final String WEBHOOK_URL = "https://httpbin.org/post";

    public void sendToWebhook(ContentResponseDTO responseDTO){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ContentResponseDTO> requestEntity = new HttpEntity<> (responseDTO,headers);
        ResponseEntity<String> response = restTemplate.postForEntity(WEBHOOK_URL, requestEntity, String.class);

        System.out.println("Webhook Response Status: " + response.getStatusCode());
        System.out.println("Webhook Response Body: " + response.getBody());
    }
}
