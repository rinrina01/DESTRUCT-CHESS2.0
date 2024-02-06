package Model;

public class Map {
    private char[][] matrix;

    public Map() {
        matrix = new char[][] { { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' } };
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void spawn(int playerCount) {
        /**
         * This function spawns all the players on the map
         * according to the number of players.
         * 
         * @param playerCount previously selected number of players
         **/
        switch (playerCount) {
            case 2: // 2 players
                matrix[4][5] = 'p';
                matrix[5][5] = 'q';
                break;
            case 3: // 3 players
                matrix[4][5] = 'p';
                matrix[5][4] = 'q';
                matrix[5][6] = 'r';
                break;
            case 4: // 4 players
                matrix[4][4] = 'p';
                matrix[4][6] = 'q';
                matrix[5][4] = 'r';
                matrix[5][6] = 's';
                break;
            default: // incorrect inputs are managed by the menu function
                break;
        }
    }
}
