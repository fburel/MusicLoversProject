package fr.florianBurel.musiquelovers;

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
}
