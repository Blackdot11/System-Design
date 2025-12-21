
package model;

import java.util.*;

public class ItemShelf {

    private int code;
    private List<Item> items = new ArrayList<>();

    public ItemShelf(int code) {
        this.code = code;
    }

    public int getCode() { return code; }
    public List<Item> getItems() { return items; }

    public boolean isSoldOut() { return items.isEmpty(); }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem() {
        if (items.isEmpty()) return null;
        return items.remove(0);
    }
}
