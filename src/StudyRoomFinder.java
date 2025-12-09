import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Main program class for the StudyRoom Finder application.
 * Handles user input, interactive menu display,
 * and coordinates interaction with StudyRoomManager.
 */
public class StudyRoomFinder {

    /** Default number of minutes to check ahead for availability. */
    private static final int DEFAULT_MINUTES_AHEAD = 30;

    /**
     * Entry point for the application. Displays menu and runs event loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudyRoomManager manager = new StudyRoomManager();
        manager.loadSampleData();

        boolean running = true;

        while (running) {
            printHeader();
            printMenu();

            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim().toLowerCase();

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
                    System.out.print("Enter room ID to check in: ");
                    manager.checkIn(readInt(scanner, -1));
                    break;
                case "5":
                    System.out.print("Enter room ID to check out: ");
                    manager.checkOut(readInt(scanner, -1));
                    break;
                case "6":
                    manager.suggestStudyTip();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.\n");
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    /**
     * Prints the program header showing the current date and total rooms.
     */
    private static void printHeader() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("========================================");
        System.out.printf("StudyRoom Finder - %s%n", now);
        System.out.println("========================================");
    }

    /**
     * Prints the user menu.
     */
    private static void printMenu() {
        System.out.println("1) List all rooms");
        System.out.println("2) View available rooms now");
        System.out.println("3) View rooms available soon");
        System.out.println("4) Check in to a room");
        System.out.println("5) Check out of a room");
        System.out.println("6) Random study tip");
        System.out.println("0) Exit");
    }

    /**
     * Reads an integer safely from the user.
     *
     * @param scanner scanner instance
     * @param fallback fallback value if input invalid
     * @return parsed integer or fallback
     */
    private static int readInt(Scanner scanner, int fallback) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return fallback;
        }
    }
}
