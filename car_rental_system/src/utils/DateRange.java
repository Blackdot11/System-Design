import java.util.Date;

public class DateRange {
    private Date start;
    private Date end;

    public DateRange(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public boolean overlaps(Date s, Date e) {
        return !(e.before(start) || s.after(end));
    }

    public boolean matches(Date s, Date e) {
        return start.equals(s) && end.equals(e);
    }
}
