package com.MiniContentGenerator.ContentGenerator.dto;

public class PromptRequestDTO {

    private String templateName;
    private String title;
    private String features;


    public PromptRequestDTO(String templateName, String title, String features) {
        this.templateName = templateName;
        this.title = title;
        this.features = features;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "PromptRequestDTO{" +
                "templateName='" + templateName + '\'' +
                ", title='" + title + '\'' +
                ", features='" + features + '\'' +
                '}';
    }
}
