package minesweeper.gui.gamepanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class InputGamePanel extends MouseAdapter {

    private final GamePanel gamePanel;

    InputGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        InputMap inputMap = gamePanel.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = gamePanel.getActionMap();

        KeyStroke spaceKeyStroke = KeyStroke.getKeyStroke("SPACE");
        inputMap.put(spaceKeyStroke, "SpacePressed");

        actionMap.put("SpacePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spacePressed();
            }
        });
    }

    private void spacePressed() {
        Point mousePosition = MouseInfo.getPointerInfo().getLocation();

        if (mousePosition == null)
            return;

        int mouseX = (int) mousePosition.getX();
        int mouseY = (int) mousePosition.getY();

        Point panelPosition = gamePanel.getLocationOnScreen();

        int panelX = (int) panelPosition.getX();
        int panelY = (int) panelPosition.getY();

        mouseX -= panelX;
        mouseY -= panelY;

        if(mouseX < 0 || mouseX >= gamePanel.getWidth())
            return;

        if(mouseY < 0 || mouseY >= gamePanel.getHeight())
            return;

        int x = pixelLocationToTileCoordinate(mouseX);
        int y = pixelLocationToTileCoordinate(mouseY);

        if(!isTileCoordinateValid(x,y))
            return;

        gamePanel.frame.getGameState().spacePressed(x,y);
        gamePanel.frame.repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = pixelLocationToTileCoordinate(e.getX());
        int y = pixelLocationToTileCoordinate(e.getY());

        if(!isTileCoordinateValid(x,y))
            return;

        if (e.getButton() == MouseEvent.BUTTON1) {
            gamePanel.frame.getGameState().leftClicked(x, y);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            gamePanel.frame.getGameState().rightClicked(x, y);
        }
        gamePanel.frame.repaint();
    }

    private int pixelLocationToTileCoordinate(int pixel) {
        return pixel / gamePanel.current_tile_size;
    }

    private boolean isTileCoordinateValid(int x, int y) {
        if (x >= gamePanel.frame.getGameState().getFieldWidth())
            return false;

        if (y >= gamePanel.frame.getGameState().getFieldHeight())
            return false;

        if (x < 0)
            return false;

        if (y < 0)
            return false;

        return true;
    }

}
