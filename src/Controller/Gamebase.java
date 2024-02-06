package Controller;

import Model.Map;
import Model.Player;

import java.util.Scanner;

public class Gamebase {
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        Map mymap = new Map();

        Player[] alivePlayers = new Player[]{
                new Player("player1", 6, 5, 0, 'p'),
                new Player("player2", 5, 6, 0, 'q')
        };

        mymap.spawn(2);

        String direction = scanner.nextLine();
        if (direction == "z") {
            move(alivePlayers[0], mymap, 'z');
        } else if (direction == "q") {
            move(alivePlayers[0], mymap, 'q');
        } else if (direction == "s") {
            move(alivePlayers[0], mymap, 's');
        } else if (direction == "d") {
            move(alivePlayers[0], mymap, 'd');
        } else {
            System.out.println("Veuillez mettre Z, Q, S ou D");
        }


    }
    static void move(Player player, Map map, char direction) {
        int playerPosX = player.getPosX();
        int playerPosY = player.getPosY();
        switch (direction) {
            case 'z':
                if (playerPosY > 0) {
                    map.setSquare(playerPosX, playerPosY, 'a');
                    map.setSquare(playerPosX, playerPosY - 1, player.getSymbol());
                    player.setPosY(playerPosY - 1);
                    System.out.println("z");
                }
                break;
            case 'q':
                if (playerPosX > 0) {
                    map.setSquare(playerPosX, playerPosY, 'a');
                    map.setSquare(playerPosX - 1, playerPosY, player.getSymbol());
                    player.setPosX(playerPosX - 1);
                    System.out.println("q");
                }
                break;
            case 's':
                if (playerPosY < 9) {
                    map.setSquare(playerPosX, playerPosY, 'a');
                    map.setSquare(playerPosX, playerPosY + 1, player.getSymbol());
                    player.setPosY(playerPosY + 1);
                    System.out.println("s");
                }
                break;
            case 'd':
                if (playerPosX < 10) {
                    map.setSquare(playerPosX, playerPosY, 'a');
                    map.setSquare(playerPosX + 1, playerPosY, player.getSymbol());
                    player.setPosX(playerPosX + 1);
                    System.out.println("d");
                }
                break;
        }
    }
}
