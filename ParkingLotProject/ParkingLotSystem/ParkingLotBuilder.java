package ParkingLotSystem;

import java.util.ArrayList;
import java.util.List;

import ParkingSpots.ConcreteParkingSpots.*;

public class ParkingLotBuilder {

    private List<ParkingFloor> floors = new ArrayList<>();

    public ParkingLotBuilder addFloor(ParkingFloor floor) {
        floors.add(floor);
        return this;
    }

    public ParkingLotBuilder createFloor(int floorNumber, int carSpots, int bikeSpots) {

        ParkingFloor floor = new ParkingFloor(floorNumber);

        for (int i = 0; i < carSpots; i++)
            floor.addParkingSpot(new CarParkingSpot(i + 1));

        for (int i = 0; i < bikeSpots; i++)
            floor.addParkingSpot(new BikeParkingSpot(carSpots + i + 1));

        floors.add(floor);

        return this;
    }

    public ParkingLot build() {
        return new ParkingLot(floors);
    }
}
