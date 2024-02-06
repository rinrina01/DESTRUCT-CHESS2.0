package Vue;

import Controller.Gamebase;

import java.util.Scanner;

public class Cli {

    // menu display
    static public void menu() {
        System.out.println("╔════════════════════════╗");
        System.out.println("║         Menu           ║");
        System.out.println("╠════════════════════════╣");
        System.out.println("║ 1. Play                ║");
        System.out.println("║ 2. Rule                ║");
        System.out.println("║ 3. Quitter             ║");
        System.out.println("╚════════════════════════╝");
    }

    static public void chooseMode() {
        Scanner sc = new Scanner(System.in);
        Cli twoMenu = new Cli();
        twoMenu.menu();
        try {
            int userChooseMode = sc.nextInt();
            switch (userChooseMode) {
                case 1:
                    Gamebase.start();
                    break;
                case 2:
                    Rule.backOrStart();
                    break;
                case 3:
                    System.out.println("Au revoir et merci d'avoir joué");
                    sc.close();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("hihihahaha" + ' ' + e);
            // return a menu
            chooseMode();
        }

    }
}
