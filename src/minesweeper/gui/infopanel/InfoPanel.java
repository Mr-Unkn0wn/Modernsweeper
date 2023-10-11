package minesweeper.gui.infopanel;

import minesweeper.gamelogic.GameState;
import minesweeper.gui.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static minesweeper.gui.infopanel.TopBarPanel.TOP_BAR_HEIGHT;

class InfoPanel extends JPanel {

    private final TopBarPanel topBarPanel;

    private final JLabel time;
    private final JLabel bombs;
    private final Timer scheduler;
    private boolean isClockRunning = false;

    public InfoPanel(TopBarPanel topBarPanel) {
        this.topBarPanel = topBarPanel;

        setBackground(Constants.TOP_BAR_GRAY);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        scheduler = new Timer();

        float fontSize = 25f;

        time = new JLabel();
        time.setFont(Constants.normal.deriveFont(fontSize));
        time.setIcon(new ImageIcon("img/clock.png"));
        time.setText(timeToString(0));
        time.setForeground(Constants.TOP_BAR_TEXT);
        time.setPreferredSize(new Dimension(200, TOP_BAR_HEIGHT));
        time.setHorizontalAlignment(SwingConstants.RIGHT);
        time.setHorizontalTextPosition(SwingConstants.LEFT);
        add(time);


        bombs = new JLabel();
        bombs.setFont(Constants.normal.deriveFont(fontSize));
        bombs.setIcon(new ImageIcon("img/bomb.png"));
        bombs.setText(String.valueOf(topBarPanel.frame.getGameState().getTotalBombs()));
        bombs.setForeground(Constants.TOP_BAR_TEXT);
        bombs.setPreferredSize(new Dimension(200, TOP_BAR_HEIGHT));
        bombs.setHorizontalAlignment(SwingConstants.LEFT);
        add(bombs);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        GameState game = topBarPanel.frame.getGameState();

        bombs.setText(String.valueOf(game.getTotalBombs() - game.getFlagsPlaced()));

        if (game.isFieldGenerated() && !isClockRunning) {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    updateClock();
                }
            };

            scheduler.scheduleAtFixedRate(timerTask, 0L, 200L);
            isClockRunning = true;
        }
    }

    private void updateClock() {
        GameState game = topBarPanel.frame.getGameState();

        long startTime = game.getStartTime();

        if (game.isGameWon() || game.isGameLost()) {
            scheduler.cancel();
            isClockRunning = false;

            long timePassed = game.getEndTime() - startTime;

            time.setText(timeToString(timePassed));
        }

        long timePassed = System.currentTimeMillis() - startTime;


        time.setText(timeToString(timePassed));
    }

    private String timeToString(long timeInMs) {
        long minutes = timeInMs / 60000; // 1 minute = 60,000 milliseconds
        long seconds = (timeInMs % 60000) / 1000; // 1 second = 1,000 milliseconds

        if(minutes > 59){ // if you need longer then one hour you don't deserve a timer
            minutes = 59;
            seconds = 59;
        }

        String minutesString = "";
        if(minutes > 0)
            minutesString = minutes + "M";

        String secondsString = seconds + "S";

        return minutesString + secondsString;
    }
}
