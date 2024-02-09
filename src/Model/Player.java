package Model;

import Vue.Cli;

public class Player {
    private final String pseudo;
    private int posX;
    private int posY;
    private int score;
    private final char symbol;
    private final Map map;

    public Player(String pseudo, int posX, int posY, char symbol, Map map) {
        this.pseudo = pseudo;
        this.posX = posX;
        this.posY = posY;
        this.symbol = symbol;
        this.map = map;
        this.score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public char getSymbol() {
        return symbol;
    }

    boolean canMoveUp() {
        /**
         * This function checks if the player can move up.
         */

        return ((posY > 0) && (map.getMatrix()[posY - 1][posX] == 'a')); // if upper square is 'a' (available) AND not a
                                                                         // wall
    }

    boolean canMoveLeft() {
        /**
         * This function checks if the player can move Left.
         */

        return ((posX > 0) && (map.getMatrix()[posY][posX - 1] == 'a')); // if left square is 'a' (available) AND not a
                                                                         // wall
    }

    boolean canMoveDown() {
        /**
         * This function checks if the player can move Down.
         */

        return ((posY < 9) && (map.getMatrix()[posY + 1][posX] == 'a')); // if down square is 'a' (available) AND not a
                                                                         // wall
    }

    boolean canMoveRight() {
        /**
         * This function checks if the player can move Right.
         */

        return ((posX < 10) && (map.getMatrix()[posY][posX + 1] == 'a')); // if right square is 'a' (available) AND not
                                                                          // a wall
    }

    public boolean canMove() {
        /**
         * This function checks if the player can move in one or multiple directions.
         */

        return ((canMoveUp()) || (canMoveDown()) || (canMoveLeft()) || (canMoveRight()));
    }

    void moveUp() {
        /**
         * This function moves the player up in the map and in the variables.
         */

        map.setSquare(posX, posY, 'a');
        map.setSquare(posX, posY - 1, symbol);
        posY -= 1;
    }

    void moveLeft() {
        /**
         * This function moves the player left in the map and in the variables.
         */

        map.setSquare(posX, posY, 'a');
        map.setSquare(posX - 1, posY, symbol);
        posX -= 1;
    }

    void moveDown() {
        /**
         * This function moves the player down in the map and in the variables.
         */

        map.setSquare(posX, posY, 'a');
        map.setSquare(posX, posY + 1, symbol);
        posY += 1;
    }

    void moveRight() {
        /**
         * This function moves the player right in the map and in the variables.
         */

        map.setSquare(posX, posY, 'a');
        map.setSquare(posX + 1, posY, symbol);
        posX += 1;
    }

    boolean checkBombBlock(char direction) {
        /**
         * This function checks a bomb block and do the action.
         *
         * @param direction : the direction of player's movement
         * @return bool : if the square where the player is going to is a bomb
         */
        return switch (direction) {
            case 'z' -> // BOMB IS UPPER ?
                // If the block of the player's direction is a bomb
                    ((getPosY() > 0) && (map.getSquare(getPosX(), getPosY() - 1) == 'b'));
            case 's' -> // BOMB IS DOWNER ?
                // If the block of the player's direction is a bomb
                    ((getPosY() < 9) && (map.getSquare(getPosX(), getPosY() + 1) == 'b'));
            case 'q' -> // BOMB IS LEFTER ?
                // If the block of the player's direction is a bomb
                    ((getPosX() > 0) && (map.getSquare(getPosX() - 1, getPosY()) == 'b'));
            case 'd' -> // BOMB IS RIGHTER ?
                // If the block of the player's direction is a bomb
                    ((getPosX() < 10) && (map.getSquare(getPosX() + 1, getPosY()) == 'b'));
            default -> false;
        };

    }

    void enclosePlayerWithDestructedBlocks(int column, int row) {
        /**
         * This function encloses a cell in the map of destroyed blocks.
         *
         * @param column : the column position of the block designed
         * @param row : the row position of the block designed
         */

        for (int x = column - 1; x <= 1 + column; x++) { // replaces all the blocks around the block selected
            for (int y = row - 1; y <= 1 + row; y++) { // replaces all the blocks around the block selected
                if ((x >= 0) && (x <= 10) && (y >= 0) && (y <= 9)) { // avoid out of ranges
                    if (map.getSquare(x, y) == 'a') { // if the square is available
                        map.setSquare(x, y, 'd');
                    }
                }

            }
        }

    }

    void move(char direction) {
        /**
         * This function moves the player, changes his variables and changes the map.
         * 
         * @param direction : direction where the player moves (z, q, s, d)
         **/

        boolean isBombBlock = checkBombBlock(direction);

        switch (direction) {
            case 'z': // if the input is UP (Z key)
                if (canMoveUp() || isBombBlock) {
                    moveUp();
                    if (isBombBlock) { // if a bomb is in the direction
                        enclosePlayerWithDestructedBlocks(getPosX(), getPosY());
                    }
                } else { // if there is a wall in this direction
                    System.out.println("You can't go there!");
                }
                break;

            case 'q': // if the input is LEFT (Q key)
                if (canMoveLeft() || isBombBlock) {
                        moveLeft();
                        if (isBombBlock) { // if a bomb is in the direction
                            enclosePlayerWithDestructedBlocks(getPosX(), getPosY());
                        }
                } else { // if there is a wall in this direction
                    System.out.println("You can't go there!");
                }
                break;

            case 's': // if the input is DOWN (S key)
                if (canMoveDown() || isBombBlock) {
                        moveDown();
                        if (isBombBlock) { // if a bomb is in the direction
                            enclosePlayerWithDestructedBlocks(getPosX(), getPosY());
                        }
                } else { // if there is a wall in this direction
                    System.out.println("You can't go there!");
                }
                break;

            case 'd': // if the input is RIGHT (D key)
                if (canMoveRight() || isBombBlock) {
                        moveRight();
                        if (isBombBlock) { // if a bomb is in the direction
                            enclosePlayerWithDestructedBlocks(getPosX(), getPosY());
                        }
                } else { // if there is a wall in this direction
                    System.out.println("You can't go there!");
                }
                break;
        }
    }

    public void getMovement() {
        /*
         * This function takes and manages player movement inputs.
         * 
         * @param player : of type Player
         */

        String direction;

        label:
        while (true) {
            direction = Cli.sc.nextLine();
            switch (direction) {
                case "z":
                case "Z":
                    move('z');
                    break label;
                case "q":
                case "Q":
                    move('q');
                    break label;
                case "s":
                case "S":
                    move('s');
                    break label;
                case "d":
                case "D":
                    move('d');
                    break label;
                default:
                    System.out.println(map);
                    System.out.println("Please enter Z, Q, S or D");
                    break;
            }
        }
    }
}
