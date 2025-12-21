public class ElevatorRequest {
    private int elevatorId;
    private int floor;
    private boolean internal;
    private Direction direction;

    public ElevatorRequest(int elevatorId, int floor, boolean internal, Direction direction) {
        this.elevatorId = elevatorId;
        this.floor = floor;
        this.internal = internal;
        this.direction = direction;
    }

    public int getElevatorId() { return elevatorId; }
    public int getFloor() { return floor; }
    public boolean isInternal() { return internal; }
    public Direction getDirection() { return direction; }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ElevatorRequest)) return false;
        ElevatorRequest other = (ElevatorRequest) obj;
        return this.floor == other.floor && this.internal == other.internal;
    }

    @Override
    public int hashCode() {
        return floor * 31 + (internal ? 1 : 0);
    }
}
