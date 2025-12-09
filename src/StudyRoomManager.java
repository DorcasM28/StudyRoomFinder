import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Handles creation, lookup, and management of StudyRoom objects.
 * Provides methods for checking availability, checking in/out,
 * and generating random study tips.
 */
public class StudyRoomManager {

    /** List of all study rooms. */
    private final List<StudyRoom> rooms = new ArrayList<>();

    /** Array of study tips (demonstrates array requirement). */
    private final String[] studyTips = {
            "Arrive a few minutes early to settle in.",
            "Turn off notifications for better focus.",
            "Use active recall instead of rereading.",
            "Take breaks every 25–30 minutes."
    };

    /** Used to choose random study tips. */
    private final Random random = new Random();

    /**
     * Creates sample study rooms and populates their schedules.
     */
    public void loadSampleData() {
        StudyRoom r1 = new StudyRoom(1, "Pod 101", "Library", 4);
        StudyRoom r2 = new StudyRoom(2, "Room 202", "Engineering", 6);
        StudyRoom r3 = new StudyRoom(3, "Study Nook 3A", "Student Center", 2);

        r1.addReservedSlot(LocalTime.of(9, 0), LocalTime.of(11, 0));
        r1.addReservedSlot(LocalTime.of(14, 0), LocalTime.of(15, 30));

        r2.addReservedSlot(LocalTime.of(10, 0), LocalTime.of(12, 0));
        r2.addReservedSlot(LocalTime.of(16, 0), LocalTime.of(17, 0));

        r3.addReservedSlot(LocalTime.of(13, 0), LocalTime.of(14, 0));

        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);
    }

    /**
     * Displays all rooms in the system.
     */
    public void listAllRooms() {
        System.out.println("\nAll rooms:");
        for (StudyRoom room : rooms) {
            System.out.println(room.describe());
        }
        System.out.println();
    }

    /**
     * Displays all rooms available at the current time.
     */
    public void listAvailableNow() {
        LocalTime now = LocalTime.now();
        System.out.println("\nRooms available now: ");
        boolean found = false;

        for (StudyRoom room : rooms) {
            if (room.isAvailableAt(now)) {
                System.out.println(room.describe());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms are free right now.");
        }
        System.out.println();
    }

    /**
     * Lists rooms available within the next 30 minutes.
     * Demonstrates method overloading.
     */
    public void listAvailableSoon() {
        listAvailableSoon(30);
    }

    /**
     * Lists rooms available within a given number of minutes.
     *
     * @param minutesAhead time window to check
     */
    public void listAvailableSoon(int minutesAhead) {
        int safeMinutes = Math.max(0, minutesAhead); /** Math method */

        LocalTime now = LocalTime.now();
        LocalTime limit = now.plusMinutes(safeMinutes);

        System.out.println("\nRooms free soon:");
        boolean found = false;

        for (StudyRoom room : rooms) {
            if (room.isAvailableBetween(now, limit)) {
                System.out.println(room.describeWithNextFreeTime(now));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available soon.");
        }
        System.out.println();
    }

    /**
     * Checks a user into a room if available.
     *
     * @param roomId ID of room to occupy
     */
    public void checkIn(int roomId) {
        StudyRoom room = findRoomById(roomId);
        if (room == null) {
            System.out.println("Room not found.\n");
            return;
        }
        LocalTime now = LocalTime.now();
        if (!room.isAvailableAt(now)) {
            System.out.println("Room is unavailable.\n");
            return;
        }
        room.setOccupied(true);
        System.out.println("Checked in successfully.\n");
    }

    /**
     * Checks a user out of a room.
     *
     * @param roomId room ID to free
     */
    public void checkOut(int roomId) {
        StudyRoom room = findRoomById(roomId);
        if (room == null) {
            System.out.println("Room not found.\n");
            return;
        }
        if (!room.isOccupied()) {
            System.out.println("Room is not currently occupied.\n");
            return;
        }
        room.setOccupied(false);
        System.out.println("Checked out successfully.\n");
    }

    /**
     * Finds a StudyRoom by its ID.
     *
     * @param id room identifier
     * @return StudyRoom object or null if not found
     */
    private StudyRoom findRoomById(int id) {
        for (StudyRoom room : rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }

    /**
     * Prints a randomly selected study tip.
     */
    public void suggestStudyTip() {
        int index = random.nextInt(studyTips.length);
        System.out.println("\nStudy tip: " + studyTips[index] + "\n");
    }
}
