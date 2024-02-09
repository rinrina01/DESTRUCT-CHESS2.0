package Model;

public class Map {
    private final char[][] matrix;

    public Map() {
        // initializes the grid with 'a' and 'b' for bombs blocks
        this.matrix = generateMatrix();

        /*
         * a => available
         * d => destroyed
         * b => bomb
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

        StringBuilder displayed = new StringBuilder();
        char[][] matrix = getMatrix();
        displayed.append("         ═════════════════════════════════════════════\n");
        for (int i = 0; i < matrix.length; i++) { // loops every rows
            displayed.append("Row:   ").append(i).append(' ');
            for (int j = 0; j < matrix[i].length; j++) { // loops every column
                char symbol = matrix[i][j];
                if (symbol != 'a') {
                    // string containing the color and content of the box
                    String coloredSymbol = switch (symbol) { // manages display colors according to the box
                        case 'b' -> // Bomb block
                                "\u001B[35mB";
                        case 'd' -> // Destructed block
                                "\u001B[90mX";
                        default -> "\u001B[" + (symbol - 'p' + 31) + 'm' + symbol;
                    } + "\u001B[0m";
                    displayed.append("║ ").append(coloredSymbol).append(' ');
                } else {
                    displayed.append("║   ");
                }
            }
            displayed.append("║\n         ═════════════════════════════════════════════\n");
        }
        displayed.append("Column:    A   B   C   D   E   F   G   H   I   J   K\n");
        return displayed.toString();
    }
}
