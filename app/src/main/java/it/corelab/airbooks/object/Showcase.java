package it.corelab.airbooks.object;

/**
 * Created by Roberto_Vecchio on 15/02/18.
 */

public class Showcase {
    private int drawable;
    private String title;

    public Showcase (int drawable){
        this.drawable = drawable;
    }

    public Showcase(int drawable, String title){
        this.drawable = drawable;
        this.title = title;
    }

    public int getDrawable(){
       return drawable;
    }

    public String getTitle(){
        return title;
    }

    public void setDrawable(int drawable){
        this.drawable = drawable;
    }

    public void setTitle(String title){
        this.title = title;
    }

}
