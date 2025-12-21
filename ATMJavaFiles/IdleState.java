public class IdleState implements ATMState {
    @Override
    public String getStateName() { return "IdleState"; }

    @Override
    public ATMState next(ATMMachineContext context) {
        return context.getCurrentCard() != null ?
                context.getStateFactory().createHasCardState() : this;
    }
}