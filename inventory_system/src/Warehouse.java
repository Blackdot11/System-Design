import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private String name;
    private Map<String, Product> products = new HashMap<>();

    public Warehouse(String name) { this.name = name; }

    public void addProduct(Product p, int qty) {
        p.setQuantity(p.getQuantity() + qty);
        products.put(p.getSku(), p);
    }

    public Product getProduct(String sku) {
        return products.get(sku);
    }

    public Map<String, Product> getAllProducts() {
        return products;
    }
}