package it.corelab.studios.airbooks.model.data.ADD.BOOK;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PostAddBook {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("genre_id")
    @Expose
    private Integer genreId;
    @SerializedName("tags")
    @Expose
    private ArrayList<String> tags;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("cover_data")
    @Expose
    private String coverData;
    @SerializedName("book_data")
    @Expose
    private String bookData;
    @SerializedName("book_format")
    @Expose
    private String bookFormat;

    public PostAddBook(String title, String description, int genreId, ArrayList<String> tags, String language, String coverData, String bookData, String bookFormat){
        this.title = title;
        this.description = description;
        this.genreId = genreId;
        this.tags = tags;
        this.language = language;
        this.coverData = coverData;
        this.bookData = bookData;
        this.bookFormat = bookFormat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCoverData() {
        return coverData;
    }

    public void setCoverData(String coverData) {
        this.coverData = coverData;
    }

    public String getBookData() {
        return bookData;
    }

    public void setBookData(String bookData) {
        this.bookData = bookData;
    }

    public String getBookFormat() {
        return bookFormat;
    }

    public void setBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
    }
}

