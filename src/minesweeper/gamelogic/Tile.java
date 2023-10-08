package minesweeper.gamelogic;

/*
* States
* - closed
*   - flagged
*   - not flagged
*   - bomb
* - open
*   - number
*   - bomb
* */
public class Tile {

    private int y = 0;
    private int x = 0;
    private int number = 0;
    private boolean bomb = false;
    private boolean closed = true;
    private boolean flagged = false;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public boolean isClosed() {
        return closed;
    }

    public void open() {
        if(!closed)
            throw new AssertionError("A tile should never be opened twice!");
        this.closed = false;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        if(isClosed())
            this.flagged = flagged;
    }
}