package Controller;

import Model.Map;
import Model.Player;
import Vue.Cli;
import CONFIDENTIAL.CATS.BUTTER.CATWITHBUTTEREDBREADONHISBACK.PIXELLE.EasterEgg;

import java.util.ArrayList;
import java.util.Random;

public class Gamebase {
    static public final ArrayList<Player> allPlayers = new ArrayList<>();
    static final ArrayList<Player> alivePlayers = new ArrayList<>();
    static final ArrayList<Player> deadPlayers = new ArrayList<>();
    static Map map;

    static void addAPlayer(int posX, int posY, char symbol, char[][] matrix) {
        /**
         * This function creates and adds a player with given parameters to alivePlayers
         * and to the map.
         * 
         * @param posX   : row where is located the player
         * @param posY   : column where is located the player
         * @param symbol : the character to recognize the player
         * @param matrix : the map
         */
        String userInput;
        boolean alreadyChosen;
        do {
            alreadyChosen = false;
            System.out.println("Enter the name of " + symbol);
            userInput = Cli.sc.nextLine();

            for (Player player : alivePlayers) {
                if (player.getPseudo().equals(userInput)) {
                    alreadyChosen = true;
                    break;
                }
            }
        } while ((userInput.length() < 2) || (userInput.length() >= 10) || alreadyChosen);

        alivePlayers.add(new Player(userInput, posX, posY, symbol, map));
        matrix[posY][posX] = symbol;
    }

    static void add2Players() {
        /**
         * This function uses the AddAPlayer function twice.
         */

        char[][] matrix = map.getMatrix();

        // add player1
        addAPlayer(5, 4, 'p', matrix);

        // add player2
        addAPlayer(5, 5, 'q', matrix);
    }

    static void add3Players() {
        /**
         * This function uses the AddAPlayer function three times.
         */

        char[][] matrix = map.getMatrix();

        // add player1
        addAPlayer(5, 4, 'p', matrix);

        // add player2
        addAPlayer(4, 5, 'q', matrix);

        // add player3
        addAPlayer(6, 5, 'r', matrix);
    }

    static void add4Players() {
        /**
         * This function uses the AddAPlayer function four times.
         */

        char[][] matrix = map.getMatrix();

        // add player1
        addAPlayer(4, 4, 'p', matrix);

        // add player2
        addAPlayer(6, 4, 'q', matrix);

        // add player3
        addAPlayer(4, 5, 'r', matrix);

        // add player4
        addAPlayer(6, 5, 's', matrix);
    }

    static void addPlayers(int playerCount) {
        /**
         * This function uses the addPlayers functions depending on playerCount and then
         * add all alivePlayers to AllPlayers.
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

        allPlayers.addAll(alivePlayers);
    }

    static void destroySquare(Map map) {
        /**
         * This function asks player to enter a square to destroy it.
         * 
         * @param map : the map
         */

        int row;
        String columnInput;
        int column;
        char[][] matrix = map.getMatrix();

        do { // ask for column and row until player enters a free square
            do { // ask column until input is valid
                column = 0;
                System.out.println("Enter the column [A - K]");
                columnInput = Cli.sc.nextLine();

                if (columnInput.length() == 1) {
                    short asciiValue = (short) (columnInput.charAt(0)); // convert char to ascii value
                    if (asciiValue >= 97) { // if character is a uppercase letter
                        column = asciiValue - 97; // set as the letter's number in the alphabet
                        if ((column >= 0) && (column <= 10)) { // check if column number is valid
                            break;
                        }
                    } else if (asciiValue >= 65) { // if character is a lowercase letter
                        column = asciiValue - 65; // set as the letter's number in the alphabet
                        if ((column >= 0) && (column <= 10)) {
                            break;
                        }
                    }
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
                    Cli.sc.next(); // clean unused input
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

    public static void initGame() {
        /**
         * This function intitalizes all game related parameters such as the number of
         * players and their position in the map.
         * It then launches the first round of the game.
         */

        map = new Map();
        int userInput;

        do {
            try {
                System.out.println("Enter the number of players (between 2 and 4)");
                userInput = Cli.sc.nextInt();

                if ((userInput >= 2) && (userInput <= 4)) { // check if user input is between 2 and 4
                    break;
                }
            } catch (Exception e) {
                Cli.sc.next(); // clean unused input
                System.out.println("That's not an integer...");
            }
        } while (true);

        addPlayers(userInput); // add players to alivePlayers and in the map
        playRound(new Random().nextInt(alivePlayers.size())); // index of the randomly chosen starting player & start
                                                              // round
    }

    static void playRound(int indexStartingPlayer) {
        /**
         * Starts the play cycle which repeats itself until the last second player dies.
         * 
         * @param indexStartingPlayer : the number of the player playing first
         */

        boolean gameOver = false;
        // use getMovement and destructBlock function with all players
        for (int i = 0; i < alivePlayers.size(); i++) {
            System.out.println(map);

            int index = (indexStartingPlayer + i) % alivePlayers.size();
            System.out.println("Turn of " + alivePlayers.get(index).getPseudo());
            alivePlayers.get(index).getMovement(); // ask player to move

            System.out.println(map);

            destroySquare(map); // ask player to destroy a square

            // verify if any player is surrounded (game over)
            for (Player player : alivePlayers) {
                if (!player.canMove()) { // if a player dies
                    deadPlayers.add(player); // add him to deadPlayers
                    System.out.println(player.getPseudo() + " has lost.");
                    // player loses points
                    player.setScore(-2);
                }
            }
            alivePlayers.removeAll(deadPlayers); // remove deadPlayers from alivePlayers

            if (alivePlayers.size() == 1) { // if only one player is alive
                Player victoryMan = alivePlayers.get(0);
                System.out.println(victoryMan.getPseudo() + " has won.");
                gameOver = true;
                // add points to winning player
                victoryMan.setScore(5);

            } else if (alivePlayers.isEmpty()) {
                EasterEgg.easterEgg();
                gameOver = true;

            }
        }
        if (!gameOver) {
            playRound(indexStartingPlayer); // repeat function
        }
    }
}
