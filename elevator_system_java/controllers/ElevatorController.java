import java.util.*;

public class ElevatorController {
    private List<Elevator> elevators;
    private List<Floor> floors;
    private SchedulingStrategy schedulingStrategy;

    public ElevatorController(int numberOfElevators, int numberOfFloors) {
        elevators = new ArrayList<>();
        floors = new ArrayList<>();
        schedulingStrategy = new ScanSchedulingStrategy();
        for (int i = 1; i <= numberOfElevators; i++) elevators.add(new Elevator(i));
        for (int i = 1; i <= numberOfFloors; i++) floors.add(new Floor(i));
    }

    public void setSchedulingStrategy(SchedulingStrategy strategy) { this.schedulingStrategy = strategy; }

    public void requestElevator(int elevatorId, int floorNumber, Direction direction) {
        Elevator e = getElevatorById(elevatorId);
        if (e != null) e.addRequest(new ElevatorRequest(elevatorId, floorNumber, false, direction));
    }

    public void requestFloor(int elevatorId, int floorNumber) {
        Elevator e = getElevatorById(elevatorId);
        Direction dir = floorNumber > e.getCurrentFloor() ? Direction.UP : Direction.DOWN;
        e.addRequest(new ElevatorRequest(elevatorId, floorNumber, true, dir));
    }

    private Elevator getElevatorById(int elevatorId) {
        for (Elevator e : elevators) if (e.getId() == elevatorId) return e;
        return null;
    }

    public void step() {
        for (Elevator e : elevators) {
            if (!e.getRequestsQueue().isEmpty()) {
                int nextStop = schedulingStrategy.getNextStop(e);
                if (e.getCurrentFloor() != nextStop) e.moveToNextStop(nextStop);
            }
        }
    }

    public List<Elevator> getElevators() { return elevators; }
    public List<Floor> getFloors() { return floors; }
}
