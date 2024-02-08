package Controller;

import Model.Player;

import java.io.*;
import java.util.*;

public class Saver {

    protected static boolean isFileNotExist(String filename) {
        /** This function verifies the save.txt
         * file exists.
         * @return boolean true if file not found **/

        return !(new File("src/Save/"+filename)).exists();
    }

    public static File CreateFile(String filename) throws IOException {
        /** This function verifies if the save.txt
         * file can be opened succesfully.
         * @return boolean true if file opened successfully **/

        if(isFileNotExist(filename))
        {
            File newFile = new File("src/Save/"+filename);
            newFile.createNewFile();
            return newFile;
        } else {
            return new File("src/Save/"+filename);
        }
    }

    public static void writeScores(ArrayList<Player> allPlayers, String filename) throws IOException {
        /**
         * This function creates a file named scores.txt and writes scores in it
         *
         * @param allPlayers : ArrayList with all players who played
         *
         * **/

        File newFile = CreateFile(filename);

        Scanner reader = new Scanner(newFile);
        Map<String, Integer> playersScore = new HashMap<>();
        String[] line;

        Map<String, Integer> scoresMap = readScores("scores.txt");

        for (Player player : allPlayers) {
            String pseudo = player.getPseudo();
            if (scoresMap.containsKey(pseudo)) {
                scoresMap.put(pseudo, scoresMap.get(pseudo) + player.getScore());
            } else {
                scoresMap.put(pseudo, player.getScore());
            }
        }



        /*
        while (reader.hasNextLine()) {
            line = reader.nextLine().split(":");
            playersScore.put(line[0], Integer.valueOf(line[1]));
        }

        if (new File("src/Save", filename).createNewFile()) {
            try (FileWriter file = new FileWriter("src/Save/"+filename)) {
                String pseudo;
                int score;
                for (Player player : allPlayers) {
                    pseudo = player.getPseudo();
                    try {
                        score = playersScore.get(pseudo) + player.getScore();
                        file.write(pseudo + ':' + score + '\n');
                    } catch (Exception e) {
                        file.write(pseudo + ':' + player.getScore() + '\n');
                    }
                }
            } catch (IOException e) {
                System.out.println("FAIIIILURE");
            }
        }*/
    }

    public static Map<String, Integer> readScores(String filename) throws IOException {
        Scanner reader = new Scanner(new File("src/Save/" + filename));
        Map<String, Integer> scores = new HashMap<>();
        String[] line;

        while(reader.hasNextLine()) {
            line = reader.nextLine().split(":");
            scores.put(line[0], Integer.valueOf(line[1]));
        }

        return scores;
    }
}
