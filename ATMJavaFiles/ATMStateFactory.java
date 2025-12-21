public class ATMStateFactory {
    private static ATMStateFactory instance = new ATMStateFactory();

    public static ATMStateFactory getInstance() {
        return instance;
    }

    public ATMState createIdleState() { return new IdleState(); }
    public ATMState createHasCardState() { return new HasCardState(); }
    public ATMState createSelectOperationState() { return new SelectOperationState(); }
    public ATMState createTransactionState() { return new TransactionState(); }
}