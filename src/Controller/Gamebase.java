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
    static final ArrayList<Player> deadPlayers = new ArrayList<>();

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
        alivePlayers.add(new Player(userInput, 5, 4, 'p', map));
        matrix[4][5] = 'p';

        // add player2
        do {
            alreadyChosen = false;
            System.out.println("Enter player2's name");
            userInput = sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo().equals(userInput)) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);
        alivePlayers.add(new Player(userInput, 5, 5, 'q', map));
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

        alivePlayers.add(new Player(userInput, 5, 4, 'p', map));
        matrix[4][5] = 'p';

        // add player2
        do {
            alreadyChosen = false;
            System.out.println("Enter player2's name");
            userInput = sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo().equals(userInput)) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);

        alivePlayers.add(new Player(userInput, 4, 5, 'q', map));
        matrix[5][4] = 'q';

        // add player3
        do {
            alreadyChosen = false;
            System.out.println("Enter player3's name");
            userInput = sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo().equals(userInput)) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);

        alivePlayers.add(new Player(userInput, 6, 5, 'r', map));
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

        alivePlayers.add(new Player(userInput, 4, 4, 'p', map));
        matrix[4][4] = 'p';

        // add player2
        do {
            alreadyChosen = false;
            System.out.println("Enter player2's name");
            userInput = sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo().equals(userInput)) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);

        alivePlayers.add(new Player(userInput, 4, 5, 'q', map));
        matrix[5][4] = 'q';

        // add player3
        do {
            alreadyChosen = false;
            System.out.println("Enter player3's name");
            userInput = sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo().equals(userInput)) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);

        alivePlayers.add(new Player(userInput, 6, 4, 'r', map));
        matrix[4][6] = 'r';

        // add player4
        do {
            alreadyChosen = false;
            System.out.println("Enter player4's name");
            userInput = sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo().equals(userInput)) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);

        alivePlayers.add(new Player(userInput, 6, 5, 's', map));
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

            // Verify if any player is surrounded (game over)
            for (Player player : alivePlayers) {
                System.out.println("player can move : "+player.canMove());
                if (!player.canMove()) {
                    deadPlayers.add(player);
                    System.out.println(player.getPseudo() + " has lost.");
                }
            }
            alivePlayers.removeAll(deadPlayers);

            if (alivePlayers.size() == 1) {
                System.out.println(alivePlayers.get(0).getPseudo() + " has  won.");
            } else {
                playRound(indexStartingPlayer);
            }
        }
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
                player.move('z');
                break;
            } else if ((direction.equals("q")) || (direction.equals("Q"))) {
                player.move('q');
                break;
            } else if ((direction.equals("s")) || (direction.equals("S"))) {
                player.move('s');
                break;
            } else if ((direction.equals("d")) || (direction.equals("D"))) {
                player.move('d');
                break;
            } else {
                System.out.println(map);
                System.out.println("Please enter Z, Q, S or D");
            }
        }
    }

    static void destroySquare(Map map) {
        int row;
        String columnInput;
        int column;
        char[][] matrix = map.getMatrix();
        do {
            do {
                try {
                    column = 0;
                    System.out.println("Enter the column [A - K]");
                    columnInput = sc.nextLine();

                    if (columnInput.length() == 1) {
                        short asciiValue = (short) (columnInput.charAt(0));
                        if (asciiValue >= 97) {
                            column = asciiValue - 97;

                            if ((column >= 0) && (column <= 10)) {
                                break;
                            }
                        } else if (asciiValue >= 65) {
                            column = asciiValue - 65;

                            if ((column >= 0) && (column <= 10)) {
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    sc.nextLine();
                    System.out.println("Entered value is not correct");
                }
            } while (true);

            do {
                try {
                    System.out.println("Enter the row [0 - 9]");
                    row = sc.nextInt();
                    if ((row >= 0) && (row <= 9)) {
                        break;
                    }
                } catch (Exception e) {
                    sc.nextLine();
                    System.out.println("Entered value is not correct");
                }
            } while (true);

            if (matrix[row][column] == 'a') {
                System.out.println("Destructed square : " + columnInput + String.valueOf(row));
                matrix[row][column] = 'd';
                System.out.println(map);
                // map.setMatrix(matrix);
                break;
            } else {
                System.out.println("Square is not available, try again");
            }
        } while (true);
    }
}
