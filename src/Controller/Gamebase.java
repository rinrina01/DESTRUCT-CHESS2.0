package Controller;

import Model.Map;
import Model.Player;
import Vue.Cli;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Gamebase {
    static final ArrayList<Player> alivePlayers = new ArrayList<>();
    static final ArrayList<Player> deadPlayers = new ArrayList<>();
    static Map map;

    public static void initGame() {
        /*
         * This function intitalizes all game related paramaters such as number of
         * players and their position on the map.
         */

        map = new Map();
        int userInput;

        do {
            try {
                System.out.println("Enter the number of players (between 2 and 4)");
                userInput = Cli.sc.nextInt();

                if ((userInput >= 2) && (userInput <= 4)) {
                    break;
                }
            } catch (Exception e) {
                Cli.sc.next();
                System.out.println("That's not an integer...");
            }
        } while (true);

        addPlayers(userInput); // add players to alivePlayers and in the map
        playRound(new Random().nextInt(alivePlayers.size())); // index of the randomly chosen starting player & start
                                                              // round
    }

    static void addPlayers(int playerCount) {
        /*
         * This function uses the addPlayers functions depending on playerCount.
         * 
         * @param playerCount : number of players previously selected by the user
         */

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

    static void add2Players() {
        /*
         * This function adds 2 players to the map and
         * stores them in the list containing all alive players
         * for the current round.
         */

        String userInput;
        boolean alreadyChosen;
        char[][] matrix = map.getMatrix();

        // add player1
        do {
            System.out.println("Enter player1's name");
            userInput = Cli.sc.nextLine();
        } while ((userInput.length() < 2) || (userInput.length() >= 10)); // username
        alivePlayers.add(new Player(userInput, 5, 4, 'p', map));
        matrix[4][5] = 'p';

        // add player2
        do {
            alreadyChosen = false;
            System.out.println("Enter player2's name");
            userInput = Cli.sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo().equals(userInput)) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);
        alivePlayers.add(new Player(userInput, 5, 5, 'q', map));
        matrix[5][5] = 'q';
    }

    static void add3Players() {
        /*
         * This function adds 3 players to the map and
         * stores them in the list containing all alive players
         * for the current round.
         */

        String userInput;
        boolean alreadyChosen;
        char[][] matrix = map.getMatrix();

        // add player1
        do {
            System.out.println("Enter player1's name");
            userInput = Cli.sc.nextLine();
        } while ((userInput.length() < 2) || (userInput.length() >= 10));

        alivePlayers.add(new Player(userInput, 5, 4, 'p', map));
        matrix[4][5] = 'p';

        // add player2
        do {
            alreadyChosen = false;
            System.out.println("Enter player2's name");
            userInput = Cli.sc.nextLine();

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
            userInput = Cli.sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo().equals(userInput)) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);

        alivePlayers.add(new Player(userInput, 6, 5, 'r', map));
        matrix[5][6] = 'r';
    }

    static void add4Players() {
        /**
         * This function adds 4 players to the map and
         * stores them in the list containing all alive players
         * for the current round.
         */

        String userInput;
        boolean alreadyChosen;
        char[][] matrix = map.getMatrix();

        // add player1
        do {
            System.out.println("Enter player1's name");
            userInput = Cli.sc.nextLine();
        } while ((userInput.length() < 2) || (userInput.length() >= 10));

        alivePlayers.add(new Player(userInput, 4, 4, 'p', map));
        matrix[4][4] = 'p';

        // add player2
        do {
            alreadyChosen = false;
            System.out.println("Enter player2's name");
            userInput = Cli.sc.nextLine();

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
            userInput = Cli.sc.nextLine();

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
            userInput = Cli.sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo().equals(userInput)) {
                    alreadyChosen = true;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);

        alivePlayers.add(new Player(userInput, 6, 5, 's', map));
        matrix[5][6] = 's';
    }

    static void getMovement(Player player) {
        /*
         * This function takes and manages player movement inputs.
         * 
         * @param player : of type Player
         */

        String direction;

        while (true) {
            direction = Cli.sc.nextLine();
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
        /*
         * This function asks player to enter a square and then destroy it.
         * 
         * @param map : of type Map
         */

        int row;
        String columnInput;
        int column;
        char[][] matrix = map.getMatrix();

        do { // ask for column and row until player enters a free square
            do { // get column until it is valid
                try {
                    column = 0;
                    System.out.println("Enter the column [A - K]");
                    columnInput = Cli.sc.nextLine();

                    if (columnInput.length() == 1) {
                        short asciiValue = (short) (columnInput.charAt(0)); // convert char to ascii value
                        if (asciiValue >= 97) { // if character is a uppercase letter
                            column = asciiValue - 97; // set as number in the alphabet

                            if ((column >= 0) && (column <= 10)) { // check if column number is valid
                                break;
                            }
                        } else if (asciiValue >= 65) { // if character is a lowercase letter
                            column = asciiValue - 65; // set as number in the alphabet

                            if ((column >= 0) && (column <= 10)) {
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    Cli.sc.nextLine();
                    System.out.println("Entered value is not correct");
                }
            } while (true);

            do { // get row until it is valid
                try {
                    System.out.println("Enter the row [0 - 9]");
                    row = Cli.sc.nextInt();
                    if ((row >= 0) && (row <= 9)) { // check if row number is valid
                        break;
                    }
                } catch (Exception e) {
                    Cli.sc.nextLine();
                    System.out.println("Entered value is not correct");
                }
            } while (true);

            if (matrix[row][column] == 'a') { // if square is free
                System.out.println("Destructed square : " + columnInput + String.valueOf(row));
                matrix[row][column] = 'd'; // set square as destroyed
                System.out.println(map);
                break;
            } else {
                System.out.println("Square is not available, try again");
            }
        } while (true);
    }

    public static void easterEgg() {
        System.out.println("                    .-====--:.\n" +
                "                .:=#%%%%%%%%%%%#+-\n" +
                "              :*###%%%%%%%%%%%%%%%%+\n" +
                "         .:.:*######%%%%%#%%%%%%%%%%#:\n" +
                "     :=+#######*++**#######%##%%%%%%%%-\n" +
                "     *#######+:::::-=+*##############%%%#*+-.\n" +
                "     -#######=:::::-==+########++===-=+#%#%#=\n" +
                "      -######=-::::==+##*+==+++*+=+=---+%#**\n" +
                "      .######*===+**##*==-:===-=**=-:::+%#=\n" +
                " .:-==+#####%%#######*+=-=-----=*##+==+#%#\n" +
                "#############%##%####*+=--:::--=+*#%**%#%-\n" +
                "*####################*==--::---=+*#%##*+=\n" +
                "*****##############*+=------::--=*##*#=\n" +
                "*****####%#####%#++****+====--===+***#*\n." +
                "*****#####%%############********++*+*#*\n." +
                "****######%%%%########****+****+****##*\n." +
                "****##%####%%%%%########**********####+\n" +
                "***##%%%###%%%%%%#####*#####******####=\n" +
                "==*##%%%%##%%%%%%############***#####*\n" +
                ":-+*##%%%%##%%%%%%###################:\n" +
                " .-*###%%%###%%%%#################*##.\n");
        System.out.println("You tied the game, Pixelle is not happy :(");
        System.exit(0);
    }

    static void playRound(int indexStartingPlayer) {
        /*
         * Starts a play cycle and then repeats itself until only one player is alive.
         * 
         * @param indexStartingPlayer : the number of the player playing first
         */

        boolean gameOver = false;
        // use getMovement and destructBlock function with all players
        for (int i = 0; i < alivePlayers.size(); i++) {
            System.out.println(map);

            int index = (indexStartingPlayer + i) % alivePlayers.size();
            System.out.println("Turn of " + alivePlayers.get(index).getPseudo());
            getMovement(alivePlayers.get(index)); // ask player to move

            System.out.println(map);

            destroySquare(map); // ask player to destroy a square

            // verify if any player is surrounded (game over)
            for (Player player : alivePlayers) {
                if (!player.canMove()) { // if a player dies
                    deadPlayers.add(player); // add him to deadPlayers
                    System.out.println(player.getPseudo() + " has lost.");
                }
            }
            alivePlayers.removeAll(deadPlayers); // remove deadPlayers from alivePlayers

            if (alivePlayers.size() == 1) { // if only one player is alive
                System.out.println(alivePlayers.get(0).getPseudo() + " has won.");
                gameOver = true;
            } else if (alivePlayers.size() == 0) {
                easterEgg();
                gameOver = true;
            }
        }
        if (!gameOver) {
            playRound(indexStartingPlayer); // repeat function
        }
    }
}
