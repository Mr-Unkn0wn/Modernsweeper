package minesweeper.gui.gamepanel;

import minesweeper.gamelogic.Tile;
import minesweeper.gui.ColorPallet;
import minesweeper.gui.MyFrame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final static int SHADOW_SIZE = 2;
    MyFrame frame;

    int current_field_size;

    public GamePanel(MyFrame frame) {
        super();
        this.frame = frame;
        InputGamePanel inputGamePanel = new InputGamePanel(this);
        addMouseListener(inputGamePanel);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintField(g);
    }

    private void paintField(Graphics g) {
        int borderOffset = 2;

        int sizeX = getWidth() / frame.getGameState().getFieldWidth();
        int sizeY = getHeight() / frame.getGameState().getFieldHeight();
        int size = Math.min(sizeX, sizeY);
        current_field_size = size;

        for (int y = 0; y < frame.getGameState().getFieldHeight(); y++) {
            for (int x = 0; x < frame.getGameState().getFieldWidth(); x++) {
                Tile tile = frame.getGameState().getField()[x][y];

                int xPixel = x * size + borderOffset;
                int yPixel = y * size + borderOffset;

                if (tile.isClosed()) {
                    paintClosedField(g, xPixel, yPixel, size);

                    if (tile.isFlagged())
                        paintFlag(g, xPixel, yPixel, size);
                } else {
                    if (tile.isBomb())
                        paintBomb(g, xPixel, yPixel, size);
                    else
                        paintNumbers(g, xPixel, yPixel, size, tile.getNumber());
                }

            }
        }
    }

    private void paintClosedField(Graphics g, int x, int y, int size) {
        g.setColor(ColorPallet.GRAY.brighter().brighter());
        Polygon topPoly = new Polygon();
        topPoly.addPoint(x, y);
        topPoly.addPoint(x + size, y);
        topPoly.addPoint(x, y + size);
        g.fillPolygon(topPoly);

        g.setColor(ColorPallet.GRAY.darker().darker());
        Polygon bottomPoly = new Polygon();
        bottomPoly.addPoint(x + size, y + size);
        bottomPoly.addPoint(x + size, y);
        bottomPoly.addPoint(x, y + size);
        g.fillPolygon(bottomPoly);

        g.setColor(ColorPallet.GRAY);
        g.fillRoundRect(x + SHADOW_SIZE, y + SHADOW_SIZE, size - 2 * SHADOW_SIZE, size - 2 * SHADOW_SIZE, 2 * SHADOW_SIZE, 2 * SHADOW_SIZE);
    }

    private void paintFlag(Graphics g, int x, int y, int size) {
        g.setColor(Color.RED);
        int diameter = size / 2;

        x = x + size / 2 - diameter / 2;
        y = y + size / 2 - diameter / 2;

        g.fillOval(x, y, diameter, diameter);
    }

    private void paintBomb(Graphics g, int x, int y, int size) {
        g.setColor(Color.BLACK);
        int diameter = size / 2;

        x = x + size / 2 - diameter / 2;
        y = y + size / 2 - diameter / 2;

        g.fillOval(x, y, diameter, diameter);
    }

    private void paintNumbers(Graphics g, int x, int y, int size, int number) {
        if(number == 0)
            return;

        g.setColor(Color.WHITE);

        x = x + size / 2;
        y = y + size / 2;

        g.drawChars(String.valueOf(number).toCharArray(), 0, 1, x, y);
    }
}
