import java.time.LocalDateTime;
import java.util.Scanner;


public class StudyRoomFinder {

    private static final int DEFAULT_MINUTES_AHEAD = 30;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        StudyRoomManager manager = new StudyRoomManager();
        manager.loadSampleData();

        boolean running = true;

        while (running) {
            printHeader();
            printMenu();

            System.out.print("Enter choice: ");
            String input = scanner.nextLine();


            String choice = input.trim().toLowerCase();


            switch (choice) {
                case "1":
                    manager.listAllRooms();
                    break;
                case "2":
                    manager.listAvailableNow();
                    break;
                case "3":
                    System.out.print("Check availability within how many minutes? ");
                    int minutes = readInt(scanner, DEFAULT_MINUTES_AHEAD);
                    manager.listAvailableSoon(minutes);
                    break;
                case "4":
                    System.out.print("Enter room id to check in: ");
                    int checkInId = readInt(scanner, -1);
                    manager.checkIn(checkInId);
                    break;
                case "5":
                    System.out.print("Enter room id to check out: ");
                    int checkOutId = readInt(scanner, -1);
                    manager.checkOut(checkOutId);
                    break;
                case "6":
                    manager.suggestStudyTip();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }

        System.out.println("Goodbye. Happy studying!");
        scanner.close();
    }


    private static void printHeader() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("========================================");
        System.out.printf("  StudyRoom Finder - %s%n", now);
        System.out.println("  Total rooms created: " + StudyRoom.getTotalRooms());
        System.out.println("========================================");
    }


    private static void printMenu() {
        System.out.println("1) List all rooms");
        System.out.println("2) Show rooms available now");
        System.out.println("3) Show rooms available soon");
        System.out.println("4) Check in to a room");
        System.out.println("5) Check out of a room");
        System.out.println("6) Suggest a random study tip");
        System.out.println("0) Exit");
    }


    private static int readInt(Scanner scanner, int defaultValue) {
        try {
            String text = scanner.nextLine().trim();
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Using default " + defaultValue);
            return defaultValue;
        }
    }
}
