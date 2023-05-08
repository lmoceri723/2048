import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    private Board board;
    private int score;
    private GameViewer window;

    public Game()
    {
        board = new Board();
        window = new GameViewer(this);
        score = 0;
    }

    public Board getBoard()
    {
        return board;
    }
    public void playGame()
    {
        window.repaint();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
