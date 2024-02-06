package Model;

public class Player {
    private String pseudo;
    private int posX;
    private int posY;
    private int score;

    public Player(String pseudo, int posX, int posY, int score) {
        this.pseudo = pseudo;
        this.posX = posX;
        this.posY = posY;
        this.score = score;
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
}
