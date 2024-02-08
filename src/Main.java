import Controller.Gamebase;
import Model.Map;
import Model.Player;
import Vue.Cli;
import Controller.Saver;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<Player> allPlayers = new ArrayList<>();
        Map map = new Map();
        allPlayers.add(new Player("Jean", 5, 4, 'p', map));


        String filename = "scores.txt";
        Saver.CreateFile(filename);
        Saver.writeScores(allPlayers, filename);
    /*
        while (true) {
            Cli.openMenu(0); // opens the menu
            Gamebase.initGame(); // launch the game
        }*/
    }
}