public enum CashType {
    HUNDRED(100), FIFTY(50), TWENTY(20), TEN(10), FIVE(5), ONE(1);

    public final int value;

    CashType(int value) {
        this.value = value;
    }
}