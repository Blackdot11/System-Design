public class SelectOperationState implements ATMState {
    @Override
    public String getStateName() { return "SelectOperationState"; }

    @Override
    public ATMState next(ATMMachineContext context) {
        if (context.getSelectedOperation() != null)
            return context.getStateFactory().createTransactionState();

        return this;
    }
}