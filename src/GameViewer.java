import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameViewer extends JFrame implements KeyListener
{
    public static final int SCREEN_SIZE = 800;

    private Game game;

    public GameViewer(Game game)
    {
        this.game = game;

        setSize(SCREEN_SIZE, SCREEN_SIZE);
        setLocationRelativeTo(null);
        setTitle("2048!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(this);

        setVisible(true);
    }

    public void paint(Graphics g)
    {
        game.getBoard().draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_A)
        {
            System.out.printf("A was pressed!");
            game.getBoard().moveLeft();
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
