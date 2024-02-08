package Model;

public class Map {
    private char[][] matrix;

    public Map() {
        this.matrix = new char[][] { // initializes the grid with only 'a's
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' },
                { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' } };
        /*
         * a => available
         * d => destroyed
         * other => players
         */
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setSquare(int x, int y, char value) {
        matrix[y][x] = value;
    }

    @Override
    public String toString() {
        /**
         * This function displays the grid of the game with the matrix variable in this
         * file
         * 
         * @return String : text containing all the information in the grid
         */

        String displayed = "";
        char[][] matrix = getMatrix();
        displayed += "         ═════════════════════════════════════════════\n";
        for (int i = 0; i < matrix.length; i++) { // loops every rows
            displayed += "Row:   " + i + ' ';
            for (int j = 0; j < matrix[i].length; j++) { // loops every columns
                if (matrix[i][j] != 'a') {
                    displayed += "║ " + matrix[i][j] + ' ';
                } else {
                    displayed += "║   ";
                }
            }
            displayed += "║\n         ═════════════════════════════════════════════\n";
        }
        displayed += "Column:    A   B   C   D   E   F   G   H   I   J   K\n";
        return displayed;
    }
}
