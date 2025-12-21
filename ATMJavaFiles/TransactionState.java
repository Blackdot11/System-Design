public class TransactionState implements ATMState {
    @Override
    public String getStateName() { return "TransactionState"; }

    @Override
    public ATMState next(ATMMachineContext context) {
        return context.getStateFactory().createSelectOperationState();
    }
}