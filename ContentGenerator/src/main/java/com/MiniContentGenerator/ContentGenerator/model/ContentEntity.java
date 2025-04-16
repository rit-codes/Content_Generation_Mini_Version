package com.MiniContentGenerator.ContentGenerator.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String entityID;
    private String content;
    private String templateName;
    private String concept;
    private String ackId;


    @Lob
    private String meta;
    @Lob
    private String imageJSON;
    @Lob
    private String generatedContent;
    @Lob
    private String summary;
    @Lob
    private String keywords;  // will store comma-separated

    @Column(name = "created_timestamp", nullable = false)
    private LocalDateTime createdTimestamp;

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityID() {
        return entityID;
    }

    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getImageJSON() {
        return imageJSON;
    }

    public void setImageJSON(String imageJSON) {
        this.imageJSON = imageJSON;
    }

    public String getGeneratedContent() {
        return generatedContent;
    }

    public void setGeneratedContent(String generatedContent) {
        this.generatedContent = generatedContent;
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

    public String getAckId() {
        return ackId;
    }

    public void setAckId(String ackId) {
        this.ackId = ackId;
    }

    @Override
    public String toString() {
        return "ContentEntity{" +
                "id=" + id +
                ", entityID='" + entityID + '\'' +
                ", content='" + content + '\'' +
                ", templateName='" + templateName + '\'' +
                ", concept='" + concept + '\'' +
                ", ackId='" + ackId + '\'' +
                ", meta='" + meta + '\'' +
                ", imageJSON='" + imageJSON + '\'' +
                ", generatedContent='" + generatedContent + '\'' +
                ", summary='" + summary + '\'' +
                ", keywords='" + keywords + '\'' +
                ", createdTimestamp=" + createdTimestamp +
                '}';
    }
}
