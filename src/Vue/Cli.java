package Vue;

import Controller.Saver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Cli {
    static public final Scanner sc = new Scanner(System.in);

    static void deleteTerminal() {
        /**
         * This function clears the terminal.
         */
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static ArrayList<String> quickSortAsc(ArrayList<String> pseudos, Map<String, Integer> scores) {
        if (pseudos.size() <= 1) {
            return pseudos;
        } else {
            int pivot = scores.get(pseudos.get(0));
            ArrayList<String> lesser = new ArrayList<>();
            ArrayList<String> equals = new ArrayList<>();
            ArrayList<String> greater = new ArrayList<>();

            for (String pseudo : pseudos) {
                int score = scores.get(pseudo);
                if (score < pivot) {
                    lesser.add(pseudo);
                } else if (score > pivot) {
                    greater.add(pseudo);
                } else {
                    equals.add(pseudo);
                }
            }
            lesser = quickSortAsc(lesser, scores);
            lesser.addAll(equals);
            lesser.addAll(quickSortAsc(greater, scores));
            return lesser;
        }
    }

    static ArrayList<String> quickSortDesc(ArrayList<String> pseudos, Map<String, Integer> scores) {
        if (pseudos.size() <= 1) {
            return pseudos;
        } else {
            int pivot = scores.get(pseudos.get(0));
            ArrayList<String> lesser = new ArrayList<>();
            ArrayList<String> equals = new ArrayList<>();
            ArrayList<String> greater = new ArrayList<>();

            for (String pseudo : pseudos) {
                int score = scores.get(pseudo);
                if (score < pivot) {
                    lesser.add(pseudo);
                } else if (score > pivot) {
                    greater.add(pseudo);
                } else {
                    equals.add(pseudo);
                }
            }
            greater = quickSortDesc(greater, scores);
            greater.addAll(equals);
            greater.addAll(quickSortDesc(lesser, scores));
            return greater;
        }
    }

    static void displayScores(ArrayList<String> pseudos, Map<String, Integer> scores) {
        /**
         * This function displays the scores.
         *
         * @param pseudos : sorted list with all players' pseudos
         * @param scores  : map with all players' pseudos as keys and their scores
         *                as values
         */

        int i = 1;
        System.out.println("╔═══════════════════════════════════════════════════╗\n" +
                "║         Scores                                    ║\n" +
                "╠═══════════════════════════════════════════════════╣");
        for (String pseudo : pseudos) { // loop that displays all scores 1 by 1
            int score = scores.get(pseudo);
            System.out.println("║ \u001B[90m" + String.valueOf(i) + ".\u001B[0m " + pseudo + " : "
                    + (score >= 0 ? "\u001B[32m" : "\u001B[31m") + score + "\u001B[0m"
                    + String.format(
                            "%" + (43 - pseudo.length() - (int) (Math.log10(Math.abs(score)))
                                    - (int) (Math.log10(i)) - (score >= 0 ? 0 : 1)) + "s",
                            "")
                    + '║');
            i++;
            if (i > 10) {
                break;
            }
        }
        System.out.println("╠═══════════════════════════════════════════════════╣\n" +

                "║ \u001B[94m1.\u001B[0m Desc                                           ║\n" +
                "║ \u001B[94m2.\u001B[0m Asc                                            ║\n" +
                "║ \u001B[94m3.\u001B[0m Menu                                           ║\n" +
                "╚═══════════════════════════════════════════════════╝");
    }

    static public void openScores(int openedTimes) {
        /**
         * This function displays the scores and asks the player the sorting order he
         * wants and if he wants to go back to menu.
         * 
         * @param openedTimes : number of times the menu, the rules or this menu have
         *                    been opened
         */

        if (openedTimes > 20) { // easter egg
            System.out.println("You're too dumb to play the game, bye");
            System.exit(0);
        }

        try {
            Map<String, Integer> scores = Saver.readScores(); // get scores from save file
            ArrayList<String> pseudos = new ArrayList<>(scores.keySet()); // get pseudos from scores
            pseudos = quickSortDesc(pseudos, scores); // sort pseudos

            displayScores(pseudos, scores);

            try { // choice of the sorting algorithm
                int userInput = sc.nextInt();
                if ((userInput >= 1) && (userInput <= 3)) {
                    switch (userInput) {
                        case 1:
                            deleteTerminal();
                            openScores(openedTimes + 1, false); // call back to the function displaying the scores
                            break;
                        case 2:
                            deleteTerminal();
                            openScores(openedTimes + 1, true); // call back to the function displaying the scores
                            break;
                        case 3:
                            deleteTerminal();
                            openMenu(openedTimes + 1);
                    }
                } else {
                    deleteTerminal();
                    System.out.println("Please enter a number between 1 and 3");
                    openScores(openedTimes + 1);
                }
            } catch (Exception e) { // player didn't enter an integer
                deleteTerminal();
                sc.next();
                System.out.println("Please enter 1, 2 or 3");
                openScores(openedTimes + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public void openScores(int openedTimes, boolean asc) {
        /**
         * This function displays the scores and asks the player the sorting order he
         * wants and if he wants to go back to menu.
         * 
         * @param openedTimes : number of times the menu, the rules or this menu have
         *                    been opened
         * @param asc         : true if sorting scores ascendingly
         */

        if (openedTimes > 20) { // easter egg
            System.out.println("You're too dumb to play the game, bye");
            System.exit(0);
        }

        try {
            Map<String, Integer> scores = Saver.readScores(); // get scores from save file
            ArrayList<String> pseudos = new ArrayList<>(scores.keySet()); // get pseudos from scores
            pseudos = asc ? quickSortAsc(pseudos, scores) : quickSortDesc(pseudos, scores); // sort pseudos

            displayScores(pseudos, scores);

            try { // choice of the sorting algorithm
                int userInput = sc.nextInt();
                if ((userInput >= 1) && (userInput <= 3)) {
                    switch (userInput) {
                        case 1:
                            deleteTerminal();
                            openScores(openedTimes + 1, false); // call back to the function displaying the scores
                            break;
                        case 2:
                            deleteTerminal();
                            openScores(openedTimes + 1, true); // call back to the function displaying the scores
                            break;
                        case 3:
                            deleteTerminal();
                            openMenu(openedTimes + 1);
                    }
                } else {
                    deleteTerminal();
                    System.out.println("Please enter a number between 1 and 3");
                    openScores(openedTimes + 1);
                }
            } catch (Exception e) { // player didn't enter an integer
                deleteTerminal();
                sc.next();
                System.out.println("Please enter 1, 2 or 3");
                openScores(openedTimes + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void displayMenu() {
        /**
         * This function displays the menu.
         **/

        System.out.println("╔════════════════════════╗\n" +
                "║    Menu                ║\n" +
                "╠════════════════════════╣\n" +
                "║ \u001B[94m1.\u001B[0m Play                ║\n" +
                "║ \u001B[94m2.\u001B[0m Scores              ║\n" +
                "║ \u001B[94m3.\u001B[0m Leave               ║\n" +
                "╚════════════════════════╝\n");
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
        Rules.displayRules();
        displayMenu();

        try {
            int userInput = sc.nextInt();
            if ((userInput >= 1) && (userInput <= 3)) {
                switch (userInput) {
                    case 1:
                        deleteTerminal();
                        break;
                    case 2:
                        deleteTerminal();
                        openScores(openedTimes + 1);
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
