package com.MiniContentGenerator.ContentGenerator.model;

import jakarta.persistence.*;

@Entity
public class ContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String entityID;
    private String content;
    private String templateName;
    private String concept;

    @Lob
    private String meta;
    @Lob
    private String imageJSON;
    @Lob
    private String generatedContent;

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

    @Override
    public String toString() {
        return "ContentEntity{" +
                "id=" + id +
                ", entityID='" + entityID + '\'' +
                ", content='" + content + '\'' +
                ", templateName='" + templateName + '\'' +
                ", concept='" + concept + '\'' +
                ", meta='" + meta + '\'' +
                ", imageJSON='" + imageJSON + '\'' +
                ", generatedContent='" + generatedContent + '\'' +
                '}';
    }
}
