package it.corelab.studios.airbooks.model.data.USER.ACTIVE;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("profile_picture_url")
    @Expose
    private String profilePictureUrl;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("subscription_date")
    @Expose
    private String subscriptionDate;
    @SerializedName("followers_count")
    @Expose
    private Integer followersCount;
    @SerializedName("favourite_genres")
    @Expose
    private List<FavouriteGenre> favouriteGenres = null;
    @SerializedName("is_followed")
    @Expose
    private Boolean isFollowed;
    @SerializedName("reviews")
    @Expose
    private Reviews reviews;
    @SerializedName("books_written")
    @Expose
    private BooksWritten booksWritten;
    @SerializedName("books_read")
    @Expose
    private BooksRead booksRead;
    @SerializedName("books_saved")
    @Expose
    private BooksSaved booksSaved;
    @SerializedName("notifications_count")
    @Expose
    private Integer notificationsCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public List<FavouriteGenre> getFavouriteGenres() {
        return favouriteGenres;
    }

    public void setFavouriteGenres(List<FavouriteGenre> favouriteGenres) {
        this.favouriteGenres = favouriteGenres;
    }

    public Boolean getIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(Boolean isFollowed) {
        this.isFollowed = isFollowed;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    public BooksWritten getBooksWritten() {
        return booksWritten;
    }

    public void setBooksWritten(BooksWritten booksWritten) {
        this.booksWritten = booksWritten;
    }

    public BooksRead getBooksRead() {
        return booksRead;
    }

    public void setBooksRead(BooksRead booksRead) {
        this.booksRead = booksRead;
    }

    public BooksSaved getBooksSaved() {
        return booksSaved;
    }

    public void setBooksSaved(BooksSaved booksSaved) {
        this.booksSaved = booksSaved;
    }

    public Integer getNotificationsCount() {
        return notificationsCount;
    }

    public void setNotificationsCount(Integer notificationsCount) {
        this.notificationsCount = notificationsCount;
    }

}
