package com.example.textextractionapp;

public class ImageTextExtraction {
    private String path;
    private String name;
    private String text;

    public ImageTextExtraction(String path, String name, String text) {
        this.path = path;
        this.name = name;
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
