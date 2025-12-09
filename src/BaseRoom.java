/**
 * Represents a generic room structure on campus.
 * This is an abstract parent class that defines core attributes
 * such as room ID, room name, and building location.
 * Subclasses must implement the basicInfo() method.
 *
 * </p> Requirements met: abstraction, inheritance, encapsulation.</p>
 */
public abstract class BaseRoom {

    /** Unique ID assigned to the room. */
    private final int id;

    /** User-friendly room name. */
    private String name;

    /** Building in which the room is located. */
    private String building;

    /**
     * Constructs a BaseRoom with essential identifying information.
     *
     * @param id       unique room identifier
     * @param name     name of the room
     * @param building building where the room is located
     */
    public BaseRoom(int id, String name, String building) {
        this.id = id;
        this.name = name;
        this.building = building;
    }

    /**
     * Returns the unique ID of the room.
     *
     * @return room ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the room name.
     *
     * @return name of the room
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the room name.
     *
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the building where the room is located.
     *
     * @return building name
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Updates the building location for this room.
     *
     * @param building new building name
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * Returns a short summary describing the room.
     * Must be implemented by subclasses.
     *
     * @return basic room information
     */
    public abstract String basicInfo();
}
