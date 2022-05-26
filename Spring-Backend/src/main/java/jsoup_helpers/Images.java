package jsoup_helpers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Images extends Thread {


    private int thread;
    private int totalThreads;



    private String[] urls;
    private Set<String> images;


    public Images(int thread, int totalThreads, String[] urls){
        this.totalThreads = totalThreads;
        this.thread = thread;
        this.images = new HashSet<String>();
        this.urls = urls;
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
                        if (!image.attr("src").equals("")) {
                            boolean add = this.images.add(image.attr("src"));
                            if (add)
                                System.out.println("Thread " + this.thread + " Found: " + image.attr("src"));
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
                        if (!image.attr("src").equals("")) {
                            boolean add = this.images.add(image.attr("src"));
                            if (add)
                                System.out.println("Thread " + this.thread + "Found: " + image.attr("src"));
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
