
import vending.VendingMachineContext;
import model.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        VendingMachineContext vm = new VendingMachineContext();
        fillInventory(vm);

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose Payment Method:");
        System.out.println("1. Coins");
        System.out.println("2. Card");
        int choice = sc.nextInt();

        if (choice == 1) {
            vm.insertCoin(Coin.TEN_RUPEES);
            vm.insertCoin(Coin.FIVE_RUPEES);
        } else {
            sc.nextLine();
            System.out.print("Card: ");
            String card = sc.nextLine();
            System.out.print("Expiry: ");
            String expiry = sc.nextLine();
            System.out.print("CVV: ");
            String cvv = sc.nextLine();
            vm.payUsingCard(card, expiry, cvv);
        }

        System.out.println("Enter product code: ");
        int code = sc.nextInt();

        vm.selectProduct(code);

        System.out.println("Dispensing...");
        vm.dispense();
    }


    private static void fillInventory(VendingMachineContext vm) {
        int code = 101;

        for (int i = 0; i < vm.getInventory().getShelves().length; i++) {
            ItemType type = switch (i / 3) {
                case 0 -> ItemType.COKE;
                case 1 -> ItemType.PEPSI;
                case 2 -> ItemType.JUICE;
                default -> ItemType.SODA;
            };

            for (int j = 0; j < 5; j++) {
                try {
                    vm.getInventory().addItem(new Item(type, 10 + i), code);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            code++;
        }
    }
}
