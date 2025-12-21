
package states;

import vending.VendingMachineContext;
import model.Item;

public class HasMoneyState implements VendingMachineState {

    @Override
    public void insertCoin(VendingMachineContext context) {
        System.out.println("Additional coin added.");
    }

    @Override
    public void makePayment(VendingMachineContext context) {}

    @Override
    public void selectProduct(VendingMachineContext context, int code) {
        try {
            Item item = context.getInventory().peekItem(code);

            if (!context.processPayment(item.getPrice())) {
                System.out.println("Insufficient funds or payment failed.");
                return;
            }

            context.setSelectedItemCode(code);
            context.setState(new SelectionState());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dispense(VendingMachineContext context) {
        System.out.println("Select a product first.");
    }

    @Override
    public String getStateName() { return "HasMoneyState"; }
}
