package ParkingLotSystem;

import java.util.ArrayList;
import java.util.List;

import ParkingSpots.ParkingSpot;

public class ParkingFloor {

    private int floorNumber;
    private List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.spots = new ArrayList<>();
    }

    public void addParkingSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public ParkingSpot findAvailableSpot(String vehicleType) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied() && spot.getSpotType().equalsIgnoreCase(vehicleType)) {
                return spot;
            }
        }
        return null;
    }

    public List<ParkingSpot> getParkingSpots() { return spots; }
    public int getFloorNumber() { return floorNumber; }
}
