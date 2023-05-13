// By Landon Moceri on 5/12
import java.awt.*;
import java.io.IOException;

public class Button
{
    // Instance variables
    private final int x1, y1, x2, y2;

    private final String label;

    private final Game game;

    // Constructor for Button
    public Button(Game game, int x1, int y1, int x2, int y2, String label)
    {
        this.game = game;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.label = label;
    }

    // Draws the button
    public void draw(Graphics g)
    {
        // Draws the shape of the button
        g.setColor(GameViewer.BUTTON_COLOR);
        g.fillRoundRect(x1, y1, x2-x1, y2-y1, 50, 50);

        // Draws the label in the center of the button
        String displayMessage = "      " + label + "      ";

        g.setColor(GameViewer.LIGHT_TEXT);
        g.setFont(GameViewer.FONT.deriveFont(Font.BOLD, getFontSize(displayMessage, x2-x1, g, 0)));

        g.drawString(displayMessage, x1, y2 - (y2-y1) / 2 + g.getFontMetrics().getHeight() / 4);
    }

    // Recursively gets the required font size to fill a space with bounds pixels using String text
    public static int getFontSize(String text, int bounds, Graphics g, int size)
    {
        // Derives the font according to the specified size
        g.setFont(GameViewer.FONT.deriveFont(Font.BOLD, size));
        // Checks if the font size does not fit inside the box
        if (g.getFontMetrics().stringWidth(text) < bounds)
        {
            // Runs the function again incrementing size
            return getFontSize(text, bounds, g, size + 1);
        }
        // Returns the size if it fits
        return size;
    }

    // Checks if the mouse is in the bounds of the button
    public boolean mouseInBounds(int mouseX, int mouseY)
    {
        return mouseX >= x1 && mouseX <= x2 && mouseY >= y1 && mouseY <= y2;
    }

    // Tries to press the button
    public void press(int mouseX, int mouseY)
    {
        // Checks if the mouse is on the button
        if (mouseInBounds(mouseX, mouseY))
        {
            // Saves the game if save is pressed
            if (label.equals("Save"))
            {
                try {
                    game.getBoard().save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            // Loads the game if load is pressed
            if (label.equals("Load"))
            {
                game.getBoard().load();
            }
            // Resets the game if reset is pressed
            if (label.equals("Reset"))
            {
                game.getBoard().reset();
            }

            // Opens the instructions screen if how to play is pressed
            if (label.equals("How To Play"))
            {
                game.setState("How To Play");
            }
        }

    }
}
