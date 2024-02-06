package Controller;

import Model.Map;
import Model.Player;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Gamebase {
    static final Scanner sc = new Scanner(System.in);
    static final ArrayList<Player> alivePlayers = new ArrayList<>();
    static Map map;

    public static void initGame() {
        /*
         * This function initializes all game related
         * paramaters such as number of players and their
         * position on the map.
         */

        map = new Map();

        int userInput;
        do {
            try {
                System.out.println("Enter the number of players (between 2 and 4)");
                userInput = sc.nextInt();
                if ((userInput >= 2) && (userInput <= 4)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("That's not a number...");
            }
        } while (true);

        addPlayers(userInput, userInput); // add players to alivePlayers and in the map
        // System.out.println(map);

        for (char[] row : map.getMatrix()) {
            for (char letter : row) {
                System.out.print(letter);
                System.out.print(' ');
            }
            System.out.println();
        }

        // Index of the randomly chosen starting player
        playRound(new Random().nextInt(alivePlayers.size()));
    }

    public static void addPlayers(int playerNumber, int playerCount) {
        /*
         * Adds players to alivePlayers and puts them in the map list recursively
         * 
         * @param playerNumber the number of the player
         * 
         * @param playerCount the total number of players
         */

        if (playerNumber != 0) { // Stops recursion if playerNumber hits 0
            String userInput;
            boolean alreadyChosen;
            do {
                alreadyChosen = false;
                System.out.println("Enter player" + Integer.valueOf(playerCount - playerNumber + 1) + "'s name");
                userInput = sc.nextLine();

                for (Player player : alivePlayers) {
                    if (player.getPseudo() == userInput) {
                        alreadyChosen = true;
                    }
                }
            } while ((userInput.length() < 2) || (userInput.length() >= 10) || (alreadyChosen));
            // Repeat until player's name has between 2 and 10 characters and is unique

            char[][] matrix = map.getMatrix();
            switch (playerNumber % 2) { // 0 if a player has to be added in the upper spawn row and 1 for the lower one
                case 0: // For the upper row
                    if (matrix[4][5] != 'a') { // If a player is in the middle
                        alivePlayers.get(alivePlayers.size() - 2).setPos(4, 4); // Move the player to the left square
                        alivePlayers.add(new Player(userInput, 4, 6, 's')); // Add a player in the right square
                        matrix[4][4] = 'q';
                        matrix[4][5] = 'a';
                        matrix[4][6] = 's';
                    } else {
                        alivePlayers.add(new Player(userInput, 5, 5, 'q')); // Add a player in the middle square
                        matrix[4][5] = 'q';
                    }
                    break;
                case 1: // For the lower row
                    if (matrix[5][5] != 'a') { // If a player is in the middle
                        alivePlayers.get(alivePlayers.size() - 2).setPos(5, 4); // Move the player to the left square
                        alivePlayers.add(new Player(userInput, 5, 6, 'r')); // Add a player in the right square
                        matrix[5][4] = 'p';
                        matrix[5][5] = 'a';
                        matrix[5][6] = 'r';
                    } else {
                        alivePlayers.add(new Player(userInput, 5, 5, 'p')); // Add a player in the middle square
                        matrix[5][5] = 'p';
                    }
                    break;
            }
            map.setMatrix(matrix);
            addPlayers(playerNumber - 1, playerCount);
        }
    }

    public static void playRound(int indexStartingPlayer) {
        /*
         * Starts a play cycle and then replays itself.
         * 
         * @param
         */

        // Use getMovement and destructBlock function with all players
        for (int i = 0; i < alivePlayers.size(); i++) {
            int index = (indexStartingPlayer + i) % alivePlayers.size();
            System.out.println("C'est au tour de " + alivePlayers.get(index).getPseudo());
            getMovement(alivePlayers.get(index)); // Ask player to move
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
            direction = sc.nextLine();
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
