import Controller.Gamebase;
import Vue.Cli;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Cli.openMenu(0); // open menu
            Gamebase.initGame(); // launch game
        }
    }
}