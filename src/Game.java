import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    private Board board;

    private Button[] buttons;
    private int score;
    private GameViewer window;

    public Game()
    {
        /*
        Height * 18 / 20 / 5 = boxHeight

        borderheight +  boxHeight / 10
        Height - boxHeight / 10


         */
        int boxHeight = GameViewer.SCREEN_HEIGHT * 18 / 20 / 5;
        int x1 = GameViewer.SCREEN_HEIGHT / 20;
        int x2 = GameViewer.SCREEN_HEIGHT / 2;
                //18 / 20 / 5;
        buttons = new Button[5];
        for (int yShift = 0; yShift < 5; yShift++)
        {
            int y1 = GameViewer.BORDER_HEIGHT + GameViewer.SCREEN_HEIGHT / 20 + (boxHeight * yShift * 9 / 10);
            buttons[yShift] = new Button(this,
                            x1,
                            y1,
                            x2,
                            y1 + (boxHeight * (yShift + 1) * 9 / 10),
                            "Test", "INSTRUCTIONS");
//                    new Button(this, GameViewer.SCREEN_HEIGHT / 20, 350 + 16, GameViewer.SCREEN_HEIGHT / 2, 510 - 16, "Reset", "RESET"),
//                    new Button(this, GameViewer.SCREEN_HEIGHT / 20, 510 + 16, GameViewer.SCREEN_HEIGHT / 2, 670 - 16, "Save", "SELECT_SAVE"),
//                    new Button(this, GameViewer.SCREEN_HEIGHT / 20, 670 + 16, GameViewer.SCREEN_HEIGHT / 2, 830 - 16, "Load", "SELECT_LOAD")
    
        }
        board = new Board();
        window = new GameViewer(this);
        score = 0;
    }

    public Board getBoard()
    {
        return board;
    }

    public Button[] getButton()
    {
        return buttons;
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
