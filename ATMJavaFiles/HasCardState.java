public class HasCardState implements ATMState {
    @Override
    public String getStateName() { return "HasCardState"; }

    @Override
    public ATMState next(ATMMachineContext context) {
        if (context.getCurrentAccount() != null)
            return context.getStateFactory().createSelectOperationState();

        return this;
    }
}