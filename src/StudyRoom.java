import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StudyRoom extends BaseRoom {

    private static int totalRooms = 0;

    private int capacity;
    private boolean occupied;
    private final List<TimeSlot> reservedSlots = new ArrayList<>();

    public StudyRoom(int id, String name, String building, int capacity) {
        super(id, name, building);
        this.capacity = capacity;
        this.occupied = false;
        totalRooms++;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity > 0 && capacity < 100) {
            this.capacity = capacity;
        }
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void addReservedSlot(LocalTime start, LocalTime end) {
        reservedSlots.add(new TimeSlot(start, end));
    }

    public boolean isAvailableAt(LocalTime t) {
        if (occupied) {
            return false;
        }
        for (TimeSlot slot : reservedSlots) {
            if (slot.isOngoing(t)) {
                return false;
            }
        }
        return true;
    }

    public boolean isAvailableBetween(LocalTime start, LocalTime limit) {
        if (occupied) {
            return false;
        }
        for (TimeSlot slot : reservedSlots) {
            if (slot.overlaps(start, limit)) {
                return false;
            }
        }
        return true;
    }

    public LocalTime nextFreeTime(LocalTime ref) {
        if (isAvailableAt(ref)) {
            return ref;
        }
        LocalTime best = null;
        for (TimeSlot slot : reservedSlots) {
            if (slot.isOngoing(ref)) {
                if (best == null || slot.getEnd().isBefore(best)) {
                    best = slot.getEnd();
                }
            }
        }
        return best;
    }

    public String describe() {
        String status = occupied ? "Yes" : "No";
        return String.format(
                "Id: %d | Name: %s | Building: %s | Capacity: %d | Occupied: %s",
                getId(), getName(), getBuilding(), capacity, status
        );
    }

    public String describeWithNextFreeTime(LocalTime ref) {
        LocalTime next = nextFreeTime(ref);
        String when = (next == null) ? "Unknown" : next.toString();
        return describe() + " | Next free: " + when;
    }


    @Override
    public String basicInfo() {
        return getName() + " in " + getBuilding();
    }


    public static int getTotalRooms() {
        return totalRooms;
    }
}
