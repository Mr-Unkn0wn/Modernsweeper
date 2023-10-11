package minesweeper.gui.infopanel;

import minesweeper.gui.MyButton;

import javax.swing.*;
import java.awt.*;

import static minesweeper.gui.infopanel.TopBarPanel.TOP_BAR_HEIGHT;

class WindowControlPanel extends JPanel {

    private final TopBarPanel topBarPanel;

    private boolean fullscreen = false;

    public WindowControlPanel(TopBarPanel topBarPanel){
        this.topBarPanel = topBarPanel;


        setOpaque(false);
        setPreferredSize(new Dimension(TopBarPanel.buttonWidth *3, TOP_BAR_HEIGHT));
        setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));

        MyButton buttonMin = new MyButton("minimize.png", this::min);
        buttonMin.setPreferredSize(new Dimension(TopBarPanel.buttonWidth, TOP_BAR_HEIGHT));

        MyButton buttonMax= new MyButton("maximize.png", this::max);
        buttonMax.setPreferredSize(new Dimension(TopBarPanel.buttonWidth, TOP_BAR_HEIGHT));

        MyButton buttonClose= new MyButton("x.png", this::close);
        buttonClose.setPreferredSize(new Dimension(TopBarPanel.buttonWidth, TOP_BAR_HEIGHT));
        buttonClose.setHighlightedColor(new Color(155, 33, 33));

        add(buttonMin);
        add(buttonMax);
        add(buttonClose);
    }

    private void min(){
        topBarPanel.frame.setExtendedState(JFrame.ICONIFIED);

    }

    private void max(){
        if(!fullscreen)
            topBarPanel.frame.setSize(1920, 1080);
        else
            topBarPanel.frame.setSize(1280, 720);
        topBarPanel.frame.setLocationRelativeTo(null);

        fullscreen = !fullscreen;
    }

    private void close(){
        System.exit(0);
    }
}
