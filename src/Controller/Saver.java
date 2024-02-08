package Controller;

import Model.Player;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Saver {

    protected static boolean fileExists(String filename) {
        /**
         * This function returns true if the file src/Save/`filename` exists.
         * 
         * @param filename : name of the file to look for in src/Save folder
         * @return boolean : true if file found
         **/

        return (new File("src/Save/" + filename)).exists();
    }

    public static void writeScores(ArrayList<Player> allPlayers) throws IOException {
        /**
         * This function creates a file named scores.txt and writes scores in it
         *
         * @param allPlayers : ArrayList with all players who played
         **/

        Files.createDirectories(Paths.get("src/Save")); // creates the folder
        String filename = "scores.txt";

        if (fileExists(filename)) {
            Map<String, Integer> scoresMap = readScores(); // get file content

            File saveFile = new File("src/Save/" + filename);
            saveFile.createNewFile(); // recreate save file to empty it

            FileWriter writer = new FileWriter(saveFile);

            for (Player player : allPlayers) {
                String pseudo = player.getPseudo();
                /*
                 * if player had a score stored in the save file, add his current score and his
                 * stored score and then remove him from scoresMap
                 */
                if (scoresMap.containsKey(pseudo)) {
                    writer.write(pseudo + ':' + String.valueOf(scoresMap.get(pseudo) + player.getScore()));
                    scoresMap.remove(pseudo);
                } else {
                    writer.write(pseudo + ':' + String.valueOf(player.getScore())); // save the players' score
                }
            }
            for (Map.Entry<String, Integer> entry : scoresMap.entrySet()) {
                writer.write(entry.getKey() + ':' + entry.getValue()); // rewrite stored scores
            }
            writer.close();
        } else {
            File saveFile = new File("src/Save/" + filename);
            saveFile.createNewFile(); // create save file

            FileWriter writer = new FileWriter(saveFile);

            for (Player player : allPlayers) {
                writer.write(player.getPseudo() + ':' + String.valueOf(player.getScore())); // save the players' score
            }
            writer.close();
        }
    }

    public static Map<String, Integer> readScores() throws IOException {
        /**
         * This function reads the save file and returns players' score
         * 
         * @return Map<String, Integer> : the String is the pseudo and Integer the score
         */
        Scanner reader = new Scanner(new File("src/Save/scores.txt"));
        Map<String, Integer> scores = new HashMap<>();
        String[] line;

        while (reader.hasNextLine()) {
            line = reader.nextLine().split(":");
            scores.put(line[0], Integer.valueOf(line[1]));
        }
        reader.close();

        return scores;
    }
}
