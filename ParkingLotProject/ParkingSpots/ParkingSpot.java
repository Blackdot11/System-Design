package ParkingSpots;

import VehicleFactoryPattern.Vehicle;

public abstract class ParkingSpot {

    private int spotNumber;
    private boolean isOccupied;
    private Vehicle vehicle;
    private String spotType;

    public ParkingSpot(int spotNumber, String spotType) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isOccupied = false;
    }

    public boolean isOccupied() { return isOccupied; }

    public abstract boolean canParkVehicle(Vehicle vehicle);

    public void parkVehicle(Vehicle vehicle) {
        if (isOccupied) throw new IllegalStateException("Spot already occupied");
        if (!canParkVehicle(vehicle)) throw new IllegalArgumentException("Vehicle not suitable");

        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void vacate() {
        if (!isOccupied) throw new IllegalStateException("Spot already vacant");

        this.vehicle = null;
        this.isOccupied = false;
    }

    public int getSpotNumber() { return spotNumber; }
    public Vehicle getVehicle() { return vehicle; }
    public String getSpotType() { return spotType; }
}
