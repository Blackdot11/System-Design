import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private static InventoryManager instance;

    private List<Warehouse> warehouses = new ArrayList<>();
    private List<InventoryObserver> observers = new ArrayList<>();

    private ProductFactory factory = new ProductFactory();
    private ReplenishmentStrategy strategy;

    private InventoryManager() {}

    public static synchronized InventoryManager getInstance() {
        if (instance == null) instance = new InventoryManager();
        return instance;
    }

    public void addWarehouse(Warehouse w) { warehouses.add(w); }
    public ProductFactory getProductFactory() { return factory; }
    public void setReplenishmentStrategy(ReplenishmentStrategy s) { this.strategy = s; }

    public void addObserver(InventoryObserver obs) { observers.add(obs); }

    private void notifyObservers(Product p) {
        for (InventoryObserver o : observers) o.update(p);
    }

    public void performInventoryCheck() {
        for (Warehouse w : warehouses) {
            for (Product p : w.getAllProducts().values()) {
                if (p.getQuantity() < p.getThreshold()) {
                    notifyObservers(p);
                    strategy.replenish(p);
                }
            }
        }
    }
}