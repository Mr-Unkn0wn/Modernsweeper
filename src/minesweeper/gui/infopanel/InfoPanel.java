package minesweeper.gui.infopanel;

import minesweeper.gamelogic.GameState;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    public InfoPanel(Frame frame){
        super();
        InputInfoPanel.addMouseMotionListener(this);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
