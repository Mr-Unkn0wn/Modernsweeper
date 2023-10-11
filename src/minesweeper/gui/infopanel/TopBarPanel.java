package minesweeper.gui.infopanel;

import minesweeper.gui.MyButton;
import minesweeper.gui.MyFrame;

import javax.swing.*;
import java.awt.*;

public class TopBarPanel extends JPanel {

    static final int TOP_BAR_HEIGHT = 42;
    static final int buttonWidth = 38;
    final MyFrame frame;

    public TopBarPanel(MyFrame frame) {
        super();
        this.frame = frame;

        setPreferredSize(new Dimension(frame.getWidth(), TOP_BAR_HEIGHT));

        InputTopBarPanel inputTopBarPanel = new InputTopBarPanel(this);
        addMouseListener(inputTopBarPanel);
        addMouseMotionListener(inputTopBarPanel);

        setLayout(new BorderLayout());




        add(new LabelSettingPanel(this), BorderLayout.WEST);
        add(new InfoPanel(this), BorderLayout.CENTER);
        add(new WindowControlPanel(this), BorderLayout.EAST);



    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        // g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
    }
}
