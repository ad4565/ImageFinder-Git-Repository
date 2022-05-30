package com.ad4565.imagefinder.controller.models;

/**
 * This data model is the model in which images will be sent back to the front end
 * and stored in the database
 */

public class Image {

    private String url;
    private String titleOfHome;


    public Image(String url, String titleOfHome) {
        this.url = url;
        this.titleOfHome = titleOfHome;
    }

    public String getUrl() {
        return this.url;
    }

    public String getTitleOfHome(){
        return this.titleOfHome;
    }

    public void setTitleOfHome(String titleOfHome) {
        this.titleOfHome = titleOfHome;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
