import java.awt.*;

public class Button
{
    private int x1, y1, x2, y2;

    private String label;

    private String state;

    private Game game;

    public Button(Game game, int x1, int y1, int x2, int y2, String label, String state)
    {
        this.game = game;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.label = label;
        this.state = state;
    }

    public void draw(Graphics g)
    {
        g.setColor(GameViewer.BUTTON_COLOR);
        g.fillRoundRect(x1, y1, x2-x1, y2-y1, 50, 50);

        String displayMessage = "AA" + label + "AA";
        int size = (int) ((x2 - x1) / displayMessage.length()) * 4;
        // Height differential = * 1.5
        System.out.println(getFontSize("Test", 250, g, 0));

        g.setColor(GameViewer.LIGHT_TEXT);
        g.setFont(GameViewer.FONT.deriveFont(Font.BOLD, size));

        g.drawString(displayMessage, x1, y2);
    }

    public int getFontSize(String text, int bounds, Graphics g, int size)
    {
        Font f = GameViewer.FONT.deriveFont(Font.BOLD, size);
        g.setFont(f);
        int width = g.getFontMetrics().stringWidth(text);
        if (width >= bounds)
        {
            return size;
        }

        getFontSize(text, bounds, g, size + 1);

        return 0;
    }

    public boolean mouseInBounds(int mouseX, int mouseY)
    {
        if (mouseX >= x1 && mouseX <= x2 && mouseY >= y1 && mouseY <= y2)
        {
            System.out.println(label);
            return true;
        }
        return false;
    }

    public String press()
    {
        return state;
    }
}
