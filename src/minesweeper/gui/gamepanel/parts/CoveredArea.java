package minesweeper.gui.gamepanel.parts;

import minesweeper.gui.gamepanel.GamePanel;

import java.awt.*;

public class CoveredArea {

    GamePanel gamePanel;

    public CoveredArea(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void paintCoveredArea(Graphics g){
        Polygon poly = new Polygon();
        g.drawPolygon();
    }
}
