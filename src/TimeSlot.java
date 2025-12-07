import java.time.LocalTime;

public class TimeSlot {

    private final LocalTime start;
    private final LocalTime end;

    public TimeSlot(LocalTime start, LocalTime end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
        this.start = start;
        this.end = end;
    }

    public boolean isOngoing(LocalTime t) {
        return !t.isBefore(start) && t.isBefore(end);
    }

    public boolean overlaps(LocalTime a, LocalTime b) {
        return !end.isBefore(a) && !b.isBefore(start);
    }

    public LocalTime getEnd() {
        return end;
    }
}
