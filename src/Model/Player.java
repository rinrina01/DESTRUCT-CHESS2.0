package Model;

public class Player {
    private String pseudo;
    private int posX;
    private int posY;
    private int score;
    private char symbol;

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Player(String pseudo, int posX, int posY, int score, char symbol) {
        this.pseudo = pseudo;
        this.posX = posX;
        this.posY = posY;
        this.score = score;
        this.symbol = symbol;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
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
