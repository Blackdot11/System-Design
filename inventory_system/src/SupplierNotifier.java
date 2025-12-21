public class SupplierNotifier implements InventoryObserver {

    private String supplier;
    private String email;

    public SupplierNotifier(String supplier, String email) {
        this.supplier = supplier;
        this.email = email;
    }

    @Override
    public void update(Product p) {
        System.out.println("Email sent to " + supplier + " for low stock: " + p.getName());
    }
}