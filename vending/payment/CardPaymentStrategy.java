
package payment;

public class CardPaymentStrategy implements PaymentStrategy {

    private String card;
    private String expiry;
    private String cvv;

    public CardPaymentStrategy(String card, String expiry, String cvv) {
        this.card = card;
        this.expiry = expiry;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing card payment: " + amount);
        return true;
    }
}
