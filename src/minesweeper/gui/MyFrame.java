package minesweeper.gui;

import minesweeper.gamelogic.GameState;
import minesweeper.gui.gamepanel.GamePanel;
import minesweeper.gui.infopanel.TopBarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MyFrame extends JFrame {
    private final GameState gameState;

    public MyFrame(GameState gameState) {
        this.gameState = gameState;

        initFrame();

        GamePanel gamePanel = new GamePanel(this);
        add(gamePanel, BorderLayout.CENTER);

        TopBarPanel topBarPanel = new TopBarPanel(this);
        add(topBarPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void initFrame() {
        setUndecorated(true);
        setLayout(new BorderLayout());

        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getRootPane().setDoubleBuffered(true);
        setLocationRelativeTo(null);
        setResizable(false);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                repaint();
            }
        });
    }


    public GameState getGameState() {
        return gameState;
    }
}
