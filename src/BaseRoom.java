/**
 * Abstract base class for rooms.
 * Requirement 16: Abstraction.
 */
public abstract class BaseRoom {

    // Encapsulated fields (requirement 18)
    private final int id;
    private String name;
    private String building;

    /**
     * Constructor for BaseRoom.
     * Requirement 14: Constructor.
     * Requirement 21: this keyword.
     */
    public BaseRoom(int id, String name, String building) {
        this.id = id;
        this.name = name;
        this.building = building;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBuilding() {
        return building;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * Abstract method to be implemented by subclasses.
     */
    public abstract String basicInfo();
}
