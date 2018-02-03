package it.corelab.airbooks;

/**
 * Created by Roberto_Vecchio on 03/02/18.
 */

public class Person {


    //=========================
    //       variables
    //=========================

    private int imageID;
    private String nameSurname;
    private String descriptionText;
    private int numberBookRead;
    private int numberReview;


    //=========================
    //       constructor
    //=========================

    public Person(int imageID, String nameSurname, String descriptionText, int numberBookRead, int numberReview){
        this.imageID = imageID;
        this.nameSurname = nameSurname;
        this.descriptionText = descriptionText;
        this.numberBookRead = numberBookRead;
        this.numberReview = numberReview;
    }

    public Person(){
        this.imageID = 0;
        this.nameSurname = "";
        this.descriptionText = "";
        this.numberBookRead = 0;
        this.numberReview = 0;
    }


    //=========================
    //        getter
    //=========================

    public int getImageID(){
        return  imageID;
    }

    public String getNameSurname(){
        return nameSurname;
    }

    public String getDescriptionText(){
        return descriptionText;
    }

    public int getNumberBookRead(){
        return numberBookRead;
    }

    public int getNumberReview(){
        return numberReview;
    }


    //=========================
    //         setter
    //=========================

    public void setImageID(int imageID){
        this.imageID = imageID;
    }

    public void setNameSurname(String nameSurname){
        this.nameSurname = nameSurname;
    }

    public void setDescriptionText(String descriptionText){
        this.descriptionText = descriptionText;
    }

    public void setNumberBookRead(int numberBookRead){
        this.numberBookRead = numberBookRead;
    }

    public void setNumberReview(int numberReview){
        this.numberReview = numberReview;
    }

}
