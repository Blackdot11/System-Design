import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        InventoryManager manager = InventoryManager.getInstance();

        manager.addObserver(new SupplierNotifier("Dell Supplier", "supplier@dell.com"));
        manager.addObserver(new DashboardAlertSystem(
                Arrays.asList("admin1@ims.com", "admin2@ims.com")
        ));

        Warehouse w1 = new Warehouse("Warehouse 1");
        Warehouse w2 = new Warehouse("Warehouse 2");

        manager.addWarehouse(w1);
        manager.addWarehouse(w2);

        ProductFactory factory = manager.getProductFactory();

        Product laptop = factory.createProduct(ProductCategory.ELECTRONICS, "SKU123", "Laptop", 1000, 20, 25);
        Product tshirt = factory.createProduct(ProductCategory.CLOTHING, "SKU456", "T-Shirt", 20, 200, 100);
        Product apple = factory.createProduct(ProductCategory.GROCERY, "SKU789", "Apple", 1, 50, 200);

        w1.addProduct(laptop, 10);
        w1.addProduct(tshirt, 20);
        w2.addProduct(apple, 20);

        manager.setReplenishmentStrategy(new JustInTimeStrategy());
        manager.performInventoryCheck();
    }
}