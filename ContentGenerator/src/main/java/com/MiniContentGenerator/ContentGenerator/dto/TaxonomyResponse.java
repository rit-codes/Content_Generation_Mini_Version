package com.MiniContentGenerator.ContentGenerator.dto;

public class TaxonomyResponse {
    private String tone;
    private String length;
    private String author;

    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "TaxonomyResponse{" +
                "tone='" + tone + '\'' +
                ", length='" + length + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
