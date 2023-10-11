package minesweeper.gamelogic;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class GameState {
    private final int totalBombs;
    private final int fieldWidth;
    private final int fieldHeight;
    private final Tile[][] field;
    private int flagsPlaced;
    private boolean isFieldGenerated = false;
    private int tilesOpened = 0;
    private boolean gameLost = false;
    private boolean gameWon = false;
    private long startTime;
    private long endTime;

    /*
     * --------------------------
     *   CONSTRUCTOR
     * --------------------------
     */

    public GameState(int totalBombs, int fieldWidth, int fieldHeight) {
        this.totalBombs = totalBombs;
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        field = new Tile[fieldWidth][fieldHeight];
        for (int x = 0; x < fieldWidth; x++) {
            for (int y = 0; y < fieldHeight; y++) {
                field[x][y] = new Tile(x, y);
            }
        }
    }

    /*
     * --------------------------
     *   GETTER
     * --------------------------
     */

    public int getFlagsPlaced() {
        return flagsPlaced;
    }

    public int getTotalBombs() {
        return totalBombs;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public Tile[][] getField() {
        return field;
    }

    public boolean isFieldGenerated() {
        return isFieldGenerated;
    }

    public boolean isGameLost() {
        return gameLost;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    /*
     * --------------------------
     *   LEFT CLICK
     * --------------------------
     */

    public void leftClicked(int x, int y) {
        if (gameLost || gameWon)
            return;

        if (!isFieldGenerated) {
            generateField(x, y);
            isFieldGenerated = true;
            startTime = System.currentTimeMillis();
        }

        Tile tile = field[x][y];

        if (!tile.isClosed())
            return;

        recursiveReveal(x, y);


    }

    private void recursiveReveal(int x, int y) {
        Tile tile = field[x][y];

        if (tile.isFlagged())
            return;

        if(!openTileAndCount(tile)) // if the tile we opened was a bomb
            return;

        if (tile.getNumber() != 0)
            return;

        Tile[] surrounding = gatherSurroundingTiles(x, y);
        Arrays.stream(surrounding)
                .filter(Objects::nonNull)
                .filter(Tile::isClosed)
                .forEach(t -> recursiveReveal(t.getX(), t.getY()));
    }

    private void generateField(int x, int y) {
        placeBombs(x, y);
        createNumbers();
    }


    private void placeBombs(int x, int y) {
        Random random = new Random();

        int bombsPlaced = 0;

        while (bombsPlaced < totalBombs) {
            int randomX = random.nextInt(fieldWidth);
            int randomY = random.nextInt(fieldHeight);


            int differenceX = x - randomX;
            int differenceY = y - randomY;
            if (Math.abs(differenceX) <= 1 && Math.abs(differenceY) <= 1)
                continue;

            if (field[randomX][randomY].isBomb()) {
                continue;
            }

            field[randomX][randomY].setBomb(true);
            bombsPlaced++;
        }
    }

    private void createNumbers() {
        for (int x = 0; x < fieldWidth; x++) {
            for (int y = 0; y < fieldHeight; y++) {
                Tile tile = field[x][y];

                if (tile.isBomb())
                    continue;

                Tile[] tiles = gatherSurroundingTiles(x, y);
                int surroundingBombs = countSurroundingBombs(tiles);

                tile.setNumber(surroundingBombs);
            }
        }
    }


    /*
     * --------------------------
     *   RIGHT CLICK
     * --------------------------
     */

    public void rightClicked(int x, int y) {
        if (gameLost || gameWon)
            return;

        Tile tile = field[x][y];

        if (!tile.isClosed())
            return;

        if (tile.isFlagged()) {
            flagsPlaced--;
            tile.setFlagged(false);
        } else {
            flagsPlaced++;
            tile.setFlagged(true);
        }
    }

    /*
     * --------------------------
     *   SPACE
     * --------------------------
     */

    public void spacePressed(int x, int y) {
        rightClicked(x, y);

        Tile tile = field[x][y];

        if (tile.isClosed())
            return;

        Tile[] tiles = gatherSurroundingTiles(x, y);
        int surroundingFlags = countSurroundingFlags(tiles);

        if (surroundingFlags != tile.getNumber())
            return;

        Arrays.stream(tiles)
                .filter(Objects::nonNull)
                .filter(Tile::isClosed)
                .forEach(t -> recursiveReveal(t.getX(), t.getY()));
    }


    /*
     * --------------------------
     *   HELPERS
     * --------------------------
     */

    private Tile[] gatherSurroundingTiles(int x, int y) {
        int total = 0;
        Tile[] tiles = new Tile[8];

        for (int offsetX = -1; offsetX <= 1; offsetX++) {
            for (int offsetY = -1; offsetY <= 1; offsetY++) {
                int currentX = x + offsetX;
                int currentY = y + offsetY;

                if (currentX < 0 || currentX >= fieldWidth)
                    continue;

                if (currentY < 0 || currentY >= fieldHeight)
                    continue;

                if (offsetX == 0 && offsetY == 0)
                    continue;

                tiles[total] = field[currentX][currentY];
                total++;

            }
        }

        return tiles;
    }

    private int countSurroundingBombs(Tile[] tiles) {
        return (int) Arrays.stream(tiles).filter(Objects::nonNull).filter(Tile::isBomb).count();
    }

    private int countSurroundingFlags(Tile[] tiles) {
        return (int) Arrays.stream(tiles).filter(Objects::nonNull).filter(Tile::isFlagged).count();
    }

    private boolean openTileAndCount(Tile tile) {
        if (tile.isBomb()) {
            endTime = System.currentTimeMillis();
            gameLost = true;
            Arrays.stream(field).forEach(arr -> Arrays.stream(arr).filter(Tile::isBomb).forEach(Tile::open));
            return false;
        }

        tilesOpened++;
        tile.open();

        if(tilesOpened == fieldWidth * fieldHeight - totalBombs){
            endTime = System.currentTimeMillis();
            gameWon = true;
        }

        return true;
    }

}
