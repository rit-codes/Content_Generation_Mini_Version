package com.MiniContentGenerator.ContentGenerator.dto;

import java.util.List;
import java.util.Map;

public record ProductRequest(
        String entityId,
        String templateName,
        String concept,
        List<ImageRecord> images,
        Map<String, String> meta
) {}

