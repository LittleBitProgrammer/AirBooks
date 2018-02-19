package it.corelab.airbooks.object;

/**
 * Created by Roberto_Vecchio on 05/02/18.
 */

public class Item {

    private int drawable;
    private String name;
    private String author;
    private int numberReviews;
    private int numberOfReaders;
    private int numbersLovers;
    private int genreColor;

    public Item(int drawable){
        this.drawable = drawable;
    }

    public Item(String name, int drawable) {
        this.drawable = drawable;
        this.name = name;
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
}