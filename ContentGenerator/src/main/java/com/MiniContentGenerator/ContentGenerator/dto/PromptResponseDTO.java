package com.MiniContentGenerator.ContentGenerator.dto;

public class PromptResponseDTO {
    private String ackID;
    private String finalPrompt;
    private String tone;
    private String length;

    public PromptResponseDTO(String ackID, String finalPrompt, String tone, String length) {
        this.ackID = ackID;
        this.finalPrompt = finalPrompt;
        this.tone = tone;
        this.length = length;
    }

    public String getAckID() {
        return ackID;
    }

    public void setAckID(String ackID) {
        this.ackID = ackID;
    }

    public String getFinalPrompt() {
        return finalPrompt;
    }

    public void setFinalPrompt(String finalPrompt) {
        this.finalPrompt = finalPrompt;
    }

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

    @Override
    public String toString() {
        return "PromptResponseDTO{" +
                "ackID='" + ackID + '\'' +
                ", finalPrompt='" + finalPrompt + '\'' +
                ", tone='" + tone + '\'' +
                ", length='" + length + '\'' +
                '}';
    }
}
