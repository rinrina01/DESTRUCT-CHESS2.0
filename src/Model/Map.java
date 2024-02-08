package Model;

public class Map {
    private final char[][] matrix;

    public Map() {
        // initializes the grid with 'a' and 'b' for bombs blocks
        this.matrix = generateMatrix();

        /*
         * a => available
         * d => destroyed
         * other => players
         */
    }

    public char[][] generateMatrix() {
        char[][] matrix = new char[][] { // initializes the grid with only 'a's
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
        
        int columnBomb = (int)(Math.random() * 11);
        int rowBomb = (int)(Math.random() * 10);
        matrix[rowBomb][columnBomb] = 'b';
    

        return matrix;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setSquare(int x, int y, char value) {
        matrix[y][x] = value;
    }

    public char getSquare(int x, int y) {
        return matrix[y][x];
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

                    String displayBlock = ""; // string containing the color and content of the box
                    String displayColorReset = "\u001B[0m";
                    switch (matrix[i][j]) { // manages display colors according to the box
                        case 'p': // Player 1
                            displayBlock = "\u001B[34mp";
                            break;
                        case 'q': // Player 2
                            displayBlock = "\u001B[31mq";
                            break;
                        case 'r': // Player 3
                            displayBlock = "\u001B[33mr";
                            break;
                        case 's': // Player 4
                            displayBlock = "\u001B[32ms";
                            break;
                        case 'b': // Bomb block
                            displayBlock = "\033[0;35mB";
                            break;
                        case 'd': // Destructed block
                            displayBlock = "\033[0;30mX";
                            break;
                    }
                    displayed += "║ " + displayBlock + displayColorReset + ' ';


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
