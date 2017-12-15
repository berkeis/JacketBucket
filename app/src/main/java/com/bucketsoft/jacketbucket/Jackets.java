package com.bucketsoft.jacketbucket;

/**
 * Created by Isin on 12/15/2017.
 */


public class Jackets {

    private String text;
    private String name;
    private String photoUrl;

    public Jackets() {
    }

    public Jackets(String text, String name, String photoUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}