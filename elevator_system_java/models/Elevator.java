import java.util.*;

public class Elevator {
    private int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private List<ElevatorObserver> observers;
    private Queue<ElevatorRequest> requests;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 1;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.observers = new ArrayList<>();
        this.requests = new LinkedList<>();
    }

    public void addObserver(ElevatorObserver observer) { observers.add(observer); }
    public void removeObserver(ElevatorObserver observer) { observers.remove(observer); }

    private void notifyStateChange(ElevatorState state) {
        for (ElevatorObserver o : observers) o.onElevatorStateChange(this, state);
    }

    private void notifyFloorChange(int floor) {
        for (ElevatorObserver o : observers) o.onElevatorFloorChange(this, floor);
    }

    public void setState(ElevatorState newState) {
        this.state = newState;
        notifyStateChange(newState);
    }

    public void setDirection(Direction newDirection) { this.direction = newDirection; }

    public void addRequest(ElevatorRequest request) {
        if (!requests.contains(request)) requests.add(request);
        int requestedFloor = request.getFloor();
        if (state == ElevatorState.IDLE && !requests.isEmpty()) {
            if (requestedFloor > currentFloor) direction = Direction.UP;
            else if (requestedFloor < currentFloor) direction = Direction.DOWN;
            setState(ElevatorState.MOVING);
        }
    }

    public void moveToNextStop(int nextStop) {
        if (state != ElevatorState.MOVING) return;
        while (currentFloor != nextStop) {
            currentFloor += direction == Direction.UP ? 1 : -1;
            notifyFloorChange(currentFloor);
            if (currentFloor == nextStop) {
                completeArrival();
                return;
            }
        }
    }

    private void completeArrival() {
        setState(ElevatorState.STOPPED);
        requests.removeIf(r -> r.getFloor() == currentFloor);
        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            setState(ElevatorState.IDLE);
        } else {
            setState(ElevatorState.MOVING);
        }
    }

    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public Direction getDirection() { return direction; }
    public ElevatorState getState() { return state; }
    public Queue<ElevatorRequest> getRequestsQueue() { return new LinkedList<>(requests); }
    public List<ElevatorRequest> getDestinationFloors() { return new ArrayList<>(requests); }
}
