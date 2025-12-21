public class Card {
    private final String cardNumber;
    private final int pin;
    private final String accountNumber;

    public Card(String cardNumber, int pin, String accountNumber) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.accountNumber = accountNumber;
    }

    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}