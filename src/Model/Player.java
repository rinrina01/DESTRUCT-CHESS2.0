package Model;

public class Player {
    private String pseudo;
    private int posX;
    private int posY;
    private int score;
    private char symbol;
    private Map map;

    public Player(String pseudo, int posX, int posY, char symbol, Map map) {
        this.pseudo = pseudo;
        this.posX = posX;
        this.posY = posY;
        this.symbol = symbol;
        this.map = map;
        this.score = 0;
    }

    public boolean canMove() {
        /**
         * This function checks if the player can move in one or multiple directions.
         * 
         * @return boolean
         */

        return ((canMoveUp()) || (canMoveDown()) || (canMoveLeft()) || (canMoveRight()));
    }

    public boolean canMoveUp() {
        /**
         * This function checks if the player can move up.
         * 
         * @return boolean
         */

        return ((posY > 0) && (map.getMatrix()[posY - 1][posX] == 'a'));  // if upper square is 'a' (available) AND not a wall
    }

    public boolean canMoveLeft() {
        /**
         * This function checks if the player can move Left.
         * 
         * @return boolean
         */

        return ((posX > 0) && (map.getMatrix()[posY][posX - 1] == 'a')); // if left square is 'a' (available) AND not a wall
    }

    public boolean canMoveDown() {
        /**
         * This function checks if the player can move Down.
         * 
         * @return boolean
         */

        return ((posY < 9) && (map.getMatrix()[posY + 1][posX] == 'a')); // if down square is 'a' (available) AND not a wall
    }

    public boolean canMoveRight() {
        /**
         * This function checks if the player can move Right.
         * 
         * @return boolean
         */

        return ((posX < 10) && (map.getMatrix()[posY][posX + 1] == 'a'));  // if right square is 'a' (available) AND not a wall
    }

    public void moveUp() {
        /**
         * This function moves the player up in the map and in the variables.
         * 
         * @return void
         */

        map.setSquare(posX, posY, 'a');
        map.setSquare(posX, posY - 1, symbol);
        posY -= 1;
    }

    public void moveLeft() {
        /**
         * This function moves the player left in the map and in the variables.
         * 
         * @return void
         */

        map.setSquare(posX, posY, 'a');
        map.setSquare(posX - 1, posY, symbol);
        posX -= 1;
    }

    public void moveDown() {
        /**
         * This function moves the player down in the map and in the variables.
         * 
         * @return void
         */

        map.setSquare(posX, posY, 'a');
        map.setSquare(posX, posY + 1, symbol);
        posY += 1;
    }

    public void moveRight() {
        /**
         * This function moves the player right in the map and in the variables.
         * 
         * @return void
         */

        map.setSquare(posX, posY, 'a');
        map.setSquare(posX + 1, posY, symbol);
        posX += 1;
    }

    public void move(char direction) {
        /**
         * This function moves the player, changes his variables and changes the map.
         * 
         * @param direction : of type char
         **/

        switch (direction) {
            case 'z': // if the input is UP (Z key)
                if (canMoveUp()) {
                    moveUp();
                } else {
                    System.out.println("You can't go there!");
                }

                break;
            case 'q': // if the input is LEFT (Q key)
                if (canMoveLeft()) { 
                    moveLeft();
                } else {
                    System.out.println("You can't go there!");
                }

                break;
            case 's': // if the input is DOWN (S key)
                if (canMoveDown()) {
                    moveDown();
                } else {
                    System.out.println("You can't go there!");
                }

                break;
            case 'd': // if the input is RIGHT (D key)
                if (canMoveRight()) {
                    moveRight();
                } else {
                    System.out.println("You can't go there!");
                }
                break;
        }
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
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
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
