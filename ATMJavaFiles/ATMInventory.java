import java.util.HashMap;
import java.util.Map;

public class ATMInventory {
    private final Map<CashType, Integer> cash = new HashMap<>();

    public ATMInventory() {
        cash.put(CashType.HUNDRED, 10);
        cash.put(CashType.FIFTY, 10);
        cash.put(CashType.TWENTY, 20);
        cash.put(CashType.TEN, 30);
    }

    public boolean hasSufficientCash(int amount) {
        return getTotalCash() >= amount;
    }

    public int getTotalCash() {
        return cash.entrySet().stream()
                .mapToInt(e -> e.getKey().value * e.getValue())
                .sum();
    }

    public Map<CashType, Integer> dispenseCash(int amount) {
        Map<CashType, Integer> output = new HashMap<>();
        int remaining = amount;

        for (CashType type : CashType.values()) {
            int available = cash.getOrDefault(type, 0);
            int needed = Math.min(remaining / type.value, available);

            if (needed > 0) {
                output.put(type, needed);
                remaining -= needed * type.value;
            }
        }

        if (remaining != 0) return null;

        output.forEach((type, count) -> cash.put(type, cash.get(type) - count));
        return output;
    }
}