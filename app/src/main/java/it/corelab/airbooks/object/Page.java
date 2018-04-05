package it.corelab.airbooks.object;

import java.util.ArrayList;
import java.util.HashMap;

public class Page<T extends Entity> extends Entity {

    private int count;
    private ArrayList<T> items;
    private String next;

    @SuppressWarnings (value="unchecked")
    public Page(HashMap<String, Object> page){
        super(page);
        this.count = (int) page.get("count");
        this.items = new ArrayList<>();
        if ((this.items = (ArrayList<T>) page.get("items")) != null) {
            this.items = (ArrayList<T>) page.get("items");
            for (T itm : items) {
                this.items.add(itm);
            }
        }
        this.next = page.get("next").toString();
    }

    public int getCount() {
        return count;
    }

    public ArrayList<T> getItems() {
        return items;
    }

    public String getNext() {
        return next;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
