import java.util.*;

public class Main {
    public static void main(String[] args) {
        Building building = new Building("Office Tower", 10, 3);
        ElevatorController controller = building.getElevatorController();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elevator Simulation Started");

        boolean running = true;
        while (running) {
            System.out.println("1. External request");
            System.out.println("2. Internal request");
            System.out.println("3. Step simulation");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    System.out.print("Elevator ID: "); int eid = scanner.nextInt();
                    System.out.print("Floor: "); int floor = scanner.nextInt();
                    System.out.print("Direction (1 UP, 2 DOWN): "); int d = scanner.nextInt();
                    controller.requestElevator(eid, floor, d==1? Direction.UP: Direction.DOWN);
                    break;
                case 2:
                    System.out.print("Elevator ID: "); int ieid = scanner.nextInt();
                    System.out.print("Destination Floor: "); int dest = scanner.nextInt();
                    controller.requestFloor(ieid, dest);
                    break;
                case 3:
                    controller.step();
                    displayStatus(controller.getElevators());
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }

    private static void displayStatus(List<Elevator> elevators) {
        for (Elevator e : elevators) {
            System.out.println("Elevator " + e.getId() +
                " Floor " + e.getCurrentFloor() +
                " Direction " + e.getDirection() +
                " State " + e.getState());
        }
    }
}
