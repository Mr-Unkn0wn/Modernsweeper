package minesweeper.gui.gamepanel;

import minesweeper.gamelogic.GameState;
import minesweeper.gui.Constants;
import minesweeper.gui.MyFrame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final int MIN_BORDER = 10;
    private static final int MAX_FIELD_SIZE = 35;
    final MyFrame frame;
    final FieldPanel fieldPanel;

    public GamePanel(MyFrame frame) {
        super();
        this.frame = frame;

        setBackground(Constants.DARK_GRAY);

        fieldPanel = new FieldPanel(this);
        add(fieldPanel);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        GameState game = frame.getGameState();

        int panelWidth = getWidth() - MIN_BORDER;
        int panelHeight = getHeight() - MIN_BORDER;

        int sizeX = panelWidth / game.getFieldWidth();
        int sizeY = panelHeight / game.getFieldHeight();

        int size = Math.min(sizeX, sizeY);

        size = Math.min(size, MAX_FIELD_SIZE);

        int fieldWidth = size * game.getFieldWidth();
        int fieldHeight = size * game.getFieldHeight();

        int borderX = (getWidth() - fieldWidth) / 2;
        int borderY = (getHeight() - fieldHeight) / 2;

        fieldPanel.tileSize = size;
        fieldPanel.setBounds(borderX, borderY, fieldWidth, fieldHeight);
    }
}
