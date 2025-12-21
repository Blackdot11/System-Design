import java.util.*;

public class ScanSchedulingStrategy implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator) {
        Queue<ElevatorRequest> requests = elevator.getRequestsQueue();
        if (requests.isEmpty()) return elevator.getCurrentFloor();

        int currentFloor = elevator.getCurrentFloor();
        Direction dir = elevator.getDirection();

        List<ElevatorRequest> sameDirection = new ArrayList<>();
        List<ElevatorRequest> oppositeDirection = new ArrayList<>();

        for (ElevatorRequest r : requests) {
            if ((dir == Direction.UP && r.getFloor() > currentFloor) ||
                (dir == Direction.DOWN && r.getFloor() < currentFloor))
                sameDirection.add(r);
            else
                oppositeDirection.add(r);
        }

        if (!sameDirection.isEmpty()) {
            int closest = sameDirection.get(0).getFloor();
            for (ElevatorRequest r : sameDirection) {
                if (Math.abs(r.getFloor() - currentFloor) < Math.abs(closest - currentFloor))
                    closest = r.getFloor();
            }
            return closest;
        } else if (!oppositeDirection.isEmpty()) {
            int closest = oppositeDirection.get(0).getFloor();
            for (ElevatorRequest r : oppositeDirection) {
                if (Math.abs(r.getFloor() - currentFloor) < Math.abs(closest - currentFloor))
                    closest = r.getFloor();
            }
            if (closest > currentFloor) elevator.setDirection(Direction.UP);
            else elevator.setDirection(Direction.DOWN);
            return closest;
        }

        return currentFloor;
    }
}
