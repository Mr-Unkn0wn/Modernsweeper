package minesweeper;


import minesweeper.gamelogic.GameState;
import minesweeper.gui.MyFrame;

public class Main {
    public static void main(String[] args) {
        GameState gameState = new GameState(99, 30, 16);
        MyFrame myFrame = new MyFrame(gameState);
    }

}