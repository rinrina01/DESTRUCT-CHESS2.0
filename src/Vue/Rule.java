package Vue;

import java.util.Scanner;

import Controller.Gamebase;

public class Rule {
    static public void displayRules() {
        /**
         * Display the rules
         **/
        System.out.println("╔═══════════════════════════════════════════════════╗\n" +
                "║         Rules                                     ║\n" +
                "╠═══════════════════════════════════════════════════╣\n" +
                "║                                                   ║\n" +
                "║ During his turn, a player can move his pawn one   ║\n" +
                "║ square (vertically or horizontally), then he      ║\n" +
                "║ destroys one square of the board.                 ║\n" +
                "║ The last player alive wins.                       ║\n" +
                "║                                                   ║\n" +
                "║ A player can't destroy an occupied square, nor    ║\n" +
                "║ can he destroy a destroyed square. He can't move  ║\n" +
                "║ his pawn to an occupied or destroyed square.      ║\n" +
                "║ A player who can't move at the end of anyone's    ║\n" +
                "║ turn is declared dead and lost the game.          ║\n" +
                "║                                                   ║\n" +
                "║ There are 11 columns and 10 rows (11 by 10 grid). ║\n" +
                "║ There are 2 to 4 players.                         ║\n" +
                "║ Players are placed in the middle of the board at  ║\n" +
                "║ the beginning of the game.                        ║\n" +
                "║ The first player to play is randomly determined.  ║\n" +
                "║ Each player moves his pawn vertically or          ║\n" +
                "║ horizotally in the grid, and then chooses a       ║\n" +
                "║ square to destroy.                                ║\n" +
                "║                                                   ║\n" +
                "║ A player wins 5 points per won game.              ║\n" +
                "║ A player loses 2 points per lost game.            ║\n" +
                "║                                                   ║\n" +
                "║   1: Play                                         ║\n" +
                "║   2: Back to menu                                 ║\n" +
                "╚═══════════════════════════════════════════════════╝\n");
    }

    static public void openRules(int openedTimes) {
        /**
         * Display rules and get player's input to play or go back to menu
         **/
        if (openedTimes > 20) {
            System.out.println("You're too dumb to play the game, bye");
            System.exit(0);
        }

        Scanner sc = new Scanner(System.in);
        displayRules();

        try {
            int userInput = sc.nextInt();
            if ((userInput == 1) || (userInput == 2)) {
                switch (userInput) {
                    case 1:
                        Gamebase.initGame(); // Launch game
                        break;
                    case 2:
                        Cli.openMenu(openedTimes + 1);
                        break;
                }
                sc.close();
            } else {
                System.out.println("Please enter a number between 1 and 2");
                openRules(openedTimes + 1);
            }
        } catch (Exception e) {
            System.out.println("Please enter a number between 1 and 2");
            Rule.displayRules();
        }
    }
}
