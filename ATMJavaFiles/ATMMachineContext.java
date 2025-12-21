import java.util.HashMap;
import java.util.Map;

public class ATMMachineContext {

    private ATMState currentState;
    private Card currentCard;
    private Account currentAccount;
    private ATMInventory inventory = new ATMInventory();
    private Map<String, Account> accounts = new HashMap<>();
    private TransactionType selectedOperation;

    private final ATMStateFactory stateFactory = ATMStateFactory.getInstance();

    public ATMMachineContext() {
        currentState = stateFactory.createIdleState();
    }

    public void insertCard(Card card) {
        if (!(currentState instanceof IdleState)) {
            System.out.println("Cannot insert card now.");
            return;
        }
        this.currentCard = card;
        advanceState();
    }

    public void enterPin(int pin) {
        if (!(currentState instanceof HasCardState)) return;

        if (currentCard.validatePin(pin)) {
            currentAccount = accounts.get(currentCard.getAccountNumber());
            advanceState();
        } else {
            System.out.println("Invalid PIN!");
        }
    }

    public void selectOperation(TransactionType type) {
        if (currentState instanceof SelectOperationState) {
            selectedOperation = type;
            advanceState();
        }
    }

    public void performTransaction(double amount) {
        if (!(currentState instanceof TransactionState)) return;

        try {
            if (selectedOperation == TransactionType.WITHDRAW_CASH)
                withdraw(amount);
            else
                checkBalance();

            advanceState();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void withdraw(double amount) throws Exception {
        if (!currentAccount.withdraw(amount))
            throw new Exception("Insufficient account balance");

        if (!inventory.hasSufficientCash((int) amount)) {
            currentAccount.deposit(amount);
            throw new Exception("ATM out of cash");
        }

        Map<CashType, Integer> cash = inventory.dispenseCash((int) amount);
        if (cash == null) {
            currentAccount.deposit(amount);
            throw new Exception("Cannot dispense exact amount");
        }

        System.out.println("Dispensed:");
        cash.forEach((t, c) -> System.out.println(c + " x $" + t.value));
    }

    private void checkBalance() {
        System.out.println("Balance: $" + currentAccount.getBalance());
    }

    public void returnCard() {
        reset();
        System.out.println("Card Returned.");
    }

    private void reset() {
        currentCard = null;
        currentAccount = null;
        selectedOperation = null;
        currentState = stateFactory.createIdleState();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void advanceState() {
        currentState = currentState.next(this);
        System.out.println("State → " + currentState.getStateName());
    }

    public Card getCurrentCard() { return currentCard; }
    public Account getCurrentAccount() { return currentAccount; }
    public TransactionType getSelectedOperation() { return selectedOperation; }
    public ATMStateFactory getStateFactory() { return stateFactory; }
}