package jsoup_helpers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 * This class serves as a helper to get all of unique links off of a website
 * Create object of this class passing in root url of the website
 * returns a Links object which has a urls field which is a set that contains all of the urls
 */

public class Links {

    private String rootUrl;
    private Set<String> urls;

    public Links(String rootUrl){
        this.rootUrl = rootUrl;
        this.urls = new HashSet<String>();
        this.urls.add(rootUrl);
    }

    public String[] getUrls(){
        return this.urls.toArray(new String[this.urls.size()]);
    }
    public void getLinks(String url)  {

        Set<String> uniqueURL = new HashSet<String>();
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements links = doc.select("a");

            if (links.isEmpty()) {
                return;
            }

            links.stream().map((link) -> link.attr("abs:href")).forEachOrdered((this_url) -> {
                boolean add = false;
                if(this_url.contains(this.rootUrl))
                    add = this.urls.add(this_url);
                if (add && this_url.contains(this.rootUrl)) {
                    System.out.println("Found: " + this_url);
                    getLinks(this_url);
                }
            });


        }
        catch(IOException exception){
            return;
        }
        catch(StackOverflowError error){
            return;
        }
    }


}
