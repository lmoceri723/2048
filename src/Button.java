import java.awt.*;
import java.io.IOException;

public class Button
{
    private int x1, y1, x2, y2;

    private String label;

    private Game game;

    public Button(Game game, int x1, int y1, int x2, int y2, String label)
    {
        this.game = game;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.label = label;
    }

    public void draw(Graphics g)
    {
        g.setColor(GameViewer.BUTTON_COLOR);
        g.fillRoundRect(x1, y1, x2-x1, y2-y1, 50, 50);

        String displayMessage = "      " + label + "      ";

        g.setColor(GameViewer.LIGHT_TEXT);
        g.setFont(GameViewer.FONT.deriveFont(Font.BOLD, getFontSize(displayMessage, x2-x1, g, 0)));

        g.drawString(displayMessage, x1, y2 - (y2-y1) / 2 + g.getFontMetrics().getHeight() / 4);
    }

    public static int getFontSize(String text, int bounds, Graphics g, int size)
    {
        g.setFont(GameViewer.FONT.deriveFont(Font.BOLD, size));
        if (g.getFontMetrics().stringWidth(text) < bounds)
        {
            return getFontSize(text, bounds, g, size + 1);
        }

        return size;
    }

    public boolean mouseInBounds(int mouseX, int mouseY)
    {
        if (mouseX >= x1 && mouseX <= x2 && mouseY >= y1 && mouseY <= y2)
        {
            return true;
        }
        return false;
    }

    public String press(int mouseX, int mouseY)
    {
        if (mouseInBounds(mouseX, mouseY))
        {
            if (label.equals("Save"))
            {
                try {
                    game.getBoard().save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (label.equals("Load"))
            {
                game.getBoard().load();
            }
            if (label.equals("Reset"))
            {
                game.getBoard().reset();
            }

            if (label.equals("How To Play"))
            {
                game.setState("How To Play");
            }
        }

        return null;
    }
}
