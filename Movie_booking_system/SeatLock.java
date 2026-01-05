import java.util.Date;
import java.time.Instant;

public class SeatLock {
    private Seat seat;
    private Show show;
    private Integer timeoutInSeconds;
    private Date lockTime;
    private User lockedBy;

    public SeatLock(Seat seat, Show show, Integer timeoutInSeconds, Date lockTime, User user) {
        this.seat = seat;
        this.show = show;
        this.timeoutInSeconds = timeoutInSeconds;
        this.lockTime = lockTime;
        this.lockedBy = user;
    }

    public boolean isLockExpired() {
        Instant expiry = lockTime.toInstant().plusSeconds(timeoutInSeconds);
        return expiry.isBefore(new Date().toInstant());
    }

    public User getLockedBy() { return lockedBy; }
}