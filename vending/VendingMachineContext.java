
package vending;

import java.util.*;
import model.*;
import payment.*;
import states.*;

public class VendingMachineContext {

    private VendingMachineState currentState;
    private Inventory inventory;
    private List<Coin> coins = new ArrayList<>();
    private int selectedItemCode;
    private PaymentStrategy paymentStrategy;

    public VendingMachineContext() {
        inventory = new Inventory(10);
        currentState = new IdleState();
    }

    public void setState(VendingMachineState state) {
        currentState = state;
        System.out.println("State → " + state.getStateName());
    }

    public Inventory getInventory() { return inventory; }
    public PaymentStrategy getPaymentStrategy() { return paymentStrategy; }

    public void setPaymentStrategy(PaymentStrategy ps) {
        this.paymentStrategy = ps;
    }

    public boolean processPayment(double amount) {
        return paymentStrategy.processPayment(amount);
    }

    public void insertCoin(Coin coin) {
        coins.add(coin);
        setPaymentStrategy(new CoinPaymentStrategy(coins));
        currentState.insertCoin(this);
    }

    public void payUsingCard(String card, String expiry, String cvv) {
        setPaymentStrategy(new CardPaymentStrategy(card, expiry, cvv));
        currentState.makePayment(this);
    }

    public void selectProduct(int code) {
        currentState.selectProduct(this, code);
    }

    public void dispense() {
        currentState.dispense(this);
    }

    public int getSelectedItemCode() { return selectedItemCode; }

    public void setSelectedItemCode(int code) {
        this.selectedItemCode = code;
    }

    public void resetPayment() {
        coins.clear();
        paymentStrategy = null;
        selectedItemCode = 0;
    }
}
