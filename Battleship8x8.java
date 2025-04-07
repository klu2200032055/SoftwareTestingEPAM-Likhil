package JavaBasic;

import java.util.Scanner;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        int index = convertToBitIndex(shot);
        long mask = 1L << (63 - index);
        shots |= mask;
        return (ships & mask) != 0;
    }

    public String state() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int index = row * 8 + col;
                long mask = 1L << (63 - index);
                boolean isShip = (ships & mask) != 0;
                boolean isShot = (shots & mask) != 0;

                if (isShip && isShot) sb.append('☒');
                else if (isShip) sb.append('☐');
                else if (isShot) sb.append('×');
                else sb.append('.');
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private int convertToBitIndex(String shot) {
        char colChar = shot.toUpperCase().charAt(0);
        int row = Integer.parseInt(shot.substring(1)) - 1;
        int col = colChar - 'A';
        return row * 8 + col;
    }

    // Main method to allow user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample map with ships
        long map = 0b11110000_00000111_00000000_00110000_00000010_01000000_00000000_00000000L;
        Battleship8x8 game = new Battleship8x8(map);

        System.out.println("Enter shots (e.g., A1, B2), type 'done' to finish:");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            boolean hit = game.shoot(input);
            System.out.println(hit ? "Hit!" : "Miss!");
        }

        System.out.println("\nFinal Board:");
        System.out.println(game.state());

        scanner.close();
    }
}
