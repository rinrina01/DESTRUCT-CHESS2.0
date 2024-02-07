package Vue;

import Model.Player;

public class Scores {
    static Player[] allPlayers;  // List of all registered players

    public Scores(Player[] allPlayers) {
        this.allPlayers = allPlayers;
    }

    static public void displayScores() {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║         Scores                                    ║");
        System.out.println("╠═══════════════════════════════════════════════════╣");
        System.out.println("║                                                   ║");
        for (int i = 0 ; i < allPlayers.length ; i++) {
            System.out.println(allPlayers[i].getPseudo());
            System.out.println(allPlayers[i].getScore());
        }
        System.out.println("║                                                   ║");
        System.out.println("║                                                   ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
    }
}
