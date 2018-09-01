package it.corelab.studios.airbooks.model.data.USER.ACTIVE;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BooksRead {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("items")
    @Expose
    private List<BooksReadItem> items = null;
    @SerializedName("next")
    @Expose
    private String next;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<BooksReadItem> getItems() {
        return items;
    }

    public void setItems(List<BooksReadItem> items) {
        this.items = items;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}
