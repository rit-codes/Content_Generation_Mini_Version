package com.MiniContentGenerator.ContentGenerator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TokenUsageEntity {
    @Id
    String ackId; // to be used from the ackId of prompt request generated
    int promptTokens;
    int completionTokens;
    int totalTokens;

    public String getAckId() {
        return ackId;
    }

    public void setAckId(String ackId) {
        this.ackId = ackId;
    }

    public int getPromptTokens() {
        return promptTokens;
    }

    public void setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
    }

    public int getCompletionTokens() {
        return completionTokens;
    }

    public void setCompletionTokens(int completionTokens) {
        this.completionTokens = completionTokens;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
    }

    @Override
    public String toString() {
        return "TokenUsageEntity{" +
                "ackId='" + ackId + '\'' +
                ", promptTokens=" + promptTokens +
                ", completionTokens=" + completionTokens +
                ", totalTokens=" + totalTokens +
                '}';
    }
}
