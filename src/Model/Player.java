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

    boolean checkBombBlock(char direction) {
        /**
         * This function checks a bomb block and do the action
         * @return bool : if the square, the player is going to is a bomb
         */
        switch (direction) {
            case 'z': // BOMB IS UPPER ?
                if ((getPosY() > 0) && (map.getSquare(getPosX(), getPosY()-1) == 'b')) { // If the block of the player's direction is a bomb
                    return true;
                } else {
                    return false;
                }
                

            case 's': // BOMB IS DOWNER ?
                if ((getPosY() < 9) && (map.getSquare(getPosX(), getPosY()+1) == 'b')) { // If the block of the player's direction is a bomb
                    return true;
                } else {
                    return false;
                }

            case 'q': // BOMB IS LEFTER ?
                if ((getPosX() > 0) && (map.getSquare(getPosX()-1, getPosY()) == 'b')) { // If the block of the player's direction is a bomb
                    return true;
                } else {
                    return false;
                }

            case 'd': // BOMB IS RIGHTER ?
                if ((getPosX() < 10) && (map.getSquare(getPosX()+1, getPosY()) == 'b')) { // If the block of the player's direction is a bomb
                    return true;
                } else {
                    return false;
                }

        }
        return false;
        
    }

    void enclosePlayerWithDestructedBlocks(int column, int row) {
        /*
         * This function encloses a cell in the map of destroyed blocks
         * @param column : the column position of the block designed
         * @param row : the row position of the block designed
         */
        int x;
        int y; 

        for (x=column;x<3+column;x++) { // replaces all the blocks arond the block selected
            for (y=-1+row;y<2+row;y++) { // replaces all the blocks arond the block selected
                System.out.println(x+" | "+y);
                if ((x > 0) || (y > 0) || (x < 10) || (y < 9)) { // avoid out of ranges
                    if (map.getSquare(x, y) == 'a') {// if the square is available
                        System.out.println("ok");
                        map.setSquare(x, y, 'd');
                    }
                }
                
            }
        }

    }


    boolean canMoveUp() {
        /**
         * This function checks if the player can move up.
         * 
         * @return boolean
         */

        return ((posY > 0) && (map.getMatrix()[posY - 1][posX] == 'a')); // if upper square is 'a' (available) AND not a
                                                                         // wall
    }

    boolean canMoveLeft() {
        /**
         * This function checks if the player can move Left.
         * 
         * @return boolean
         */

        return ((posX > 0) && (map.getMatrix()[posY][posX - 1] == 'a')); // if left square is 'a' (available) AND not a
                                                                         // wall
    }

    boolean canMoveDown() {
        /**
         * This function checks if the player can move Down.
         * 
         * @return boolean
         */

        return ((posY < 9) && (map.getMatrix()[posY + 1][posX] == 'a')); // if down square is 'a' (available) AND not a
                                                                         // wall
    }

    boolean canMoveRight() {
        /**
         * This function checks if the player can move Right.
         * 
         * @return boolean
         */

        return ((posX < 10) && (map.getMatrix()[posY][posX + 1] == 'a')); // if right square is 'a' (available) AND not
                                                                          // a wall
    }

    public boolean canMove() {
        /**
         * This function checks if the player can move in one or multiple directions.
         * 
         * @return boolean
         */

        return ((canMoveUp()) || (canMoveDown()) || (canMoveLeft()) || (canMoveRight()));
    }

    void moveUp() {
        /**
         * This function moves the player up in the map and in the variables.
         * 
         * @return void
         */

        map.setSquare(posX, posY, 'a');
        map.setSquare(posX, posY - 1, symbol);
        posY -= 1;
    }

    void moveLeft() {
        /**
         * This function moves the player left in the map and in the variables.
         * 
         * @return void
         */

        map.setSquare(posX, posY, 'a');
        map.setSquare(posX - 1, posY, symbol);
        posX -= 1;
    }

    void moveDown() {
        /**
         * This function moves the player down in the map and in the variables.
         * 
         * @return void
         */

        map.setSquare(posX, posY, 'a');
        map.setSquare(posX, posY + 1, symbol);
        posY += 1;
    }

    void moveRight() {
        /**
         * This function moves the player right in the map and in the variables.
         * 
         * @return void
         */

        map.setSquare(posX, posY, 'a');
        map.setSquare(posX + 1, posY, symbol);
        posX += 1;
    }

    void move(char direction) {
        /**
         * This function moves the player, changes his variables and changes the map.
         * 
         * @param direction : of type char
         **/

        boolean isBombBlock = checkBombBlock(direction);

        switch (direction) {
            case 'z': // if the input is UP (Z key)
                if (canMoveUp() || isBombBlock == true) {
                    moveUp();
                    if (isBombBlock) { // if a bomb is in the direction
                        System.out.println("The player has touched the bomb, is locked in and has now lost.");
                        enclosePlayerWithDestructedBlocks(getPosX()+1, getPosY());
                    }
                } else { // if there a wall in this direction
                    System.out.println("You can't go there!");
                }
                break;

            case 'q': // if the input is LEFT (Q key)
                if (canMoveLeft() || isBombBlock == true) {
                        moveLeft();
                        if (isBombBlock) { // if a bomb is in the directio
                            System.out.println("The player has touched the bomb, is locked in and has now lost.");
                            enclosePlayerWithDestructedBlocks(getPosX()-1, getPosY());
                        }
                } else { // if there a wall in this direction
                    System.out.println("You can't go there!");
                }
                break;

            case 's': // if the input is DOWN (S key)
                if (canMoveDown() || isBombBlock == true) {
                        moveDown();
                        if (isBombBlock) { // if a bomb is in the directio
                            System.out.println("The player has touched the bomb, is locked in and has now lost.");
                            enclosePlayerWithDestructedBlocks(getPosX(), getPosY()+1);
                        }
                } else { // if there a wall in this direction
                    System.out.println("You can't go there!");
                }
                break;

            case 'd': // if the input is RIGHT (D key)
                if (canMoveRight() || isBombBlock == true) {
                        moveRight();
                        if (isBombBlock) { // if a bomb is in the directio
                            System.out.println("The player has touched the bomb, is locked in and has now lost.");
                            enclosePlayerWithDestructedBlocks(getPosX()+1, getPosY());
                        }
                } else { // if there a wall in this direction
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

        while (true) {
            direction = Cli.sc.nextLine();
            if ((direction.equals("z")) || (direction.equals("Z"))) {
                move('z');
                break;
            } else if ((direction.equals("q")) || (direction.equals("Q"))) {
                move('q');
                break;
            } else if ((direction.equals("s")) || (direction.equals("S"))) {
                move('s');
                break;
            } else if ((direction.equals("d")) || (direction.equals("D"))) {
                move('d');
                break;
            } else {
                System.out.println(map);
                System.out.println("Please enter Z, Q, S or D");
            }
        }
    }
}
