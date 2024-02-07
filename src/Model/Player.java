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
        return ((canMoveUp()) || (canMoveDown()) || (canMoveLeft()) || (canMoveRight()));
    }

    public boolean canMoveUp() {
        return ((posY > 0) && (map.getMatrix()[posY - 1][posX] == 'a'));
    }

    public boolean canMoveLeft() {
        return ((posX > 0) && (map.getMatrix()[posY][posX - 1] == 'a'));
    }

    public boolean canMoveDown() {
        return ((posY < 9) && (map.getMatrix()[posY + 1][posX] == 'a'));
    }

    public boolean canMoveRight() {
        return ((posX < 10) && (map.getMatrix()[posY][posX + 1] == 'a'));
    }

    public void moveUp() {
        map.setSquare(posX, posY, 'a');
        map.setSquare(posX, posY - 1, symbol);
        posY -= 1;
    }

    public void moveLeft() {
        map.setSquare(posX, posY, 'a');
        map.setSquare(posX - 1, posY, symbol);
        posX -= 1;
    }

    public void moveDown() {
        map.setSquare(posX, posY, 'a');
        map.setSquare(posX, posY + 1, symbol);
        posY += 1;
    }

    public void moveRight() {
        map.setSquare(posX, posY, 'a');
        map.setSquare(posX + 1, posY, symbol);
        posX += 1;
    }

    public void move(char direction) {
        /**
         * This function moves the player, changes his values and changes the blocks of
         * the map
         * 
         * @param drection of type char
         **/

        switch (direction) {
            case 'z': // If is UP (Z key)
                if (canMoveUp()) { // IF upper square is 'a' (available) AND not a wall
                    moveUp();
                } else {
                    System.out.println("You can't go there!");
                }

                break;
            case 'q': // If is LEFT (Q key)
                if (canMoveLeft()) { // IF left square is 'a' (available) AND not a wall
                    moveLeft();
                } else {
                    System.out.println("You can't go there!");
                }

                break;
            case 's': // If is DOWN (S key)
                if (canMoveDown()) { // IF down square is 'a' (available) AND not a wall
                    moveDown();
                } else {
                    System.out.println("You can't go there!");
                }

                break;
            case 'd': // If is RIGHT (D key)
                if (canMoveRight()) { // IF right square is 'a' (available) AND not a wall
                    moveRight();
                } else {
                    System.out.println("You can't go there!");
                }
                break;
        }
    }

    public boolean gameOver() {

        // Tester si le joueur est dans un coin
        // Tester s'il reste au moins un 'a' autour du joueur

        return false;
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
