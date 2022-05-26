package com.ad4565.imagefinder.model_sql;

import javax.persistence.*;

@Entity
public class WebsiteData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String websiteTitle;
    @Lob
    private String imageUrls;

    public WebsiteData(){}
    public WebsiteData(String websiteTitle, String imageUrls) {
        this.websiteTitle = websiteTitle;
        this.imageUrls = imageUrls;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteTitle = websiteName;
    }

    public void setImages(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public long getId() {
        return id;
    }

    public String getWebsiteName() {
        return websiteTitle;
    }

    public String getImages() {
        return imageUrls;
    }

}
