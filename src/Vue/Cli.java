package Vue;

import Controller.Gamebase;
import java.util.Scanner;

public class Cli {
    static public void displayMenu() {
        /**
         * Display the menu
         **/
        System.out.println("╔════════════════════════╗\n" +
                "║         Menu           ║\n" +
                "╠════════════════════════╣\n" +
                "║ 1. Play                ║\n" +
                "║ 2. Leave               ║\n" +
                "╚════════════════════════╝\n");
    }

    static public void openMenu(int openedTimes) {
        /**
         * Display menu and get player's input to play, open rules or leave game
         **/
        if (openedTimes > 20) {
            System.out.println("You're too dumb to play the game, bye");
            System.exit(0);
        }
        Scanner sc = new Scanner(System.in);
        displayMenu();
        Rule.displayRules();

        try {
            int userInput = sc.nextInt();
            if ((userInput >= 1) && (userInput <= 3)) {
                switch (userInput) {
                    case 1:
                        // delete a terminal
                        Rule.deleteTerminal();
                        Gamebase.initGame(); // Launch game
                        break;
                    case 2:
                        Rule.deleteTerminal();
                        System.out.println("Bye! Thanks for playing!");
                        System.exit(0); // Stop script
                }
                sc.close();
            } else {
                Rule.deleteTerminal();
                System.out.println("Please enter a number between 1 and 3");
                openMenu(openedTimes + 1);
            }
        } catch (Exception e) { // Player didn't enter an integer
            Rule.deleteTerminal();
            System.out.println("Please enter a number between 1 and 3");
            openMenu(openedTimes + 1);
        }
    }
}
