import java.awt.*;
import java.util.Arrays;

public class Board {
    private Tile[][] board;
    public static final int BOARD_SIZE = 4;

    public Board()
    {
        board = new Tile[4][4];
        reset();
    }

    public void generate()
    {
        
    }
    public void reset()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            Arrays.fill(board[i], new Tile(0));
            board[0][2] = new Tile();
        }
    }

    public void combineHorizontal()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE - 1; j++)
            {
                board[i][j].combine(board[i][j+1]);
            }
        }

    }
    public void combineVertical()
    {
        for (int i = 0; i < BOARD_SIZE - 1; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                board[i][j].combine(board[i+1][j]);
            }
        }

    }
    public void moveLeft()
    {
        combineHorizontal();
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 1; j < BOARD_SIZE; j++)
            {
                if (board[i][j].getVal() != 0 && board[i][j-1].getVal() == 0)
                {
                    board[i][j-1].setVal(4);
                    board[i][j].setVal(0);
                    //j--;
                }
            }
            }
    }

    public void moveRight()
    {
        combineHorizontal();
    }
    public void moveUp()
    {

    }
    public void moveDown()
    {

    }

    public void draw (Graphics g)
    {
        g.setColor(Color.ORANGE);
        g.fillRect(0,0, GameViewer.SCREEN_SIZE, GameViewer.SCREEN_SIZE);

        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                board[i][j].draw(g, j, i);
            }

        }

    }

    public void save ()
    {

    }

    public void load()
    {

    }
}
