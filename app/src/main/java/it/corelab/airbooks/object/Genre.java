package it.corelab.airbooks.object;

import java.util.HashMap;

public class Genre extends Entity {

    private int ID;
    private String name_eng;

    public Genre(HashMap<String, Object> genre){
        super(genre);
        this.ID = (int) genre.get("id");
        this.name_eng = genre.get("name_eng").toString();
    }

    public int getID() {
        return ID;
    }

    public String getName_eng() {
        return name_eng;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName_eng(String name_eng) {
        this.name_eng = name_eng;
    }
}
