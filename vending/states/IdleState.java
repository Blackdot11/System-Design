
package states;

import vending.VendingMachineContext;

public class IdleState implements VendingMachineState {

    @Override
    public void insertCoin(VendingMachineContext context) {
        System.out.println("Coins inserted.");
        context.setState(new HasMoneyState());
    }

    @Override
    public void makePayment(VendingMachineContext context) {
        System.out.println("Card payment initiated.");
        context.setState(new HasMoneyState());
    }

    @Override
    public void selectProduct(VendingMachineContext context, int code) {
        System.out.println("Insert payment first.");
    }

    @Override
    public void dispense(VendingMachineContext context) {
        System.out.println("Nothing to dispense.");
    }

    @Override
    public String getStateName() { return "IdleState"; }
}
