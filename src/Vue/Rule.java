package Vue;

import java.util.Scanner;

import Controller.Gamebase;

public class Rule {

    // delete terminal
    public static void deleteTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static public void displayRules() {
        /**
         * Display the rules
         **/
        System.out.print("╔═══════════════════════════════════════════════════╗\n" +
                "║         Rules                                     ║\n" +
                "╠═══════════════════════════════════════════════════╣\n" +
                "║                                                   ║\n" +
                "║ During his turn, a player's pawn can \u001B[34mmove one\u001B[0m     ║\n" +
                "║ \u001B[34msquare\u001B[0m (vertically or horizontally), then he      ║\n" +
                "║ \u001B[34mdestroys one square\u001B[0m of the board.                 ║\n" +
                "║ \u001B[32mThe last player alive wins\u001B[0m.                       ║\n" +
                "║                                                   ║\n" +
                "║ A player \u001B[31mcan't destroy an occupied square\u001B[0m, nor    ║\n" +
                "║ can he destroy a destroyed square. He \u001B[31mcan't move\u001B[0m  ║\n" +
                "║ \u001B[31mhis pawn to an occupied or destroyed square\u001B[0m.      ║\n" +
                "║ \u001B[32mA player who can't move at the end of anyone's\u001B[0m    ║\n" +
                "║ \u001B[32mturn is declared unalived\u001B[0m.                        ║\n" +
                "║                                                   ║\n" +
                "║ There are \u001B[33m11 columns\u001B[0m and \u001B[33m10 rows\u001B[0m (11 by 10 grid). ║\n" +
                "║ There are \u001B[33m2 to 4 players\u001B[0m.                         ║\n" +
                "║ Players are \u001B[35mplaced in the middle of the board\u001B[0m at  ║\n" +
                "║ the beginning of the game.                        ║\n" +
                "║ \u001B[35mThe first player to play is randomly determined\u001B[0m.  ║\n" +
                "║ Each player moves his pawn vertically or          ║\n" +
                "║ horizontally in the grid, and then chooses a      ║\n" +
                "║ square to destroy.                                ║\n" +
                "║                                                   ║\n" +
                "║ A player \u001B[32mwins 5 points per won game\u001B[0m.              ║\n" +
                "║ A player \u001B[32mloses 2 points per lost game\u001B[0m.            ║\n" +
                "║                                                   ║\n" +
                "║                                                   ║\n" +
                "║                                                   ║\n" +
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
                        deleteTerminal();
                        Gamebase.initGame(); // Launch game
                        break;
                    case 2:
                        deleteTerminal();
                        Cli.openMenu(openedTimes + 1);
                        break;
                }
                sc.close();
            }
        } catch (Exception e) {
            deleteTerminal();
            System.out.println("Please enter a number between 1 and 2");
            openRules(openedTimes + 1);

        }
    }
}
