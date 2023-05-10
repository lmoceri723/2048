import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class GameViewer extends JFrame implements KeyListener, MouseListener
{
    public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 30
            - ((int) (((Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - 30) % 200));
    public static final int SCREEN_WIDTH = SCREEN_HEIGHT * 3 / 2;

    public static final int BORDER_HEIGHT = 30;

    public static final File FONT_FILE = new File("rsc/Font.ttf");

    public static Font FONT;

    public static final Color BORDER_COLOR = new Color(185, 173, 162);

    public static final Color DARK_TEXT = new Color (118, 110, 102);

    public static final Color LIGHT_TEXT = new Color(249, 246, 242);
    public static final Color[] SQUARE_COLORS = {
            new Color(203, 193, 181),
            new Color(237, 228, 219),
            new Color(236, 225, 204),
            new Color(235, 179, 131),
            new Color(234, 153, 109),
            new Color(233, 128, 103),
            new Color(231, 102, 72),
            new Color(233, 208, 128),
            new Color(233, 204, 112),
            new Color(231, 200, 100),
            new Color(232, 198, 89),
            new Color(231, 194, 80),
            new Color(112, 145, 222),
            new Color(133, 180, 237),
            new Color(114, 207, 219),
            new Color(133, 237, 180),
            new Color(118, 232, 137),
            new Color(94, 181, 100),
        };

    private Game game;

    public GameViewer(Game game) {
        this.game = game;
        try {
            FONT = Font.createFont(Font.TRUETYPE_FONT, GameViewer.FONT_FILE);
            FONT = FONT.deriveFont(Font.BOLD, 100);
        } catch (FontFormatException | IOException e) {
            FONT = new Font(Font.SERIF, Font.PLAIN,  10);
            throw new RuntimeException(e);
        }

        setSize(SCREEN_WIDTH, SCREEN_HEIGHT + 30);
        setLocationRelativeTo(null);
        setTitle("2048!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(this);
        addMouseListener(this);

        setVisible(true);
    }

    public void paint(Graphics g)
    {
        drawBackground(g);
        game.getBoard().draw(g);

        for (Button b : game.getButton())
        {
            b.draw(g);
        }

//        g.setColor(new Color(0, 0, 0, 140));
//        g.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT + BORDER_HEIGHT);
        Toolkit.getDefaultToolkit().sync();
    }

    public void drawBackground(Graphics g)
    {
        g.setColor(new Color(237, 228, 219));
        g.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT + BORDER_HEIGHT);
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
                System.out.println(SCREEN_HEIGHT);
                System.out.println(SCREEN_WIDTH);
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

    @Override
    public void mouseClicked(MouseEvent e)
    {
        for (Button b : game.getButton())
        {
            b.mouseInBounds(e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
