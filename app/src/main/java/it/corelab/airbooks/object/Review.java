package it.corelab.airbooks.object;

/**
 * Created by Roberto_Vecchio on 10/03/18.
 */

public class Review {

    private int drawable;
    private String name;
    private int hour;
    private String description;
    private boolean isSegnaled;
    private int vote;

    public Review (int drawable, String name, int hour, String description,int vote, boolean isSegnaled){
        this.drawable = drawable;
        this.name = name;
        this.hour = hour;
        this.description = description;
        this.vote = vote;
        this.isSegnaled = isSegnaled;
    }
    public Review(){
    }

    public int getDrawable(){
        return drawable;
    }

    public String getName(){
        return name;
    }

    public int getHour(){
        return hour;
    }

    public String getDescription(){
        return description;
    }

    public int getVote(){
        return vote;
    }

    public boolean getIsSegnaled(){
        return isSegnaled;
    }

    public void setDrawable(int drawable){
        this.drawable = drawable;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setHour(int hour){
        this.hour = hour;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setIsSegnaled(boolean isSegnaled){
        this.isSegnaled = isSegnaled;
    }

    public void setVote(int vote){
        this.vote = vote;
    }

}
