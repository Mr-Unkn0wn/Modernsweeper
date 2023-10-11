package minesweeper.gui.gamepanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class InputGamePanel extends MouseAdapter {

    private final FieldPanel fieldPanel;

    InputGamePanel(FieldPanel fieldPanel) {
        this.fieldPanel = fieldPanel;

        InputMap inputMap = fieldPanel.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = fieldPanel.getActionMap();

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

        Point panelPosition = fieldPanel.getLocationOnScreen();

        int panelX = (int) panelPosition.getX();
        int panelY = (int) panelPosition.getY();

        mouseX -= panelX;
        mouseY -= panelY;

        if(mouseX < 0 || mouseX >= fieldPanel.getWidth())
            return;

        if(mouseY < 0 || mouseY >= fieldPanel.getHeight())
            return;

        int x = pixelLocationToTileCoordinate(mouseX);
        int y = pixelLocationToTileCoordinate(mouseY);

        if(!isTileCoordinateValid(x,y))
            return;

        fieldPanel.gamePanel.frame.getGameState().spacePressed(x,y);
        SwingUtilities.invokeLater(() -> fieldPanel.gamePanel.frame.repaint());

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = pixelLocationToTileCoordinate(e.getX());
        int y = pixelLocationToTileCoordinate(e.getY());

        if(!isTileCoordinateValid(x,y))
            return;


        if (e.getButton() == MouseEvent.BUTTON1) {
            fieldPanel.gamePanel.frame.getGameState().leftClicked(x, y);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            fieldPanel.gamePanel.frame.getGameState().rightClicked(x, y);
        }
        SwingUtilities.invokeLater(() -> fieldPanel.gamePanel.frame.repaint());
    }

    private int pixelLocationToTileCoordinate(int pixel) {
        return pixel / fieldPanel.tileSize;
    }

    private boolean isTileCoordinateValid(int x, int y) {
        if (x >= fieldPanel.gamePanel.frame.getGameState().getFieldWidth())
            return false;

        if (y >= fieldPanel.gamePanel.frame.getGameState().getFieldHeight())
            return false;

        if (x < 0)
            return false;

        if (y < 0)
            return false;

        return true;
    }

}
