package it.corelab.studios.airbooks.object;

import java.util.Date;
import java.util.HashMap;

public class Review extends Entity {

    private String bookID;
    private String userID;
    private String title;
    private String description;
    private int rating;
    private Date date;

    private Book book;
    private User author;

    public Review(HashMap<String, Object> review){
        super(review);

        this.bookID = review.get("book_id").toString();
        this.userID = review.get("user_id").toString();
        this.title = review.get("bookTitle").toString();
        this.description = review.get("description").toString();
        this.rating = (int)review.get("rating");
        this.date = (Date) review.get("date");

        if ((this.book = (Book) review.get("book")) != null){
            this.book = (Book) review.get("book");
        }

        if ((this.author = (User) review.get("bookAuthor")) != null){
            this.author= (User) review.get("bookAuthor");
        }
    }

    public String getBookID() {
        return bookID;
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

    public int getRating() {
        return rating;
    }

    public Date getDate() {
        return date;
    }

    public Book getBook() {
        return book;
    }

    public User getAuthor() {
        return author;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
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

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
