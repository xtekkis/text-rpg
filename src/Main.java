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
        System.out.println("\nWelcome, " + player.getName() + "! Your adventure begins...");
    }
}