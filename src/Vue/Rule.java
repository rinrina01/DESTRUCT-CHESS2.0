package Vue;

import java.util.Scanner;

public class Rule {

    // display rule of game
    static public void displayRule() {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║         Rules                                     ║");
        System.out.println("╠═══════════════════════════════════════════════════╣");
        System.out.println("║                                                ║");

        System.out.println(
                "║ Pendant son tour un joueur peut déplacer son pion \n║ d’une case (verticalement ou horizontalement),      ║");
        System.out
                .println(
                        "║ puis il détruit une case du plateau. Le dernier joueur \n║ pouvant encore se déplacer gagne.║");
        System.out.println("║                                       ║");
        System.out.println(
                "║ Un joueur ne peut pas détruire une case occupée, \n║ ne peut pas occuper \n║ une case détruite ou une case déjà occupée. \n║ Un joueur bloqué pendant un tour est déclaré perdant. ");
        System.out.println("║                                        ║");
        System.out.println(
                "║ Il y a 11 colonnes et 10 lignes (matrice de 11 x 10).\n║ Dans ce tableau 2 joueurs sont positionné au centre\n║"
                        + //
                        "(Position C5;L6 et C6;L6) en début de partie.\n║ Chaque joueur déplace horizontalement ou\n║" + //
                        " verticalement son pion dans la matrice,\n║ puis sélectionne une case à détruire.\n║");
        System.out.println("║                                        ║");
        System.out.println("║ Un joueur gagne 5 point par partie gagnée.  ║");
        System.out.println("║ Un joueur perd 2 point par partie perdue.  ║");
        System.out.println("║                                        ║");
        System.out.println("║   1: Start                                    ║");
        System.out.println("║   2: Back to menu                                     ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");

    }

    // input for back to menu or play
    static public void backOrStart() {
        Scanner scRule = new Scanner(System.in);
        Rule threeMenu = new Rule();
        threeMenu.displayRule();
        try {
            int userChooseMode = scRule.nextInt();
            switch (userChooseMode) {
                case 1:
                    // map
                    break;
                case 2:
                    Cli.chooseMode();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("hihihahaha" + ' ' + e);
            // return a rule
            threeMenu.displayRule();
        }

    }
}
