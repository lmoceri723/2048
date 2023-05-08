import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class GameViewer extends JFrame implements KeyListener
{
    public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()
            - ((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() % 200));
    public static final int SCREEN_WIDTH = (SCREEN_HEIGHT * 3) / 2;

    public static final int BORDER_HEIGHT = 30;

    public static final File FONT_FILE = new File("rsc/Font.ttf");

    public static Font FONT;

    private Game game;

    public GameViewer(Game game) {
        this.game = game;
        try {
            FONT = Font.createFont(Font.TRUETYPE_FONT, GameViewer.FONT_FILE);
            FONT = FONT.deriveFont(Font.PLAIN, 100);
        } catch (FontFormatException | IOException e) {
            FONT = new Font(Font.SERIF, Font.PLAIN,  10);
            throw new RuntimeException(e);
        }

        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("2048!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(this);

        setVisible(true);
    }

    public void paint(Graphics g)
    {
        drawBackground(g);
        game.getBoard().draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void drawBackground(Graphics g)
    {
        g.setColor(new Color(237, 228, 219));
        g.fillRect(0,BORDER_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (game.getBoard().gameNotOver()) {
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_W) {
                game.getBoard().moveUp();
            }
            if (keyCode == KeyEvent.VK_A) {
                game.getBoard().moveLeft();
            }
            if (keyCode == KeyEvent.VK_S) {
                game.getBoard().moveDown();
            }
            if (keyCode == KeyEvent.VK_D) {
                game.getBoard().moveRight();
            }
            if (keyCode == KeyEvent.VK_R) {
                game.getBoard().reset();
            }
            if (keyCode == KeyEvent.VK_L) {
                game.getBoard().load();
            }
            if (keyCode == KeyEvent.VK_K) {
                try {
                    game.getBoard().save();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
