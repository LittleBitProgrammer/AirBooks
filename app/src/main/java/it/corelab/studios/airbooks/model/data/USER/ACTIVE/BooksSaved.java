package it.corelab.studios.airbooks.model.data.USER.ACTIVE;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BooksSaved {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("items")
    @Expose
    private List<BooksSavedItem> items = null;
    @SerializedName("next")
    @Expose
    private String next;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<BooksSavedItem> getItems() {
        return items;
    }

    public void setItems(List<BooksSavedItem> items) {
        this.items = items;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}
