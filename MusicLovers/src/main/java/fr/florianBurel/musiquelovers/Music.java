package fr.florianBurel.musiquelovers;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by fl0 on 04/11/2013.
 */
public class Music {

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

    public static ArrayList<Music> getAllMusics()
    {
        InputStream inputStream = getDataFromURL(ITUNES_WEBSERVICE_URL);

        return musicsFromInputStream(inputStream);
        
    }

}
