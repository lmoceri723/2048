// By Landon Moceri on 5/12
import java.awt.*;

public class Tile {
    // Instance variables
    public static final double FONT_CONSTANT = 1.65;
    private int val;
    private boolean newlyCombined;

    // Constructor for Tile
    public Tile()
    {
        // Initializes its value with a 90% chance of being 2 and a 10% chance of being 4
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

    // Constructor to create a tile with a specific value
    public Tile(int val)
    {
        this.val = val;
        newlyCombined = false;
    }

    // Attempts to combine 2 tiles
    public boolean combine(Tile other)
    {
        // Checks if the tiles are combinable and if they have already been made in a movement
        if (this.val != 0 && this.val == other.val && !this.newlyCombined && !other.newlyCombined)
        {
            // Combines the two tiles and returns true
            this.val *= 2;
            this.newlyCombined = true;
            other.val = 0;
            return true;
        }
        // Returns false otherwise
        return false;
    }

    // Getters and setters
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

    // Gets the x and y coordinates of a tile relative to its position on the board
    public int[] getCoordinates(int row, int col)
    {
        int x = GameViewer.SCREEN_WIDTH / 3 + GameViewer.SCREEN_HEIGHT / 20 + GameViewer.SCREEN_HEIGHT / 50 +
                row * (GameViewer.SCREEN_HEIGHT / 5 + GameViewer.SCREEN_HEIGHT / 50);
        int y = GameViewer.BORDER_HEIGHT + GameViewer.SCREEN_HEIGHT / 20+ GameViewer.SCREEN_HEIGHT / 50 +
                col * (GameViewer.SCREEN_HEIGHT / 5 + GameViewer.SCREEN_HEIGHT / 50);

        return new int[]{x, y};
    }

    // Draws the tile
    public void draw(Graphics g, int row, int col)
    {
        // Uses log base 2 to get its position on a corresponding array of colors
        int index;
        if (val == 0)
        {
            index = 0;
        }
        else
        {
            index = (int) (Math.log(val) / Math.log(2));
        }

        // Gets the coordinates using the helper method
        int[] coordinates = getCoordinates(row, col);
        int x = coordinates[0];
        int y = coordinates[1];

        // Draws the box for the square
        g.setColor(GameViewer.SQUARE_COLORS[index]);
        g.fillRect(x, y, GameViewer.SCREEN_HEIGHT / 5, GameViewer.SCREEN_HEIGHT / 5);

        // Sets the text color to one that will best match the square
        switch (val) {
            case 0 -> {
                return;
            }
            case 2, 4 -> g.setColor(GameViewer.DARK_TEXT);
            default -> g.setColor(GameViewer.LIGHT_TEXT);
        }

        // Gets the font size needed for the text to fit and draws the number in the box
        String displayMessage = " " + val + " ";
        int size = (int) ((GameViewer.SCREEN_HEIGHT / 5) / displayMessage.length() * FONT_CONSTANT);

        Font newFont = GameViewer.FONT.deriveFont(Font.BOLD, size);
        g.setFont(newFont);

        g.drawString(displayMessage, x + (GameViewer.SCREEN_HEIGHT / 5 - g.getFontMetrics().stringWidth(displayMessage)) / 2, y + GameViewer.SCREEN_HEIGHT / 10 + g.getFontMetrics().getHeight() / 4);
    }
}
