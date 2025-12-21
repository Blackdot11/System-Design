public class JustInTimeStrategy implements ReplenishmentStrategy {
    @Override
    public void replenish(Product p) {
        System.out.println("JIT: Replenishing " + p.getName());
    }
}