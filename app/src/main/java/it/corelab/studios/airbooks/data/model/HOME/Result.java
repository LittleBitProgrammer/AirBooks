package it.corelab.studios.airbooks.data.model.HOME;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("showcase")
    @Expose
    private List<Showcase> showcase = null;
    @SerializedName("best")
    @Expose
    private Best best;
    @SerializedName("reading")
    @Expose
    private Reading reading;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;

    public List<Showcase> getShowcase() {
        return showcase;
    }

    public void setShowcase(List<Showcase> showcase) {
        this.showcase = showcase;
    }

    public Best getBest() {
        return best;
    }

    public void setBest(Best best) {
        this.best = best;
    }

    public Reading getReading() {
        return reading;
    }

    public void setReading(Reading reading) {
        this.reading = reading;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

}