import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tile {
    private int val;
    private boolean newlyCombined;


    public Tile()
    {
        int chance = (int) (Math.random() * 10);
        if (chance == 0)
        {
            val = 4;
        }
        else
        {
            val = 2;
        }
        newlyCombined = false;
    }
    public Tile(int val)
    {
        this.val = val;
        newlyCombined = false;
    }


    public void combine(Tile other)
    {
        if (this.val != 0 && this.val == other.val && !this.newlyCombined && !other.newlyCombined)
        {
            this.val *= 2;
            this.newlyCombined = true;
            other.val = 0;
        }
    }

    public int getVal()
    {
        return val;
    }

    public void setVal(int val)
    {
        this.val = val;
    }

    public void setNewlyCombined(boolean newlyCombined)
    {
        this.newlyCombined = newlyCombined;
    }

    public int[] getCoordinates(int row, int col)
    {
        int x = GameViewer.SCREEN_WIDTH / 3 + GameViewer.SCREEN_HEIGHT / 20 + GameViewer.SCREEN_HEIGHT / 50 +
                row * (GameViewer.SCREEN_HEIGHT / 5 + GameViewer.SCREEN_HEIGHT / 50);
        int y = GameViewer.BORDER_HEIGHT + GameViewer.SCREEN_HEIGHT / 20+ GameViewer.SCREEN_HEIGHT / 50 +
                col * (GameViewer.SCREEN_HEIGHT / 5 + GameViewer.SCREEN_HEIGHT / 50);

        return new int[]{x, y};
    }

    public void draw(Graphics g, int row, int col)
    {
        int index;
        if (val == 0)
        {
            index = 0;
        }
        else
        {
            index = (int) (Math.log(val) / Math.log(2));
        }

        int[] coordinates = getCoordinates(row, col);
        int x = coordinates[0];
        int y = coordinates[1];

        g.setColor(GameViewer.SQUARE_COLORS[index]);
        g.fillRect(x, y, GameViewer.SCREEN_HEIGHT / 5, GameViewer.SCREEN_HEIGHT / 5);

        switch (val) {
            case 0 -> {
                return;
            }
            case 2, 4 -> g.setColor(GameViewer.DARK_TEXT);
            default -> g.setColor(GameViewer.LIGHT_TEXT);
        }

        // derive the font size
        Font newFont = GameViewer.FONT.deriveFont(Font.PLAIN, 75);
        g.setFont(newFont);

        g.drawString(Integer.toString(val), x + 50, y + 100);
    }
}
