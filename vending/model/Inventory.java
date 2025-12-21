
package model;

public class Inventory {

    private ItemShelf[] shelves;

    public Inventory(int size) {
        shelves = new ItemShelf[size];
        int code = 101;
        for (int i = 0; i < size; i++) {
            shelves[i] = new ItemShelf(code++);
        }
    }

    public ItemShelf[] getShelves() { return shelves; }

    public void addItem(Item item, int code) throws Exception {
        for (ItemShelf shelf : shelves) {
            if (shelf.getCode() == code) {
                shelf.addItem(item);
                return;
            }
        }
        throw new Exception("Invalid Code");
    }

    public Item peekItem(int code) throws Exception {
        for (ItemShelf shelf : shelves) {
            if (shelf.getCode() == code) {
                if (shelf.isSoldOut()) throw new Exception("Item Sold Out");
                return shelf.getItems().get(0);
            }
        }
        throw new Exception("Invalid Code");
    }

    public Item removeItem(int code) throws Exception {
        for (ItemShelf shelf : shelves) {
            if (shelf.getCode() == code) {
                Item item = shelf.removeItem();
                if (item == null) throw new Exception("Item Sold Out");
                return item;
            }
        }
        throw new Exception("Invalid Code");
    }
}
