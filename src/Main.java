import java.util.Scanner;

public class Main {
    private static Player player;
    private static Room currentRoom;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("       Welcome to Text RPG      ");
        System.out.println("================================");
        System.out.print("Enter your username: ");
        String name = scanner.nextLine();

        // Create player
        player = new Player(name);

        // Build the world
        buildWorld();

        System.out.println("\nWelcome, " + player.getName() + "! Your adventure begins...");
    }

    private static void buildWorld() {
        // Create rooms
        Room entrance = new Room("Entrance", "You stand at the entrance of a dark dungeon.");
        Room hallway = new Room("Hallway", "A long dark hallway stretches before you.");
        Room treasury = new Room("Treasury", "A dusty room filled with old chests.");
        Room bossRoom = new Room("Boss Chamber", "A massive chamber. Something evil lurks here.");

        // Connect rooms
        entrance.setNorth(hallway);
        hallway.setSouth(entrance);
        hallway.setNorth(bossRoom);
        hallway.setEast(treasury);
        treasury.setWest(hallway);
        bossRoom.setSouth(hallway);

        // Create and place enemies
        hallway.setEnemy(new Enemy("Goblin", 30, 10, 15));
        bossRoom.setEnemy(new Enemy("Dark Knight", 80, 25, 50));

        // Create and place items
        entrance.setItem(new Item("Health Potion", "Restores 30 HP", 30, 10));
        treasury.setItem(new Item("Elixir", "Restores 50 HP", 50, 25));

        // Start in entrance
        currentRoom = entrance;
    }
}