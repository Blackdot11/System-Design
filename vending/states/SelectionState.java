
package states;

import vending.VendingMachineContext;
import model.Item;
import payment.CoinPaymentStrategy;

public class SelectionState implements VendingMachineState {

    @Override
    public void insertCoin(VendingMachineContext context) {}

    @Override
    public void makePayment(VendingMachineContext context) {}

    @Override
    public void selectProduct(VendingMachineContext context, int code) {
        System.out.println("Product already selected.");
    }

    @Override
    public void dispense(VendingMachineContext context) {
        try {
            int code = context.getSelectedItemCode();
            Item item = context.getInventory().removeItem(code);

            System.out.println("Dispensing: " + item.getType());

            if (context.getPaymentStrategy() instanceof CoinPaymentStrategy cps) {
                int change = cps.getTotal() - item.getPrice();
                if (change > 0) System.out.println("Returning change: " + change);
            }

            context.resetPayment();
            context.setState(new IdleState());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getStateName() { return "SelectionState"; }
}
