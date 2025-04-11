package com.MiniContentGenerator.ContentGenerator.dto;

public class ImageJSON{

    private String imageUrl;
    private String altText;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    @Override
    public String toString() {
        return "ImageJSON{" +
                "imageUrl='" + imageUrl + '\'' +
                ", altText='" + altText + '\'' +
                '}';
    }
}

