package minesweeper.gui;

import minesweeper.gamelogic.GameState;
import minesweeper.gui.gamepanel.GamePanel;
import minesweeper.gui.infopanel.TopBarPanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MyFrame extends JFrame {
    private final GameState gameState;

    public MyFrame(GameState gameState) {
        this.gameState = gameState;
        GamePanel gamePanel = new GamePanel(this);
        TopBarPanel topBarPanel = new TopBarPanel(this);



        initFrame(gamePanel, topBarPanel);

        setVisible(true);
    }

    private void initFrame(GamePanel gamePanel, TopBarPanel topBarPanel) {
        setUndecorated(true);

        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                repaint();
            }
        });
        setLayout(new BorderLayout());



        topBarPanel.setBackground(Constants.TOP_BAR_GRAY);
        topBarPanel.setBorder(new MatteBorder(0,0,2,0, Constants.BORDER_GRAY));
        add(topBarPanel, BorderLayout.NORTH);

        gamePanel.setBackground(Constants.DARK_GRAY);
        add(gamePanel, BorderLayout.CENTER);




    }


    public GameState getGameState() {
        return gameState;
    }
}
