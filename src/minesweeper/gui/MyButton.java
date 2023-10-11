package minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// maybe make my own button without extending JButton? seems to be not flexible enough
public class MyButton extends JPanel {

    private final Image img;
    private Color highlightedColor;

    public MyButton(String imageName, Runnable task) {
        img = new ImageIcon("img/" + imageName).getImage();
        highlightedColor = Constants.BORDER_GRAY;

        setBackground(Constants.TOP_BAR_GRAY);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                task.run();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Change the background or border when the mouse enters
                setBackground(highlightedColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Reset the background or border when the mouse exits
                setBackground(Constants.TOP_BAR_GRAY);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int offsetX = (getWidth() - img.getWidth(null)) / 2;
        int offsetY = (getHeight() - img.getHeight(null)) / 2;
        g.drawImage(img, offsetX, offsetY, null);
    }

    public void setHighlightedColor(Color highlightedColor) {
        this.highlightedColor = highlightedColor;
    }
}
