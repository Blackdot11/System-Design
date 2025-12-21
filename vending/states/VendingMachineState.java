
package states;

import vending.VendingMachineContext;

public interface VendingMachineState {

    void insertCoin(VendingMachineContext context);
    void makePayment(VendingMachineContext context);
    void selectProduct(VendingMachineContext context, int code);
    void dispense(VendingMachineContext context);
    String getStateName();
}
