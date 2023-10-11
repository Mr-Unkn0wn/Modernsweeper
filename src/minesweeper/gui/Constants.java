package minesweeper.gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Constants {

    public final static Color BORDER_GRAY = new Color(81, 81, 81);
    public final static Color TOP_BAR_GRAY = new Color(59, 63, 66);
    public final static Color TOP_BAR_TEXT = new Color(130, 130, 130);
    public final static Color DARK_GRAY = new Color(25, 25, 25);

    // public final static Font bold;
    public final static Font normal;

    static {
        try {
            normal = Font.createFont(Font.TRUETYPE_FONT, new File("font/Montserrat-Regular.ttf"));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
