package Controller;

import Model.Map;

public class Gamebase {
    static public void start() {
        Map mymap = new Map();
        var createMap = mymap.getMatrix();
        System.out.println("         ═══════════════════════════════════════════════════");
        for (int i = 0; i < createMap.length; i++) {
            System.out.print("Ligne: " + i + ' ');
            for (int j = 0; j < createMap[i].length; j++) {
                System.out.print("║    ");
            }
            System.out.println();
            System.out.println("         ═══════════════════════════════════════════════════");
        }
        mymap.spawn(2);
    }
}
