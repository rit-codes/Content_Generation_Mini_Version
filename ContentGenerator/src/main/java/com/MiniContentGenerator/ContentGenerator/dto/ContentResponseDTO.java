package com.MiniContentGenerator.ContentGenerator.dto;

public class ContentResponseDTO {
    private String content;
    private String summary;
    private String keywords;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
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
        return "ContentResponseDTO{" +
                "content='" + content + '\'' +
                ", summary='" + summary + '\'' +
                ", keywords='" + keywords + '\'' +
                ", meta=" + meta +
                ", imageJSON=" + imageJSON +
                '}';
    }
}
