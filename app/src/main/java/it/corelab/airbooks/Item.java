package it.corelab.airbooks;

/**
 * Created by Roberto_Vecchio on 05/02/18.
 */

public class Item {

    private int drawable;
    private String name;

    public Item(String name, int drawable) {
        this.drawable = drawable;
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public String getName() {
        return name;
    }
}