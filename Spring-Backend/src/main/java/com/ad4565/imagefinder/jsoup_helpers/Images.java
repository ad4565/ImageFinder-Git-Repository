package com.ad4565.imagefinder.jsoup_helpers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 * This class serves as a helper to jsoup by going through all of the links it is given
 * and searching for images on each of the links. Multi-threaded, all links stored in "images" and get be retrieved
 * after running the run method and using the getter
 */
public class Images extends Thread {


    private int thread;
    private int totalThreads;



    private String[] urls;
    private Set<String> images;
    private String originalUrl;

    public Images(int thread, int totalThreads, String[] urls, String originalUrl){
        this.totalThreads = totalThreads;
        this.thread = thread;
        this.images = new HashSet<String>();
        this.urls = urls;
        this.originalUrl = originalUrl;
    }
    @Override
    public void run() {
        //Amount of work each thread has to do
        int work =  (int) Math.floor(urls.length/totalThreads);


        //If cases are to account for different amount of threads on each machine
        //and the work not splitting evenly, there has to be a better way to do this maybe?
        if(thread != (totalThreads - 1)) {
            Document doc;
            for (int i = (work * thread); i < ((work * thread) + work); i++) {
                System.out.println("Searching for photos from: " + urls[i]);
                try {
                    doc = Jsoup.connect(urls[i]).userAgent("Mozilla").get();


                    String documentUrl = doc.location();
                    for (Element image : doc.select("img")) {
                        //Checks for duplicates or empty sources
                        if (!image.attr("src").equals("") && !images.contains(image.attr("src"))) {
                            boolean add;
                            //Hanles case where the photo is located on the website
                            if(!image.attr("src").contains(doc.location()) && !image.attr("src").contains("https://")  && !image.attr("src").contains("www.")){
                                String imageUrl = originalUrl + image.attr("src");
                                add = this.images.add(imageUrl);
                                if (add)
                                    System.out.println("Thread " + this.thread + " Found: " + imageUrl);
                            }
                            else{
                                add = this.images.add(image.attr("src"));
                                if (add)
                                    System.out.println("Thread " + this.thread + " Found: " + image.attr("src") );

                            }


                        }
                    }
                }
                catch(IOException error){
                    System.out.println("Invalid Link");
                }


            }
        }
        //This is the case for the last thread (thread = totalThreads - 1)
        else{
            Document doc;
            for(int i = (work * thread); i < urls.length; i ++){
                System.out.println("Searching for photos from: " + urls[i]);
                try {
                    doc = Jsoup.connect(urls[i]).userAgent("Mozilla").get();


                    String documentUrl = doc.location();
                    for (Element image : doc.select("img")) {
                        //Checks for duplicates or empty sources
                        if (!image.attr("src").equals("") && !images.contains(image.attr("src"))) {
                            boolean add;
                            //Handles case where the photo is located on the website
                            if(!image.attr("src").contains(doc.location()) && !image.attr("src").contains("https://") && !image.attr("src").contains("www.")) {
                                String imageUrl = originalUrl + image.attr("src" );
                                add = this.images.add(imageUrl);
                                if (add)
                                    System.out.println("Thread " + this.thread + " Found: " + imageUrl);
                            }
                            else{
                                add = this.images.add(image.attr("src"));
                                if (add)
                                    System.out.println("Thread " + this.thread + " Found: " + image.attr("src") );

                            }


                        }
                    }
                }
                catch(IOException error){
                    System.out.println("Invalid Link");
                }

            }
        }

    }

    public String[] getImages(){
        return this.images.toArray(new String[this.images.size()]);
    }


}
