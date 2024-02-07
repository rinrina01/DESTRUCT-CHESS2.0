package Vue;

import Controller.Gamebase;
import Model.Player;

import java.util.Scanner;

public class Cli {
    static public void displayMenu() {
        /**
         * This function displays the menu.
         **/

        System.out.println("╔════════════════════════╗\n" +
                "║         Menu           ║\n" +
                "╠════════════════════════╣\n" +
                "║ 1. Play                ║\n" +
                "║ 2. Leave               ║\n" +
                "╚════════════════════════╝\n");
    }

    public void displayScores(Player[] allPlayers) {
        /*
         * This function displays the scores.
         * 
         * @param allPlayers : list with all players
         */

        System.out.println("╔═══════════════════════════════════════════════════╗" +
                "║         Scores                                    ║" +
                "╠═══════════════════════════════════════════════════╣" +
                "║                                                   ║");
        for (Player player : allPlayers) { // loop that displays all scores 1 by 1
            String pseudo = player.getPseudo();
            int score = player.getScore();
            System.out.println("║         " + pseudo + String.format("%" + (44 - pseudo.length()) + "s", "") + '║');
            System.out.println(
                    "║         " + score + String.format("%" + (int) (44 - Math.log10(score)) + "s", "") + '║');
        }
        System.out.println("║                                                   ║" +
                "║                                                   ║" +
                "╚═══════════════════════════════════════════════════╝");
    }

    public static void deleteTerminal() {
        /*
         * This function clears terminal.
         */

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static public void openMenu(int openedTimes) {
        /**
         * This function displays the menu and gets player's input to play, open rules
         * or leave game.
         **/

        if (openedTimes > 20) { // easter egg
            System.out.println("You're too dumb to play the game, bye");
            System.exit(0);
        }
        Scanner sc = new Scanner(System.in);
        displayMenu();
        Rules.displayRules();

        try {
            int userInput = sc.nextInt();
            if ((userInput >= 1) && (userInput <= 3)) {
                switch (userInput) {
                    case 1:
                        deleteTerminal();
                        Gamebase.initGame(); // launch game
                        break;
                    case 2:
                        deleteTerminal();
                        System.out.println("Bye! Thanks for playing!");
                        System.exit(0); // stop script
                }
                sc.close();
            } else {
                deleteTerminal();
                System.out.println("Please enter a number between 1 and 3");
                openMenu(openedTimes + 1);
            }
        } catch (Exception e) { // player didn't enter an integer
            deleteTerminal();
            System.out.println("Please enter a number between 1 and 3");
            openMenu(openedTimes + 1);
        }
    }
}
