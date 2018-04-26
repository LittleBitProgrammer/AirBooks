package it.corelab.airbooks.object;

import java.util.ArrayList;
import java.util.HashMap;

public class Feed extends Entity{

    private Page<Book> best;
    private Page<Book> reading;
    private ArrayList<Genre> genres;

    @SuppressWarnings (value="unchecked")
    public Feed(HashMap<String, Object> feed){
        super(feed);

        if ((this.best = (Page<Book>) feed.get("best")) != null){
            this.best = (Page<Book>) feed.get("best");
        }

        if ((this.reading = (Page<Book>) feed.get("reading")) != null){
            this.reading = (Page<Book>) feed.get("reading");
        }

        this.genres = new ArrayList<>();
    }

    public Page<Book> getBest() {
        return best;
    }

    public Page<Book> getReading() {
        return reading;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setBest(Page<Book> best) {
        this.best = best;
    }

    public void setReading(Page<Book> reading) {
        this.reading = reading;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }
}
