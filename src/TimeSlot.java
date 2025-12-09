import java.time.LocalTime;

/**
 * Represents a reserved time interval for a study room.
 * Time slots ensure that rooms are not used when reserved.
 */
public class TimeSlot {

    /** Start time of the reservation. */
    private final LocalTime start;

    /** End time of the reservation. */
    private final LocalTime end;

    /**
     * Creates a new time slot with a defined start and end time.
     *
     * @param start beginning of reservation
     * @param end   end of reservation
     */
    public TimeSlot(LocalTime start, LocalTime end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time must not be before start time");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Checks if the given time is within this time slot.
     *
     * @param t time to check
     * @return true if reservation is active at time t
     */
    public boolean isOngoing(LocalTime t) {
        return !t.isBefore(start) && t.isBefore(end);
    }

    /**
     * Determines whether this slot overlaps with another time range.
     *
     * @param a start of comparison window
     * @param b end of comparison window
     * @return true if intervals overlap
     */
    public boolean overlaps(LocalTime a, LocalTime b) {
        return !end.isBefore(a) && !b.isBefore(start);
    }

    /**
     * Returns the ending time of this reservation.
     *
     * @return end time
     */
    public LocalTime getEnd() {
        return end;
    }
}
