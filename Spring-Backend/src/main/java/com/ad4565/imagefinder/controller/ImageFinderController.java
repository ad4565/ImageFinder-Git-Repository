package com.ad4565.imagefinder.controller;

import com.ad4565.imagefinder.controller.models.URL;
import com.ad4565.imagefinder.controller.models.Image;
import com.ad4565.imagefinder.model_sql.WebsiteData;
import com.ad4565.imagefinder.repository.WebsiteDataRepository;
import com.ad4565.imagefinder.jsoup_helpers.Images;
import com.ad4565.imagefinder.jsoup_helpers.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("api/")
@CrossOrigin("http://localhost:3000")
public class ImageFinderController {

    @Autowired
    WebsiteDataRepository websiteDataRepository;

    @PostMapping("images")
    public ArrayList<Image> getImages(@RequestBody URL url) throws IOException {
        //initial page information
        Document doc = Jsoup.connect(url.getURL()).get();


        //check current url just in case of redirect by website
        String documentUrl = doc.location();

        System.out.println("Searching " + documentUrl + " for links.");
        Links links = new Links(documentUrl);

        links.getLinks(documentUrl);
        String[] urls = links.getUrls();

        System.out.println("Searching " + documentUrl + "for Photos.");
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        Images[] threads = new Images[numberOfThreads];

        //Start the threads
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Images(i, numberOfThreads, urls, url.getURL());
            threads[i].start();

        }
        //Make sure threads join
        for(int thread = 0; thread < threads.length; thread ++ ){
            try {
                threads[thread].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //Extract data from threads
        ArrayList<Image> imagesUrls = new ArrayList<Image>();
        for(int thread = 0; thread < threads.length; thread++){

            //Extract images
            String[] images = threads[thread].getImages();
            for(int i = 0; i < images.length; i ++){
                imagesUrls.add(new Image(images[i], doc.title()));
            }

        }

        return imagesUrls;
    }

    @PostMapping("save-image")
    public ResponseEntity saveImage(@RequestBody Image image ){
        System.out.println("Testing save");


        String websiteTitle = image.getTitleOfHome();
        String url = image.getUrl();
        System.out.println(websiteTitle);
        try {
            Optional<WebsiteData> websiteData = websiteDataRepository.findByWebsiteTitle(websiteTitle);
            if(websiteData.isPresent()){

                if(!websiteData.get().getImagesUrls().contains(url)) {
                    System.out.println("Saved: " + url + " into " + websiteTitle);
                    String urls = websiteData.get().getImagesUrls();
                    websiteData.get().setImages(urls + image.getUrl() + ",");
                    websiteDataRepository.save(websiteData.get());
                }

                else{
                    System.out.println("Image already saved in DB!");
                }

            }

            else{

             WebsiteData newWebsiteData = new WebsiteData(websiteTitle, url + ",");
             websiteDataRepository.save(newWebsiteData);
             System.out.println("Created New Website in DB with title: " + websiteTitle + " containing image " + url);
            }
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception exception){
            System.out.println(exception);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("get-images")
    public ResponseEntity<ArrayList<WebsiteData>> getImages(){

        System.out.println("here");
        ArrayList<WebsiteData> data = new ArrayList<>();
        for(WebsiteData websiteData: websiteDataRepository.findAll()){
            data.add(websiteData);
        }

        return new ResponseEntity<>(data, HttpStatus.OK);
    }




}
