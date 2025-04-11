package com.MiniContentGenerator.ContentGenerator.dto;

public class Meta {
    private String length;
    private String tone;
    private String author;

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "length='" + length + '\'' +
                ", tone='" + tone + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
