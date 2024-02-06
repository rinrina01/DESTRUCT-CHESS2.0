package Model;

public class Map {
    private char[][] matrix;

    public Map() {
        this.matrix = new char[][]{
            {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
            {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
            {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
            {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
            {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
            {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
            {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
            {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
            {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
            {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'}};
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setSquare(int x, int y, char value) {
        matrix[y][x] = value;
    }

    public void spawn(int playerCount) {
        /** This function spawns all the players on the map, they'll have name code p, q, r and s
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

    @Override
    public String toString() {
        String displayed = "";
        char[][] matrix = getMatrix();
        displayed += "         ═════════════════════════════════════════\n";
        for (int i = 0; i < matrix.length; i++) {
            displayed += "Row:   " + i + ' ';
            for (int j = 0; j < matrix[i].length; j++) {
                displayed += "║   ";
            }
            displayed += "\n         ═════════════════════════════════════════\n";
        }
        displayed += "Column:    A   B   C   D   E   F   G   H   I   J\n";
        return displayed;
    }
}
