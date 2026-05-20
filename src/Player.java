public class Player {
    private String name;
    private int health;
    private int gold;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.health = 100;
        this.gold = 0;
    }

    // Getters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getGold() { return gold; }

    // Take damage
    public void takeDamage(int damage) {
        health -= damage;
    }

    // Add gold
    public void addGold(int amount) {
        gold += amount;
    }

    // Check if player is alive
    public boolean isAlive() {
        return health > 0;
    }

    // Display player stats
    public void displayStats() {
        System.out.println("Name: " + name);
        System.out.println("Health: " + health);
        System.out.println("Gold: " + gold);
    }
}
