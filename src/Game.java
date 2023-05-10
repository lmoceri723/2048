import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {
    private Board board;
    private Button[] buttons;
    private GameViewer window;

    private String state;

    public Game()
    {
        createButtons();
        board = new Board();
        window = new GameViewer(this);
        state = "INSTRUCTIONS";
    }

    public void createButtons()
    {
        int boxHeight = GameViewer.SCREEN_HEIGHT * 9 / 50;
        int x1 = GameViewer.SCREEN_HEIGHT / 20;
        int x2 = GameViewer.SCREEN_HEIGHT / 2;
        buttons = new Button[4];
        for (int yShift = 1; yShift < 5; yShift++)
        {
            int y1 = GameViewer.BORDER_HEIGHT + GameViewer.SCREEN_HEIGHT / 20 + boxHeight * yShift + boxHeight / 10;
            buttons[yShift - 1] = new Button(this, x1, y1, x2, y1 + boxHeight - boxHeight / 10,
                    GameViewer.BUTTON_LABELS[yShift-1], GameViewer.BUTTON_STATES[yShift-1]);
        }
    }

    public Board getBoard()
    {
        return board;
    }

    public Button[] getButton()
    {
        return buttons;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
