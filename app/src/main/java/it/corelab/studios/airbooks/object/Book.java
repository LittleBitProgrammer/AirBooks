package it.corelab.studios.airbooks.object;

import java.util.HashMap;


enum BookType{
    pdf("pdf"),
    epub("epub");

    private final String text;

    BookType(final String text){
        this.text = text;
    }

    @Override
    public String toString(){
        return text;
    }
}

public class Book extends Entity {

    private String ID;
    private String userID;
    private String title;
    private String description;
    private String genreID;
    private String language;
    private String uploadDate;
    private String coverUrl;
    private String bookUrl;
    private String authorFirstName;
    private String authorLastNAme;

    private int readings;
    private int lovers;
    private double avarageRating;

    private boolean isSaved;

    private Page<Review> reviews;
    private User author;

    @SuppressWarnings (value="unchecked")
    public Book(HashMap<String, Object> books){
        super(books);

        this.ID = books.get("id").toString();
        this.userID = books.get("user_id").toString();
        this.title = books.get("bookTitle").toString();
        this.description = books.get("description").toString();
        this.genreID = books.get("genre_id").toString();
        this.language = books.get("language").toString();
        this.uploadDate = books.get("upload_date").toString();
        this.coverUrl = books.get("cover_url").toString();
        this.bookUrl = books.get("book_url").toString();
        this.readings = (int) books.get("readings");
        this.lovers = (int) books.get("lovers");
        this.avarageRating = (double) books.get("average_rating");
        this.authorFirstName = books.get("author_first_name").toString();
        this.authorLastNAme = books.get("author_last_name").toString();
        //this.isSaved = (boolean) books.get("is_saved");

        if ((this.reviews = (Page<Review>) books.get("reviews")) != null){
            this.reviews = (Page<Review>) books.get("reviews");
        }

        if ((this.author = (User) books.get("bookAuthor")) != null){
            this.author = (User) books.get("bookAuthor");
        }
    }

    public String getID() {
        return ID;
    }

    public String getUserID() {
        return userID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenreID() {
        return genreID;
    }

    public String getLanguage() {
        return language;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastNAme() {
        return authorLastNAme;
    }

    public int getReadings() {
        return readings;
    }

    public int getLovers() {
        return lovers;
    }

    public double getAvarageRating() {
        return avarageRating;
    }

   /* public boolean getIsSaved() {
        return isSaved;
    }*/

    public Page<Review> getReviews() {
        return reviews;
    }

    public User getAuthor() {
        return author;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public void setAuthorLastNAme(String authorLastNAme) {
        this.authorLastNAme = authorLastNAme;
    }

    public void setReadings(int readings) {
        this.readings = readings;
    }

    public void setLovers(int lovers) {
        this.lovers = lovers;
    }

    public void setAvarageRating(double avarageRating) {
        this.avarageRating = avarageRating;
    }

   /* public void setSaved(boolean saved) {
        isSaved = saved;
    }*/

    public void setReviews(Page<Review> reviews) {
        this.reviews = reviews;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
