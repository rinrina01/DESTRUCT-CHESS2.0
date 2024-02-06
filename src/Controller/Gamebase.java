package Controller;

import Model.Map;
import Model.Player;

import java.util.Scanner;
import java.util.Random;

public class Gamebase {
    static public void start() {
        Map mymap = new Map();
        var createMap = mymap.getMatrix();
        System.out.println("         ═══════════════════════════════════════════════════");
        for (int i = 0; i < createMap.length; i++) {
            System.out.print("Ligne: " + i + ' ');
            for (int j = 0; j < createMap[i].length; j++) {
                System.out.print("║    ");
            }
            System.out.println();
            System.out.println("         ═══════════════════════════════════════════════════");
        }
        mymap.spawn(2);
    }
  
    final static Scanner scanner = new Scanner(System.in);
    static Player[] alivePlayers;
    static Map map;

    public static void initGame() {
        /*
         * This function initializes all game related
         * paramaters such as number of players and their
         * position on the map.
         */
        alivePlayers = new Player[] {
                new Player("player1", 6, 5, 0, 'p'),
                new Player("player2", 5, 6, 0, 'q') };

        map.spawn(2); // Spawns the players in the map

        // Index of the randomly chosen starting player
        playRound(new Random().nextInt(alivePlayers.length));
    }

    public static void playRound(int indexStartingPlayer) {
        /*
         * Starts a play cycle and then replays itself.
         * 
         * @param
         */

        // Use getMovement and destructBlock function with all players
        for (int i = 0; i < alivePlayers.length; i++) {
            int index = (indexStartingPlayer + i) % alivePlayers.length;
            System.out.println("C'est au tour de " + alivePlayers[index].getPseudo());
            getMovement(alivePlayers[index]); // Ask player to move
            destroySquare(map); // Ask player to destroy a square
        }

        playRound(indexStartingPlayer);
    }

    public static void getMovement(Player player) {
        /*
         * This function takes and manages
         * player movement input.
         * 
         * @param player of type Player
         */
        String direction;
        while (true) {
            direction = scanner.nextLine();
            if (direction == "z") {
                move(player, map, 'z');
                break;
            } else if (direction == "q") {
                move(player, map, 'q');
                break;
            } else if (direction == "s") {
                move(player, map, 's');
                break;
            } else if (direction == "d") {
                move(player, map, 'd');
                break;
            } else {
                System.out.println("Please enter Z, Q, S or D");
            }
        }
    }

    static void move(Player player, Map map, char direction) {
        /**
         * This function moves the player, changes his values and changes the blocks of
         * the map
         * 
         * @param player   of type Player
         * @param map      of type Map
         * @param drection of type char
         **/
        int playerPosX = player.getPosX();
        int playerPosY = player.getPosY();
        switch (direction) {
            case 'z': // If is UP (Z key)
                if (playerPosY > 0) {
                    map.setSquare(playerPosX, playerPosY, 'a');
                    map.setSquare(playerPosX, playerPosY - 1, player.getSymbol());
                    player.setPosY(playerPosY - 1);
                    System.out.println("z");
                } else {
                    System.out.println("Dommage vous entrez dans un mur, vous ne bougez pas.");
                }
                break;
            case 'q': // If is LEFT (Q key)
                if (playerPosX > 0) {
                    map.setSquare(playerPosX, playerPosY, 'a');
                    map.setSquare(playerPosX - 1, playerPosY, player.getSymbol());
                    player.setPosX(playerPosX - 1);
                    System.out.println("q");
                } else {
                    System.out.println("Dommage vous entrez dans un mur, vous ne bougez pas.");
                }
                break;
            case 's': // If is DOWN (S key)
                if (playerPosY < 9) {
                    map.setSquare(playerPosX, playerPosY, 'a');
                    map.setSquare(playerPosX, playerPosY + 1, player.getSymbol());
                    player.setPosY(playerPosY + 1);
                    System.out.println("s");
                } else {
                    System.out.println("Dommage vous entrez dans un mur, vous ne bougez pas.");
                }
                break;
            case 'd': // If is RIGHT (D key)
                if (playerPosX < 10) {
                    map.setSquare(playerPosX, playerPosY, 'a');
                    map.setSquare(playerPosX + 1, playerPosY, player.getSymbol());
                    player.setPosX(playerPosX + 1);
                    System.out.println("d");
                } else {
                    System.out.println("Dommage vous entrez dans un mur, vous ne bougez pas.");
                }
                break;
        }
    }

    static void destroySquare(Map map) {

    }
}
