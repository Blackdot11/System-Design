import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class SeatLockProvider implements ISeatLockProvider {

    private final Integer lockTimeout;
    private final Map<Show, Map<Seat, SeatLock>> locks = new ConcurrentHashMap<>();

    public SeatLockProvider(Integer lockTimeout) {
        this.lockTimeout = lockTimeout;
    }

    public void lockSeats(Show show, List<Seat> seats, User user) throws Exception {
        Map<Seat, SeatLock> seatLocks =
            locks.computeIfAbsent(show, s -> new ConcurrentHashMap<>());

        synchronized (seatLocks) {
            for (Seat seat : seats) {
                if (seatLocks.containsKey(seat)
                        && !seatLocks.get(seat).isLockExpired()) {
                    throw new Exception("Seat already locked");
                }
            }
            Date now = new Date();
            for (Seat seat : seats) {
                seatLocks.put(seat,
                    new SeatLock(seat, show, lockTimeout, now, user));
            }
        }
    }

    public void unlockSeats(Show show, List<Seat> seats, User user) {
        Map<Seat, SeatLock> seatLocks = locks.get(show);
        if (seatLocks == null) return;

        synchronized (seatLocks) {
            for (Seat seat : seats) {
                SeatLock lock = seatLocks.get(seat);
                if (lock != null && lock.getLockedBy().equals(user)) {
                    seatLocks.remove(seat);
                }
            }
        }
    }

    public boolean validateLock(Show show, Seat seat, User user) {
        Map<Seat, SeatLock> seatLocks = locks.get(show);
        if (seatLocks == null) return false;

        SeatLock lock = seatLocks.get(seat);
        return lock != null &&
               !lock.isLockExpired() &&
               lock.getLockedBy().equals(user);
    }

    public List<Seat> getLockedSeats(Show show) {
        Map<Seat, SeatLock> seatLocks = locks.get(show);
        if (seatLocks == null) return Collections.emptyList();

        return seatLocks.entrySet().stream()
            .filter(e -> !e.getValue().isLockExpired())
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }
}