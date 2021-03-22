package tictactoe;

public class Cell {
    private int x,y;

    Cell()
    {
        this.x = 420;
        this.y = 420;
    }

    Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
