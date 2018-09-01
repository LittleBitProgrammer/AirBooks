package it.corelab.studios.airbooks.model.data.USER.ACTIVE;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import it.corelab.studios.airbooks.model.data.HOME.Genre;

public class BooksReadItem {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("genre_id")
    @Expose
    private Integer genreId;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("upload_date")
    @Expose
    private String uploadDate;
    @SerializedName("cover_url")
    @Expose
    private String coverUrl;
    @SerializedName("book_url")
    @Expose
    private String bookUrl;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("author_first_name")
    @Expose
    private String authorFirstName;
    @SerializedName("author_last_name")
    @Expose
    private String authorLastName;
    @SerializedName("reviews_count")
    @Expose
    private Integer reviewsCount;
    @SerializedName("readings")
    @Expose
    private Integer readings;
    @SerializedName("average_rating")
    @Expose
    private Float averageRating;
    @SerializedName("lovers")
    @Expose
    private Integer lovers;
    @SerializedName("is_saved")
    @Expose
    private Boolean isSaved;
    @SerializedName("genre")
    @Expose
    private Genre genre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public Integer getReadings() {
        return readings;
    }

    public void setReadings(Integer readings) {
        this.readings = readings;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getLovers() {
        return lovers;
    }

    public void setLovers(Integer lovers) {
        this.lovers = lovers;
    }

    public Boolean getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(Boolean isSaved) {
        this.isSaved = isSaved;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}
