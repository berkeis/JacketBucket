package com.bucketsoft.jacketbucket;

import java.util.ArrayList;

/**
 * Created by Isin on 12/17/2017.
 */

public class Books {

    private String author;
    private String name;
    private String photoUrl;
    private String locations;    //TODO: Multiline spinner yaptıktan sonra ArrayList<String>'e çevir.


    public Books() {
    }

    public Books(String author, String name, String photoUrl, String locations) {
        this.author = author;
        this.name = name;
        this.photoUrl = photoUrl;
        this.locations = locations;
    }


    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
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

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }


}
