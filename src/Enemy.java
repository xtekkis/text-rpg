public class Enemy {
    private String name;
    private int health;
    private int damage;
    private int goldReward;

    // Constructor
    public Enemy(String name, int health, int damage, int goldReward) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.goldReward = goldReward;
    }

    // Getters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getDamage() { return damage; }
    public int getGoldReward() { return goldReward; }

    // Take damage
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    // Check if enemy is alive
    public boolean isAlive() {
        return health > 0;
    }

    // Display enemy stats
    public void displayStats() {
        System.out.println("Enemy: " + name);
        System.out.println("Health: " + health);
        System.out.println("Damage: " + damage);
    }
}