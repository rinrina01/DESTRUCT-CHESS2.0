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
                sc.nextLine();
                System.out.println("That's not an integer...");
            }
        } while (true);

        addPlayers(userInput); // add players to alivePlayers and in the map

        // Index of the randomly chosen starting player
        playRound(new Random().nextInt(alivePlayers.size()));
    }

    public static void addPlayers(int playerCount) {
        switch (playerCount) {
            case 2:
                add2Players();
                break;
            case 3:
                add3Players();
                break;
            case 4:
                add4Players();
                break;
        }

    }

    public static void add2Players() {
        /**
         * Add 2 players to the map and to the alive players array
         */

        String userInput;
        boolean alreadyChosen;
        char[][] matrix = map.getMatrix();

        // add player1
        do {
            System.out.println("Enter player1's name");
            userInput = sc.nextLine();
        } while ((userInput.length() < 2) || (userInput.length() >= 10));
        alivePlayers.add(new Player(userInput, 5, 4, 'p'));
        matrix[4][5] = 'p';

        // add player2
        do {
            alreadyChosen = false;
            System.out.println("Enter player2's name");
            userInput = sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo() == userInput) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);
        alivePlayers.add(new Player(userInput, 5, 5, 'q'));
        matrix[5][5] = 'q';

        map.setMatrix(matrix);
    }

    public static void add3Players() {
        /**
         * Add 3 players to the map and to the alive players array
         */

        String userInput;
        boolean alreadyChosen;
        char[][] matrix = map.getMatrix();

        // add player1
        do {
            System.out.println("Enter player1's name");
            userInput = sc.nextLine();
        } while ((userInput.length() < 2) || (userInput.length() >= 10));

        alivePlayers.add(new Player(userInput, 5, 4, 'p'));
        matrix[4][5] = 'p';

        // add player2
        do {
            alreadyChosen = false;
            System.out.println("Enter player2's name");
            userInput = sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo() == userInput) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);

        alivePlayers.add(new Player(userInput, 4, 5, 'q'));
        matrix[5][4] = 'q';

        // add player3
        do {
            alreadyChosen = false;
            System.out.println("Enter player3's name");
            userInput = sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo() == userInput) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);

        alivePlayers.add(new Player(userInput, 6, 5, 'r'));
        matrix[5][6] = 'r';

        map.setMatrix(matrix);
    }

    public static void add4Players() {
        /**
         * Add 4 players to the map and to the alive players array
         */

        String userInput;
        boolean alreadyChosen;
        char[][] matrix = map.getMatrix();

        // add player1
        do {
            System.out.println("Enter player1's name");
            userInput = sc.nextLine();
        } while ((userInput.length() < 2) || (userInput.length() >= 10));

        alivePlayers.add(new Player(userInput, 4, 4, 'p'));
        matrix[4][4] = 'p';

        // add player2
        do {
            alreadyChosen = false;
            System.out.println("Enter player2's name");
            userInput = sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo() == userInput) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);

        alivePlayers.add(new Player(userInput, 4, 5, 'q'));
        matrix[5][4] = 'q';

        // add player3
        do {
            alreadyChosen = false;
            System.out.println("Enter player3's name");
            userInput = sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo() == userInput) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);

        alivePlayers.add(new Player(userInput, 6, 4, 'r'));
        matrix[4][6] = 'r';

        // add player4
        do {
            alreadyChosen = false;
            System.out.println("Enter player4's name");
            userInput = sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo() == userInput) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);

        alivePlayers.add(new Player(userInput, 6, 5, 's'));
        matrix[5][6] = 's';

        map.setMatrix(matrix);
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
            System.out.println(map);
            System.out.println("C'est au tour de " + alivePlayers.get(index).getPseudo());
            getMovement(alivePlayers.get(index)); // Ask player to move
            System.out.println(map);
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
            if ((direction.equals("z")) || (direction.equals("Z"))) {
                move(player, map, 'z');
                break;
            } else if ((direction.equals("q")) || (direction.equals("Q"))) {
                move(player, map, 'q');
                break;
            } else if ((direction.equals("s")) || (direction.equals("S"))) {
                move(player, map, 's');
                break;
            } else if ((direction.equals("d")) || (direction.equals("D"))) {
                move(player, map, 'd');
                break;
            } else {
                System.out.println(map);
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
                } else {
                    System.out.println("Dommage vous entrez dans un mur, vous ne bougez pas.");
                }

                break;
            case 'q': // If is LEFT (Q key)
                if (playerPosX > 0) {
                    map.setSquare(playerPosX, playerPosY, 'a');
                    map.setSquare(playerPosX - 1, playerPosY, player.getSymbol());
                    player.setPosX(playerPosX - 1);
                } else {
                    System.out.println("Dommage vous entrez dans un mur, vous ne bougez pas.");
                }

                break;
            case 's': // If is DOWN (S key)
                if (playerPosY < 9) {
                    map.setSquare(playerPosX, playerPosY, 'a');
                    map.setSquare(playerPosX, playerPosY + 1, player.getSymbol());
                    player.setPosY(playerPosY + 1);
                } else {
                    System.out.println("Dommage vous entrez dans un mur, vous ne bougez pas.");
                }

                break;
            case 'd': // If is RIGHT (D key)
                if (playerPosX < 10) {
                    map.setSquare(playerPosX, playerPosY, 'a');
                    map.setSquare(playerPosX + 1, playerPosY, player.getSymbol());
                    player.setPosX(playerPosX + 1);
                } else {
                    System.out.println("Dommage vous entrez dans un mur, vous ne bougez pas.");
                }

                break;
        }
    }

    static void destroySquare(Map map) {

    }
}
