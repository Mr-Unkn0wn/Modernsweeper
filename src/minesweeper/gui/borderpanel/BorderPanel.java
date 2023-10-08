package minesweeper.gui.borderpanel;


import javax.swing.*;
import java.awt.*;

public class BorderPanel extends JPanel {

    public BorderPanel(){
        super();
        InputBorderPanel.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
