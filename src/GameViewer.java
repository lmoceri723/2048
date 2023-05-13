import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class GameViewer extends JFrame implements KeyListener, MouseListener
{
    // Static and instance variables
    /* Gets the screen height, which is the highest multiple of 200 pixels
       Inclusive of the 30 pixel border able to fit vertically on the screen */
    public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 30
            - ((int) (((Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - 30) % 200));
    public static final int SCREEN_WIDTH = SCREEN_HEIGHT * 3 / 2;

    public static final int BORDER_HEIGHT = 30;

    public static final File FONT_FILE = new File("rsc/Font.ttf");

    public static Font FONT;

    public static final Color BG_COLOR = new Color(237, 228, 219);

    public static final Color BORDER_COLOR = new Color(185, 173, 162);

    public static final Color BUTTON_COLOR = new Color(140, 122,105);

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

    public static final String[] BUTTON_LABELS = {
            "How To Play",
            "Reset",
            "Save",
            "Load"
    };

    public static final String[] INSTRUCTIONS = {
            "   How To Play   ",
            "   Use your WASD keys to move the tiles   ",
            "   Tiles with the same number merge into one when they touch      ",
            "   Add them up to reach 2048   ",
            "   Click anywhere with the mouse to start   "
    };

    private final Game game;

    // Constructor for the viewer, initializes instance variables
    public GameViewer(Game game) {
        this.game = game;
        // Tries to initialize the font
        try {
            FONT = Font.createFont(Font.TRUETYPE_FONT, GameViewer.FONT_FILE);
            FONT = FONT.deriveFont(Font.BOLD, 100);
        } catch (FontFormatException | IOException e) {
            FONT = new Font(Font.SERIF, Font.PLAIN,  10);
            throw new RuntimeException(e);
        }

        // Sets the screen size, title, and key and mouse listeners
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT + 30);
        setLocationRelativeTo(null);
        setTitle("2048!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(this);
        addMouseListener(this);

        setVisible(true);
    }

    // Draws the game
    public void paint(Graphics g)
    {
        // Draws the background, board, and buttons
        drawBackground(g);
        game.getBoard().draw(g);

        for (Button b : game.getButton())
        {
            b.draw(g);
        }

        // Draws the instructions screen over the game
        if (game.getState().equals("How To Play"))
        {
            drawInstructions(g);
        }

        // Syncs to reduce stuttering
        Toolkit.getDefaultToolkit().sync();
    }

    // Draws the instruction screen
    public void drawInstructions(Graphics g)
    {
        // Draws an opaque layer over the board
        g.setColor(new Color(0, 0, 0, 140));
        g.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT + BORDER_HEIGHT);

        // Draws the box in which the instructions are encased
        g.setColor(BG_COLOR);
        g.fillRoundRect(SCREEN_WIDTH / 5, BORDER_HEIGHT + SCREEN_HEIGHT / 5,
                SCREEN_WIDTH * 3 / 5, SCREEN_HEIGHT * 3 / 5, 50, 50);

        // Draws the "How To Play" label
        g.setColor(DARK_TEXT);
        g.setFont(FONT.deriveFont(Font.BOLD, Button.getFontSize(INSTRUCTIONS[0],
                SCREEN_WIDTH * 3 / 5, g, 0)));
        g.drawString(INSTRUCTIONS[0], SCREEN_WIDTH / 5,
                BORDER_HEIGHT + SCREEN_HEIGHT / 5 + (int) (g.getFontMetrics().getHeight() / 1.2));

        // Draws the instructions with the font size of the largest line
        g.setFont(FONT.deriveFont(Font.BOLD, Button.getFontSize(INSTRUCTIONS[2],
                SCREEN_WIDTH * 3 / 5, g, 0)));
        for (int line = 1; line < 5; line++)
        {
            g.drawString(INSTRUCTIONS[line], SCREEN_WIDTH / 5,
                    BORDER_HEIGHT + SCREEN_HEIGHT * 9 / 20 + g.getFontMetrics().getHeight() * 2 * (line - 1));
        }

    }

    // Draws the background and logo
    public void drawBackground(Graphics g)
    {
        // Draws the background
        g.setColor(BG_COLOR);
        g.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT + BORDER_HEIGHT);

        // Draws the 2048 logo
        int size = SCREEN_HEIGHT / 8;
        g.setFont(FONT.deriveFont(Font.PLAIN, size));
        g.setColor(DARK_TEXT);
        g.drawString("2048", SCREEN_HEIGHT / 20,
                BORDER_HEIGHT + SCREEN_HEIGHT / 20 + g.getFontMetrics().getHeight() / 2);
    }

    // Runs when a key is pressed and processes the user input
    @Override
    public void keyPressed(KeyEvent e) {
        // Gets the key the user entered
        int keyCode = e.getKeyCode();

        // Allows instructions and resetting to be entered as long as the user is in a game
        if (game.getState().equals("GAME"))
        {
            // Resets the board
            if (keyCode == KeyEvent.VK_R)
            {
                game.getBoard().reset();
            }
            // Opens the instructions
            if (keyCode == KeyEvent.VK_SPACE)
            {
                game.setState("How To Play");
                repaint();
            }

            // Checks if the game is ongoing
            if (game.getBoard().gameNotOver())
            {
                // Moves the board in whatever direction the user specifies by calling helper functions
                if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
                    game.getBoard().moveUp();
                }
                if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
                    game.getBoard().moveLeft();
                }
                if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
                    game.getBoard().moveDown();
                }
                if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
                    game.getBoard().moveRight();
                }
                repaint();
            }
        }
        // Allows the user to exit the instructions screen by pressing space
        else if (game.getState().equals("How To Play"))
        {
            if (keyCode == KeyEvent.VK_SPACE)
            {
                game.setState("GAME");
                repaint();
            }
        }
    }

    // Runs when the mouse is clicked to handle mouse input
    @Override
    public void mouseClicked(MouseEvent e)
    {
        // Runs press on each button at the mouse coordinates,
        // Which checks if the coordinates are inside the button and executes its action
        if (game.getState().equals("GAME"))
        {
            for (Button b : game.getButton())
            {
                b.press(e.getX(), e.getY());
            }
            repaint();
        }

        // Exits the instructions screen if the mouse is pressed
        else if (game.getState().equals("How To Play"))
        {
            game.setState("GAME");
            repaint();
        }
    }

    // Required functions for Mouse and Keyboard Listeners

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

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
