package it.corelab.studios.airbooks.data.model.HOME;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Showcase {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("bookTitle")
    @Expose
    private String title;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("background_image_url")
    @Expose
    private String backgroundImageUrl;
    @SerializedName("is_dark")
    @Expose
    private Boolean isDark;
    @SerializedName("language")
    @Expose
    private String language;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public Boolean getIsDark() {
        return isDark;
    }

    public void setIsDark(Boolean isDark) {
        this.isDark = isDark;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
