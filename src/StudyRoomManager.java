import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StudyRoomManager {

    private final List<StudyRoom> rooms = new ArrayList<>();


    private final String[] studyTips = {
            "Arrive ten minutes early to settle in.",
            "Turn off notifications to stay focused.",
            "Use active recall instead of rereading notes.",
            "Take a short break every 25 to 30 minutes."
    };


    private final Random random = new Random();


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

    public void listAllRooms() {
        System.out.println("\nAll rooms:");
        for (StudyRoom room : rooms) {
            System.out.println(room.describe());
        }
        System.out.println();
    }

    public void listAvailableNow() {
        LocalTime now = LocalTime.now();
        System.out.println("\nRooms available now at " + now.withSecond(0).withNano(0) + ":");
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


    public void listAvailableSoon() {
        listAvailableSoon(30);
    }

    public void listAvailableSoon(int minutesAhead) {

        int safeMinutes = Math.max(0, minutesAhead);

        LocalTime now = LocalTime.now();
        LocalTime limit = now.plusMinutes(safeMinutes);

        System.out.println("\nRooms available between " + now.withSecond(0).withNano(0)
                + " and " + limit.withSecond(0).withNano(0) + ":");

        boolean found = false;
        for (StudyRoom room : rooms) {
            if (room.isAvailableBetween(now, limit)) {
                System.out.println(room.describeWithNextFreeTime(now));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available in this window.");
        }
        System.out.println();
    }

    public void checkIn(int roomId) {
        StudyRoom room = findRoomById(roomId);
        if (room == null) {
            System.out.println("Room id " + roomId + " not found.\n");
            return;
        }
        LocalTime now = LocalTime.now();
        if (!room.isAvailableAt(now)) {
            System.out.println("Room is reserved or already occupied.\n");
            return;
        }
        room.setOccupied(true);
        System.out.println("Checked in to " + room.basicInfo() + ".\n");
    }

    public void checkOut(int roomId) {
        StudyRoom room = findRoomById(roomId);
        if (room == null) {
            System.out.println("Room id " + roomId + " not found.\n");
            return;
        }
        if (!room.isOccupied()) {
            System.out.println("Room is not currently occupied.\n");
            return;
        }
        room.setOccupied(false);
        System.out.println("Checked out of " + room.basicInfo() + ".\n");
    }


    private StudyRoom findRoomById(int id) {
        for (StudyRoom room : rooms) {
            if (room.getId() == id && room.getName().length() > 0) {
                return room;
            }
        }
        return null;
    }


    public void suggestStudyTip() {
        int index = random.nextInt(studyTips.length);
        String tip = studyTips[index];
        System.out.println("\nStudy tip: " + tip + "\n");
    }
}

