import java.util.Scanner;
import java.util.Random;

public class Main {
    private static Player player;
    private static Room currentRoom;
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

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

        // Start game loop
        gameLoop();
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

    private static void gameLoop() {
        while (player.isAlive()) {
            currentRoom.displayRoom();
            player.displayStats();

            System.out.print("\nWhat do you do? (go north/south/east/west, fight, take, quit): ");
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.equals("quit")) {
                System.out.println("Thanks for playing!");
                break;
            } else if (input.startsWith("go ")) {
                move(input.substring(3));
            } else if (input.equals("fight")) {
                fight();
            } else if (input.equals("take")) {
                takeItem();
            } else {
                System.out.println("Invalid command. Try again.");
            }

            // Check if player is dead
            if (!player.isAlive()) {
                System.out.println("\nYOU DIED");
            }
        }
    }

    private static void move(String direction) {
        Room nextRoom = null;

        switch (direction) {
            case "north": nextRoom = currentRoom.getNorth(); break;
            case "south": nextRoom = currentRoom.getSouth(); break;
            case "east": nextRoom = currentRoom.getEast(); break;
            case "west": nextRoom = currentRoom.getWest(); break;
            default: System.out.println("Invalid direction."); return;
        }

        if (nextRoom == null) {
            System.out.println("Does not open from this side");
        } else if (currentRoom.hasEnemy()) {
            System.out.println("Defeat the enemy to continue!");
        } else {
            currentRoom = nextRoom;
            System.out.println("You move " + direction + ".");
        }
    }

    private static void fight() {
        if (!currentRoom.hasEnemy()) {
            System.out.println("There is no enemy here.");
            return;
        }

        Enemy enemy = currentRoom.getEnemy();
        boolean isBoss = enemy.getName().equals("Dark Knight");
        System.out.println("\nFighting the " + enemy.getName() + "!");

        if (!isBoss) {
            System.out.println("(Type 'roll' to attack or 'flee' to escape)");
        } else {
            System.out.println("(Type 'roll' to attack - you cannot flee a boss!)");
        }

        while (player.isAlive() && enemy.isAlive()) {
            System.out.print("\nYour move: ");
            String action = scanner.nextLine().toLowerCase().trim();

            if (action.equals("flee")) {
                if (isBoss) {
                    System.out.println("You cannot flee from the " + enemy.getName() + "!");
                } else {
                    int fleeDamage = 10;
                    player.takeDamage(fleeDamage);
                    System.out.println("You flee and take " + fleeDamage + " damage!");
                    System.out.println("Your health: " + player.getHealth());
                    break;
                }
            } else if (action.equals("roll")) {
                System.out.println("Rolling...");
                try { Thread.sleep(1500); } catch (InterruptedException e) {}
                int playerRoll = random.nextInt(20) + 1;
                System.out.println("You rolled a " + playerRoll + "!");

                if (playerRoll >= 10) {
                    int playerDamage = playerRoll;
                    enemy.takeDamage(playerDamage);
                    System.out.println("Hit! You deal " + playerDamage + " damage to the " + enemy.getName());
                    System.out.println(enemy.getName() + " health: " + enemy.getHealth());
                } else {
                    System.out.println("You missed!");
                }

                if (!enemy.isAlive()) {
                    System.out.println("You defeated the " + enemy.getName() + "!");
                    player.addGold(enemy.getGoldReward());
                    System.out.println("You earned " + enemy.getGoldReward() + " gold!");
                    break;
                }

                System.out.println("\n" + enemy.getName() + " is rolling...");
                try { Thread.sleep(1500); } catch (InterruptedException e) {}
                int enemyRoll = random.nextInt(20) + 1;
                System.out.println(enemy.getName() + " rolled a " + enemyRoll + "!");

                if (enemyRoll >= 10) {
                    int enemyDamage = enemy.getDamage();
                    player.takeDamage(enemyDamage);
                    System.out.println("Hit! " + enemy.getName() + " deals " + enemyDamage + " damage to you!");
                    System.out.println("Your health: " + player.getHealth());
                } else {
                    System.out.println(enemy.getName() + " missed!");
                }
            } else {
                System.out.println("Invalid action. Type 'roll' or 'flee'.");
            }
        }
    }

    private static void takeItem() {
        if (!currentRoom.hasItem()) {
            System.out.println("There is no item here.");
            return;
        }

        Item item = currentRoom.getItem();
        System.out.println("You picked up " + item.getName() + "!");
        System.out.println(item.getDescription());

        if (item.getHealAmount() > 0) {
            player.heal(item.getHealAmount());
        }

        // Remove item from room after taking it
        currentRoom.setItem(null);
    }
}