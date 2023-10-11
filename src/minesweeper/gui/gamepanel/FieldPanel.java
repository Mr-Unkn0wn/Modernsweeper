package minesweeper.gui.gamepanel;

import minesweeper.gamelogic.GameState;
import minesweeper.gamelogic.Tile;
import minesweeper.gui.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class FieldPanel extends JPanel {

    private static final int ROUNDING = 15;
    int tileSize;
    GamePanel gamePanel;

    FieldPanel(GamePanel gamePanel) {
        setBackground(Constants.DARK_GRAY);
        this.gamePanel = gamePanel;


        InputGamePanel inputGamePanel = new InputGamePanel(this);
        addMouseListener(inputGamePanel);
    }


    /**
     * IDEA:
     * 1. Dark Gray Background ( done )
     * 2. Orange Overlay ( done )
     * 4. Open fields
     * 3. Lines ( done )
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        // g2d.setFont(new Font("Arial", Font.BOLD, 24));

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintCoveredArea(g2d);
        paintOpenFields(g2d);
        paintLines(g2d);
    }

    private void paintCoveredArea(Graphics2D g2d) {
        g2d.setColor(Constants.TOP_BAR_GRAY);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), ROUNDING, ROUNDING);
    }

    private void paintLines(Graphics2D g2d) {
        g2d.setColor(Constants.BORDER_GRAY);

        int gap = 5;
        GameState game = gamePanel.frame.getGameState();

        for (int x = 0; x < game.getFieldWidth(); x++) {
            for (int y = 0; y < game.getFieldHeight(); y++) {
                if (x != 0)
                    paintLineOnTheLeft(g2d, x, y, gap);

                if (y != 0)
                    paintLineOnTheTop(g2d, x, y, gap);
            }
        }
    }

    private void paintLineOnTheLeft(Graphics2D g2d, int x, int y, int gap) {
        Point p = indexToPixel(x, y);

        int topY = p.y + gap;
        int botY = p.y + tileSize - gap;

        g2d.drawLine(p.x, topY, p.x, botY);
    }

    private void paintLineOnTheTop(Graphics2D g2d, int x, int y, int gap) {
        Point p = indexToPixel(x, y);

        int leftX = p.x + gap;
        int rightX = p.x + tileSize - gap;

        g2d.drawLine(leftX, p.y, rightX, p.y);
    }

    private Point indexToPixel(int x, int y) {
        Point p = new Point();

        p.x = x * tileSize;
        p.y = y * tileSize;

        return p;
    }

    private void paintOpenFields(Graphics2D g2d) {
        GameState game = gamePanel.frame.getGameState();
        Tile[][] field = game.getField();

        Arrays.stream(field)
                .parallel()
                .forEach(arr -> Arrays.stream(arr)
                        .parallel()
                        .filter(tile -> !tile.isClosed())
                        .forEach(tile -> paintOpenField(g2d, tile)));
    }

    private void paintOpenField(Graphics2D g2d, Tile tile) {
        Point p = indexToPixel(tile.getX(), tile.getY());

        int oversize = 4;

        g2d.setColor(Color.RED);
        g2d.fillRoundRect(p.x - oversize, p.y - oversize, tileSize + oversize * 2, tileSize + oversize * 2, ROUNDING, ROUNDING);

        g2d.setColor(Constants.TOP_BAR_TEXT);
        if (tile.getNumber() > 0) {

            String nr = String.valueOf(tile.getNumber());


            g2d.drawString(nr, tile.getX(), tile.getY());
        }
    }

}
