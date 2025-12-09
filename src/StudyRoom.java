import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a study room that students can use on campus.
 * Includes availability data, occupancy status, and reserved time slots.
 *
 * <p>This class extends BaseRoom and implements features such as:</p>
 * <ul>
 *     <li>inheritance</li>
 *     <li>use of super keyword</li>
 *     <li>encapsulation via private fields</li>
 *     <li>static variables for room tracking</li>
 *     <li>conditional operator usage</li>
 * </ul>
 */
public class StudyRoom extends BaseRoom {

    /** Total number of StudyRoom objects created. */
    private static int totalRooms = 0;

    /** Maximum number of students allowed in the room. */
    private int capacity;

    /** Whether the room is currently occupied. */
    private boolean occupied;

    /** List of reserved time slots for this room. */
    private final List<TimeSlot> reservedSlots = new ArrayList<>();

    /**
     * Constructs a StudyRoom using room details.
     *
     * @param id        room ID
     * @param name      room name
     * @param building  building containing the room
     * @param capacity  capacity of the room
     */
    public StudyRoom(int id, String name, String building, int capacity) {
        super(id, name, building);          // calls parent constructor
        this.capacity = capacity;
        this.occupied = false;
        totalRooms++;
    }

    /**
     * Returns the maximum number of students allowed in the room.
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Updates the room capacity.
     *
     * @param capacity new capacity value
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Checks whether the room is occupied.
     *
     * @return true if occupied
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Sets the occupied status of the room.
     *
     * @param occupied true to mark room as in use
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Adds a reserved time slot to the room schedule.
     *
     * @param start start time
     * @param end   end time
     */
    public void addReservedSlot(LocalTime start, LocalTime end) {
        reservedSlots.add(new TimeSlot(start, end));
    }

    /**
     * Checks if the room is available at a specific time.
     *
     * @param t time to check
     * @return true if available
     */
    public boolean isAvailableAt(LocalTime t) {
        if (occupied) return false;
        for (TimeSlot slot : reservedSlots) {
            if (slot.isOngoing(t)) return false;
        }
        return true;
    }

    /**
     * Determines if the room is available at some point
     * within a given time window.
     *
     * @param start window start time
     * @param limit window end time
     * @return true if available sometime in the window
     */
    public boolean isAvailableBetween(LocalTime start, LocalTime limit) {
        if (occupied) return false;
        for (TimeSlot slot : reservedSlots) {
            if (slot.overlaps(start, limit)) return false;
        }
        return true;
    }

    /**
     * Returns the earliest time that the room will next be free.
     *
     * @param ref reference time
     * @return next available time
     */
    public LocalTime nextFreeTime(LocalTime ref) {
        if (isAvailableAt(ref)) {
            return ref;
        }
        LocalTime soonest = null;
        for (TimeSlot slot : reservedSlots) {
            if (slot.isOngoing(ref)) {
                if (soonest == null || slot.getEnd().isBefore(soonest)) {
                    soonest = slot.getEnd();
                }
            }
        }
        return soonest;
    }

    /**
     * Returns a formatted string describing the room.
     *
     * @return room details
     */
    public String describe() {
        String status = occupied ? "Yes" : "No"; /** conditional operator */
        return String.format(
                "Id: %d | Name: %s | Building: %s | Capacity: %d | Occupied: %s",
                getId(), getName(), getBuilding(), capacity, status
        );
    }

    /**
     * Returns a description including next free time.
     *
     * @param ref reference time
     * @return extended description
     */
    public String describeWithNextFreeTime(LocalTime ref) {
        LocalTime next = nextFreeTime(ref);
        return describe() + " | Next free: " + next;
    }

    /**
     * Returns a summary string for displaying room info.
     *
     * @return basic info summary
     */
    @Override
    public String basicInfo() {
        return getName() + " in " + getBuilding();
    }

    /**
     * Returns the total number of created StudyRoom objects.
     *
     * @return total room count
     */
    public static int getTotalRooms() {
        return totalRooms;
    }
}
