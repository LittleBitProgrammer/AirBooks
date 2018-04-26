package it.corelab.airbooks.object;

import java.util.HashMap;

public class Genre extends Entity {

    private int ID;
    private String name;

    public Genre(HashMap<String, Object> genre){
        super(genre);
        this.ID = (int) genre.get("id");
        this.name = genre.get("name").toString();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }
}
