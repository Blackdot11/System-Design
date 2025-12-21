import java.util.List;

public class DashboardAlertSystem implements InventoryObserver {

    private List<String> admins;

    public DashboardAlertSystem(List<String> admins) {
        this.admins = admins;
    }

    @Override
    public void update(Product p) {
        System.out.println("Dashboard ALERT: Low stock on " + p.getName());
        for (String admin : admins) {
            System.out.println("Notified admin: " + admin);
        }
    }
}