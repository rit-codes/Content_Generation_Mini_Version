package com.MiniContentGenerator.ContentGenerator.dto;

import java.util.List;

public class AiResponse {
    /*
            entity.setContent(content);
            entity.setSummary(summary);
            entity.setKeywords(keywordsStr);
            entity.setGeneratedContent(jsonResponse);
            entity.setMeta(metaString);
            entity.setImageJSON(imageJSONString);
     */
    private String content;
    private String summary;
    private List<String> keywords;
    private Meta meta;
    private ImageJSON imageJSON;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ImageJSON getImageJSON() {
        return imageJSON;
    }

    public void setImageJSON(ImageJSON imageJSON) {
        this.imageJSON = imageJSON;
    }

    @Override
    public String toString() {
        return "AiResponse{" +
                "content='" + content + '\'' +
                ", summary='" + summary + '\'' +
                ", keywords=" + keywords +
                ", meta=" + meta +
                ", imageJSON=" + imageJSON +
                '}';
    }
}
