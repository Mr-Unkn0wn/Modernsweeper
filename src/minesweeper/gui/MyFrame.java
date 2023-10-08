package minesweeper.gui;

import minesweeper.gamelogic.GameState;
import minesweeper.gui.borderpanel.BorderPanel;
import minesweeper.gui.gamepanel.GamePanel;
import minesweeper.gui.infopanel.InfoPanel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MyFrame extends JFrame {

    private static final float BORDER_FACTOR = 0.01f;
    private static final float INFO_PANEL_HEIGHT_FACTOR = 0.1f;

    GameState gameState;

    public MyFrame(GameState gameState) {
        this.gameState = gameState;
        BorderPanel borderPanel = new BorderPanel();
        GamePanel gamePanel = new GamePanel(this);
        InfoPanel infoPanel = new InfoPanel(this);


        initFrame(borderPanel, gamePanel, infoPanel);

        generateLayout(borderPanel, gamePanel, infoPanel);
        setVisible(true);
    }

    private void initFrame(JPanel borderPanel, GamePanel gamePanel, InfoPanel infoPanel) {
        // frame.setUndecorated(true);

        setSize(1200, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                generateLayout(borderPanel, gamePanel, infoPanel);
                repaint();
            }
        });

        borderPanel.setBackground(ColorPallet.GRAY);
        borderPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        borderPanel.setLayout(null);
        add(borderPanel);

        gamePanel.setBackground(ColorPallet.GRAY);
        gamePanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        borderPanel.add(gamePanel);

        infoPanel.setBackground(ColorPallet.GRAY);
        infoPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        borderPanel.add(infoPanel);


    }

    private void generateLayout(JPanel borderPanel, GamePanel gamePanel, InfoPanel infoPanel) {

        int borderSize = (int) (borderPanel.getWidth() * BORDER_FACTOR);

        generateInfoPanelLayout(borderPanel, infoPanel, borderSize);
        generateGamePanelLayout(borderPanel, infoPanel, gamePanel, borderSize);

    }

    private void generateInfoPanelLayout(JPanel borderPanel, InfoPanel infoPanel, int borderSize) {
        int x = borderPanel.getX() + borderSize;
        int y = borderPanel.getY() + borderSize;

        int width = borderPanel.getWidth() - 2 * borderSize;
        int height = (int) (borderPanel.getHeight() * INFO_PANEL_HEIGHT_FACTOR);

        infoPanel.setBounds(x, y, width, height);
    }

    private void generateGamePanelLayout(JPanel borderPanel, InfoPanel infoPanel, GamePanel gamePanel, int borderSize) {
        int x = borderPanel.getX() + borderSize;
        int y = infoPanel.getY() + infoPanel.getHeight() + borderSize;

        int width = borderPanel.getWidth() - 2 * borderSize;
        int height = borderPanel.getHeight() - infoPanel.getHeight() - 3 * borderSize;

        gamePanel.setBounds(x, y, width, height);
    }

    public GameState getGameState() {
        return gameState;
    }
}
