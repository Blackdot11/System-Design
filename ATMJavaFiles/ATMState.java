public interface ATMState {
    String getStateName();
    ATMState next(ATMMachineContext context);
}