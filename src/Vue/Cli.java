package Vue;

import Controller.Saver;

import java.util.ArrayList;
import java.util.Map;
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

    static public void displayScores(ArrayList<String> pseudos, Map<String, Integer> scores, int openedTimes) {
        /*
         * This function displays the scores.
         *
         * @param allPlayers : list with all players
         * @param openedTimes : number of times the menu, the rules or this has been used
         */

        if (openedTimes > 20) { // easter egg
            System.out.println("You're too dumb to play the game, bye");
            System.exit(0);
        }

        int i = 1;
        System.out.println("╔═══════════════════════════════════════════════════╗\n" +
                "║         Scores                                    ║\n" +
                "╠═══════════════════════════════════════════════════╣");
        for (String pseudo : pseudos) { // loop that displays all scores 1 by 1
                int score = scores.get(pseudo);
                System.out.println("║ \u001B[30m" + String.valueOf(i) + ".\u001B[0m " + pseudo + " : "
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
        System.out.println("║ \uD81A\uDD67\uD81A\uDC7C\uD81A\uDC7C\uD81A\uDD23\uD81A\uDD67\uD81A\uDC7C\uD81A\uDC7C\uD81A\uDD23\uD81A\uDD67\uD81A\uDC7C\uD81A\uDC7C\uD81A\uDD23\uD81A\uDD67\uD81A\uDC7C\uD81A\uDC7C\uD81A\uDD23\uD81A\uDD67\uD81A\uDC7C\uD81A\uDC7C\uD81A\uDD23\uD81A\uDD67\uD81A\uDC7C" +
                " ʕ̢̣̣̣̣̩̩̩̩·͡˔·ོɁ̡̣̣̣̣̩̩̩̩✧ " +
                        "\uD81A\uDD67\uD81A\uDC7C\uD81A\uDC7C\uD81A\uDD23\uD81A\uDD67\uD81A\uDC7C\uD81A\uDC7C\uD81A\uDD23\uD81A\uDD67\uD81A\uDC7C\uD81A\uDC7C\uD81A\uDD23\uD81A\uDD67\uD81A\uDC7C\uD81A\uDC7C\uD81A\uDD23\uD81A\uDD67\uD81A\uDC7C\uD81A\uDC7C\uD81A\uDD23\uD81A\uDD67\uD81A\uDC7C ║\n" +
                "║ 1. Desc                                           ║\n" +
                "║ 2. Asc                                            ║\n" +
                "║ 3. Menu                                           ║\n" +
                "╚═══════════════════════════════════════════════════╝");

        // Iterate on Map containing all usernames and associated scores


        do { // choice of the sorting algorithm
            try {
                int userInput = sc.nextInt();
                switch (userInput) {
                    case 1:
                        deleteTerminal();
                        pseudos = quickSortDesc(pseudos, scores); // allPlayers array is sorted in descending order
                        displayScores(pseudos, scores, openedTimes + 1);              // call back to the function displaying the scores
                        break;
                    case 2:
                        deleteTerminal();
                        pseudos = quickSortAsc(pseudos, scores); // allPlayers array is sorted in ascending order
                        displayScores(pseudos, scores, openedTimes + 1);              // call back to the function displaying the scores
                        break;
                    case 3:
                        deleteTerminal();
                        openMenu(openedTimes + 1);
                    default:
                        System.out.println("Please enter DESC or ASC");
                }
            } catch (Exception e) { // player didn't enter DESC or ASC
                deleteTerminal();
                sc.next();
                System.out.println("Please enter DESC or ASC");
            }
        }
        while (true) ;
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
                        Map<String, Integer> scores = Saver.readScores();
                        displayScores(new ArrayList<>(scores.keySet()), scores, openedTimes+1);
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

    public static ArrayList<String> quickSortAsc(ArrayList<String> tab, Map<String, Integer> scores) {
        if (tab.size() <= 1) {
            return tab;
        } else {
            int pivot = scores.get(tab.get(0));
            ArrayList<String> lesser = new ArrayList<>();
            ArrayList<String> equals = new ArrayList<>();
            ArrayList<String> greater = new ArrayList<>();

            for (String pseudo : tab) {
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

    public static ArrayList<String> quickSortDesc(ArrayList<String> tab, Map<String, Integer> scores) {
        if (tab.size() <= 1) {
            return tab;
        } else {
            int pivot = scores.get(tab.get(0));
            ArrayList<String> lesser = new ArrayList<>();
            ArrayList<String> equals = new ArrayList<>();
            ArrayList<String> greater = new ArrayList<>();

            for (String pseudo : tab) {
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
}
