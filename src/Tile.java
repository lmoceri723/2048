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

    public void draw(Graphics g, int row, int col)
    {
        g.setFont(GameViewer.FONT);
        g.setColor(new Color(118, 110, 102));
        if (val != 0)
        {
            g.drawString(Integer.toString(val), row * 200 + 50, col * 200 + 100);
        }
    }
}
