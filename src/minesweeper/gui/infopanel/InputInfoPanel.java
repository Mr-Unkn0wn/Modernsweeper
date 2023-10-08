package minesweeper.gui.infopanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class InputInfoPanel {

    static void addMouseMotionListener(InfoPanel infoPanel){
        infoPanel.addMouseMotionListener(new MouseMotionAdapter() {
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
