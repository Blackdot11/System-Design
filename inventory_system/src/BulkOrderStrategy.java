public class BulkOrderStrategy implements ReplenishmentStrategy {
    @Override
    public void replenish(Product p) {
        System.out.println("Bulk Order: Replenishing " + p.getName());
    }
}