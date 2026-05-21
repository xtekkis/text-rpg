public class Item {
    private String name;
    private String description;
    private int healAmount;
    private int value;

    // Constructor
    public Item(String name, String description, int healAmount, int value) {
        this.name = name;
        this.description = description;
        this.healAmount = healAmount;
        this.value = value;
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getHealAmount() { return healAmount; }
    public int getValue() { return value; }

    // Display item info
    public void displayInfo() {
        System.out.println(name + " - " + description);
        if (healAmount > 0) {
            System.out.println("Heals: " + healAmount + " HP");
        }
        System.out.println("Value: " + value + " gold");
    }
}