public class Room {
    private String name;
    private String description;
    private Enemy enemy;
    private Item item;
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    // Constructor
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.enemy = null;
        this.item = null;
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Enemy getEnemy() { return enemy; }
    public Item getItem() { return item; }
    public Room getNorth() { return north; }
    public Room getSouth() { return south; }
    public Room getEast() { return east; }
    public Room getWest() { return west; }

    // Setters
    public void setEnemy(Enemy enemy) { this.enemy = enemy; }
    public void setItem(Item item) { this.item = item; }
    public void setNorth(Room north) { this.north = north; }
    public void setSouth(Room south) { this.south = south; }
    public void setEast(Room east) { this.east = east; }
    public void setWest(Room west) { this.west = west; }

    // Check if room has an enemy
    public boolean hasEnemy() { return enemy != null && enemy.isAlive(); }

    // Check if room has an item
    public boolean hasItem() { return item != null; }

    // Display room info
    public void displayRoom() {
        System.out.println("\n=== " + name + " ===");
        System.out.println(description);

        if (hasEnemy()) {
            System.out.println("Enemy here: " + enemy.getName());
        }
        if (hasItem()) {
            System.out.println("Item here: " + item.getName());
        }

        System.out.println("\nExits:");
        if (north != null) System.out.println("↑ NORTH");
        if (south != null) System.out.println("↓ SOUTH");
        if (east != null) System.out.println("→ EAST");
        if (west != null) System.out.println("← WEST");
    }
}
