
package payment;

import model.Coin;
import java.util.List;

public class CoinPaymentStrategy implements PaymentStrategy {

    private List<Coin> coins;

    public CoinPaymentStrategy(List<Coin> coins) { this.coins = coins; }

    @Override
    public boolean processPayment(double amount) {
        int total = coins.stream().mapToInt(c -> c.value).sum();
        return total >= amount;
    }

    public int getTotal() {
        return coins.stream().mapToInt(c -> c.value).sum();
    }
}
