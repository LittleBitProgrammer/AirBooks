package it.corelab.airbooks.object;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class User extends Entity {

    private String ID;
    private String firstName;
    private String lastName;
    private String profilePictureUrl;
    private String nationality;
    private Date subscriptionDate;
    private String email;

    private int follower;

    private boolean isFollowed;

    private ArrayList<Genre> favouriteGenres = new ArrayList<>();

    private Page<Review> reviews;
    private Page<Book> booksWritten;
    private Page<Book> booksReading;
    private Page<Book> booksRead;
    private Page<Book> booksSaved;

    @SuppressWarnings (value="unchecked")
    public User(HashMap<String, Object> user){
        super(user);

        this.ID = user.get("id").toString();
        this.firstName = user.get("first_name").toString();
        this.lastName = user.get("last_name").toString();
        this.profilePictureUrl = user.get("profile_picture_url").toString();
        this.nationality = user.get("nationality").toString();
        this.subscriptionDate = (Date) user.get("subscription_date");
        this.email = user.get("email").toString();
        this.follower = (int) user.get("followers_count");
        this.isFollowed = (boolean) user.get("is_followed");

        if ((this.favouriteGenres = (ArrayList<Genre>) user.get("favourite_genres")) != null) {
            this.favouriteGenres = (ArrayList<Genre>) user.get("favourite_genres");
            for (Genre itm : favouriteGenres) {
                this.favouriteGenres.add(itm);
            }
        }

        if ((this.reviews = (Page<Review>) user.get("reviews")) != null){
            this.reviews = (Page<Review>) user.get("reviews");
        }

        if ((this.booksWritten = (Page<Book>) user.get("written")) != null){
            this.booksWritten = (Page<Book>) user.get("written");
        }

        if ((this.booksReading = (Page<Book>) user.get("reading")) != null){
            this.booksReading = (Page<Book>) user.get("reading");
        }

        if ((this.booksRead = (Page<Book>) user.get("read")) != null){
            this.booksRead = (Page<Book>) user.get("read");
        }

        if ((this.booksSaved = (Page<Book>) user.get("saved")) != null){
            this.booksSaved = (Page<Book>) user.get("saved");
        }
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public String getNationality() {
        return nationality;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public String getEmail() {
        return email;
    }

    public int getFollower() {
        return follower;
    }

    public boolean getIsFollowed(){
        return isFollowed;
    }

    public Page<Review> getReviews() {
        return reviews;
    }

    public Page<Book> getBooksWritten() {
        return booksWritten;
    }

    public Page<Book> getBooksReading() {
        return booksReading;
    }

    public Page<Book> getBooksRead() {
        return booksRead;
    }

    public Page<Book> getBooksSaved() {
        return booksSaved;
    }

    public ArrayList<Genre> getFavouriteGenres() {
        return favouriteGenres;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }

    public void setReviews(Page<Review> reviews) {
        this.reviews = reviews;
    }

    public void setBooksWritten(Page<Book> booksWritten) {
        this.booksWritten = booksWritten;
    }

    public void setBooksReading(Page<Book> booksReading) {
        this.booksReading = booksReading;
    }

    public void setBooksRead(Page<Book> booksRead) {
        this.booksRead = booksRead;
    }

    public void setBooksSaved(Page<Book> booksSaved) {
        this.booksSaved = booksSaved;
    }

    public void setFavouriteGenres(ArrayList<Genre> favouriteGenres) {
        this.favouriteGenres = favouriteGenres;
    }
}
