package com.example.exchangelibrary;

public class Upload {
    String name;
    String imageUrl;

    public Upload(String name, String imageUrl) {
        if(name.trim().equals("")){
            name = "No name";
        }
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Upload() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
