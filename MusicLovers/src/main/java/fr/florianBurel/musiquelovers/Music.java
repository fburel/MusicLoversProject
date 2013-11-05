package fr.florianBurel.musiquelovers;

import com.androidquery.util.XmlDom;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by fl0 on 04/11/2013.
 */
public class Music {

    private static final String ITUNES_WEBSERVICE_URL = "https://itunes.apple.com/fr/rss/topsongs/limit=50/explicit=true/xml";

    private String name;
    private String author;
    private String category;
    private String description;

    public Music() {
        this.name = "For Whom the Bell Tolls";
        this.author = "Metallica";
        this.category = "Metal";
        this.description = "Ride the lightening!!!!";
        this.liked = true;
    }

    public Music(String name, String author, String category, String description) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.description = description;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    private boolean liked;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Music{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", liked=" + liked +
                '}';
    }

    public static ArrayList<Music> getAllMusics() throws IOException, SAXException {

        InputStream inputStream = getDataFromURL(ITUNES_WEBSERVICE_URL);

        return musicsFromInputStream(inputStream);

    }

    private static ArrayList<Music> musicsFromInputStream(InputStream inputStream) throws SAXException {

        ArrayList<Music> arrayList = new ArrayList<Music>();

        XmlDom xmlDom = new XmlDom(inputStream);

        for(XmlDom entry : xmlDom.children("entry"))
        {
            String title = entry.text("im:name");
            String artist = entry.text("im:artist");
            String description = entry.text("rights");
            String category = entry.tag("category").attr("term");

            arrayList.add(new Music(title, artist, category, description));
        }

        return arrayList;
    }

    private static InputStream getDataFromURL(String url) throws IOException {

        HttpClient client = new DefaultHttpClient();
        HttpUriRequest request = new HttpGet(url);

        HttpResponse response = client.execute(request);

        return response.getEntity().getContent();
    }



}
