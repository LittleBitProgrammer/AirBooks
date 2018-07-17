package it.corelab.studios.airbooks.object;


/**
 * Created by Roberto_Vecchio on 05/02/18.
 */

public class Item {

    private int drawable;
    private String name;
    private String author;
    private String genreName;
    private int numberReviews;
    private int numberOfReaders;
    private int numbersLovers;
    private int genreColor;
    private int vote;
    private String review;

    public Item(int drawable){
        this.drawable = drawable;
    }
    public Item(int drawable, int genreColor){
        this.drawable = drawable;
        this.genreColor = genreColor;
    }

    public Item(int drawable, String genreName){
        this.genreName = genreName;
        this.drawable = drawable;
    }

    public Item(String name, int drawable) {
        this.drawable = drawable;
        this.name = name;
    }

    public Item(String name, int drawable, int genreColor, String author){
        this.name = name;
        this.drawable = drawable;
        this.author = author;
        this.genreColor = genreColor;
    }
    public Item(String name, int drawable, int genreColor, String author, int numbersLovers, int numberOfReaders){
        this.name = name;
        this.drawable = drawable;
        this.author = author;
        this.genreColor = genreColor;
        this.numbersLovers = numbersLovers;
        this.numberOfReaders = numberOfReaders;
    }

    public Item(String name, int drawable, String author){
        this.name = name;
        this.drawable = drawable;
        this.author = author;
    }


    public Item(String name, int drawable, String author, int numberReviews){
        this.name = name;
        this.drawable = drawable;
        this.author = author;
        this.numberReviews = numberReviews;
    }
    public Item(String name, int drawable, int numberReviews, int vote,  String author){
        this.name = name;
        this.drawable = drawable;
        this.author = author;
        this.numberReviews = numberReviews;
        this.vote = vote;
    }

    public Item(String name, int drawable, String author, int numberReviews, int numberOfReaders, int numbersLovers, int genreColor){
        this.name = name;
        this.drawable = drawable;
        this.author = author;
        this.numberReviews = numberReviews;
        this.numberOfReaders = numberOfReaders;
        this.numbersLovers = numbersLovers;
        this.genreColor = genreColor;
    }

    public Item(String name, int drawable, String author, int numberOfReaders, int numbersLovers){
        this.name = name;
        this.drawable = drawable;
        this.author = author;
        this.numberOfReaders = numberOfReaders;
        this.numbersLovers = numbersLovers;
    }

    public Item(String name, int drawable, int vote, String author, String review, int genreColor){
        this.name = name;
        this.drawable = drawable;
        this.vote = vote;
        this.author = author;
        this.review = review;
        this.genreColor = genreColor;
    }

    public Item(String name){
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public String getName() {
        return name;
    }

    public String getAuthor(){
        return author;
    }

    public int getNumberReviews(){
        return numberReviews;
    }

    public int getNumberOfReaders(){
        return numberOfReaders;
    }

    public int getNumbersLovers(){
        return numbersLovers;
    }

    public int getGenreColor(){
        return genreColor;
    }

    public String getGenreName(){
        return genreName;
    }

    public int getVote(){
        return vote;
    }

    public String getReview(){
        return review;
    }

    public void setDrawable(int drawable){
        this.drawable = drawable;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setNumberReviews(int numberReviews){
        this.numberReviews = numberReviews;
    }

    public void setNumberOfReaders(int numberOfReaders){
        this.numberOfReaders = numberOfReaders;
    }

    public void setNumbersLovers(int numbersLovers){
        this.numbersLovers = numbersLovers;
    }

    public void setGenreColor(int genreColor){
        this.genreColor = genreColor;
    }

    public void setGenreName(String genreName){
        this.genreName = genreName;
    }

    public void setVote(int vote){
        this.vote = vote;
    }

    public void setReview(String review){
        this.review = review;
    }
}