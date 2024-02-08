package Vue;

import Model.Player;

import java.util.Scanner;

public class Cli {
    static public final Scanner sc = new Scanner(System.in);

    static public void displayMenu() {
        /**
         * This function displays the menu.
         **/

        System.out.println("╔════════════════════════╗\n" +
                "║         Menu           ║\n" +
                "╠════════════════════════╣\n" +
                "║ 1. Play                ║\n" +
                "║ 2. Scores              ║\n" +
                "║ 3. Leave               ║\n" +
                "╚════════════════════════╝\n");
    }

    static public void displayScores(Player[] allPlayers) {
        /*
         * This function displays the scores.
         * 
         * @param allPlayers : list with all players
         */

        int i = 1;
        System.out.println("╔═══════════════════════════════════════════════════╗\n" +
                "║         Scores                                    ║\n" +
                "╠═══════════════════════════════════════════════════╣");
        for (Player player : allPlayers) { // loop that displays all scores 1 by 1
            String pseudo = player.getPseudo();
            int score = player.getScore();
            System.out.println("║ \u001B[30m" + String.valueOf(i + 1) + ".\u001B[0m " + pseudo + " : "
                    + (score >= 0 ? "\u001B[32m" : "\u001B[31m") + score + "\u001B[0m"
                    + String.format(
                            "%" + (43 - pseudo.length() - (int) (Math.log10(Math.abs(score)))
                                    - (int) (Math.log10(i + 1) - (score >= 0 ? 0 : 1))) + "s",
                            "")
                    + '║');
            i++;
        }
        System.out.println("╚═══════════════════════════════════════════════════╝");
    }

    static public void deleteTerminal() {
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
        displayMenu();
        Rules.displayRules();

        try {
            int userInput = sc.nextInt();
            if ((userInput >= 1) && (userInput <= 3)) {
                switch (userInput) {
                    case 1:
                        deleteTerminal();
                        break;
                    case 2:
                        deleteTerminal();
                        // displayScores();
                        openMenu(openedTimes + 1);
                        break;
                    case 3:
                        deleteTerminal();
                        System.out.println("Bye! Thanks for playing!");
                        System.exit(0); // stop script
                }
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
