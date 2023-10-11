package minesweeper.gui.infopanel;

import minesweeper.gui.MyFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

class InputTopBarPanel extends MouseAdapter implements MouseMotionListener {


    private final TopBarPanel topBarPanel;
    private int mouseStartingX = 0;
    private int mouseStaringY = 0;
    private int frameStartingX = 0;
    private int frameStartingY = 0;

    public InputTopBarPanel(TopBarPanel topBarPanel) {
        this.topBarPanel = topBarPanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        Point point = e.getLocationOnScreen();

        int xMovement = (int) (point.getX() - mouseStartingX);
        int yMovement = (int) (point.getY() - mouseStaringY);

        MyFrame frame = topBarPanel.frame;

        frame.setLocation(frameStartingX + xMovement, frameStartingY + yMovement);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        frameStartingX = topBarPanel.frame.getX();
        frameStartingY = topBarPanel.frame.getY();

        Point point = e.getLocationOnScreen();

        mouseStartingX = (int) point.getX();
        mouseStaringY = (int) point.getY();
    }
}
