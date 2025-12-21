public class ATMDemo {
    public static void main(String[] args) {
        ATMMachineContext atm = new ATMMachineContext();

        atm.addAccount(new Account("ACC123", 1000));
        atm.insertCard(new Card("CARD1", 1234, "ACC123"));
        atm.enterPin(1234);

        atm.selectOperation(TransactionType.WITHDRAW_CASH);
        atm.performTransaction(200);

        atm.selectOperation(TransactionType.CHECK_BALANCE);
        atm.performTransaction(0);

        atm.returnCard();
    }
}