package minesweeper.gui.borderpanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class InputBorderPanel {

    static void addMouseMotionListener(BorderPanel borderPanel){
        borderPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });

    }
}
