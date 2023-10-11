package minesweeper.gui.infopanel;

import minesweeper.gui.MyButton;

import javax.swing.*;
import java.awt.*;

import static minesweeper.gui.infopanel.TopBarPanel.TOP_BAR_HEIGHT;

public class LabelSettingPanel extends JPanel {

    private final TopBarPanel topBarPanel;

    LabelSettingPanel(TopBarPanel topBarPanel) {
        this.topBarPanel = topBarPanel;

        setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));

        setOpaque(false);
        setPreferredSize(new Dimension(TopBarPanel.buttonWidth *3, TOP_BAR_HEIGHT));

        MyButton buttonLeft = new MyButton("menu.png", () -> System.out.println("settings"));
        buttonLeft.setPreferredSize(new Dimension(TopBarPanel.buttonWidth, TOP_BAR_HEIGHT));
        add(buttonLeft);
    }
}
