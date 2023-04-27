import java.awt.*;

public class Tile {
    private int val;

    public Tile()
    {
        int chance = (int) (Math.random() * 4);
        if (chance == 0)
        {
            val = 4;
        }
        else
        {
            val = 2;
        }
    }
    public Tile(int val)
    {
        this.val = val;
    }


    public void combine(Tile other)
    {
        if (this.val != 0 && this.val == other.val)
        {
            this.val *= 2;
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

    public void draw(Graphics g, int row, int col)
    {
        g.setFont(new Font(Font.SERIF, Font.PLAIN,  100));
        g.setColor(new Color(118, 110, 102));
        g.drawString(Integer.toString(val), row * 200 + 50, col * 200 + 100);
    }
}
