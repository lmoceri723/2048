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
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Tile(0);
            }
        }
        board[1][1].setVal(2);
        board[1][2].setVal(4);
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
            for (int j = BOARD_SIZE - 1; j > 0; j--)
            {
                while (board[i][j].getVal() != 0 && board[i][j-1].getVal() == 0)
                {
                    board[i][j-1].setVal(board[i][j].getVal());
                    board[i][j].setVal(0);
                }
            }
        }
    }

    public void moveRight()
    {
        combineHorizontal();
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE - 1; j++)
            {
                while (board[i][j].getVal() != 0 && board[i][j+1].getVal() == 0)
                {
                    board[i][j+1].setVal(board[i][j].getVal());
                    board[i][j].setVal(0);
                }
            }
        }
    }
    public void moveUp()
    {
        combineVertical();
        for (int i = BOARD_SIZE - 1; i > 0; i--)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                while (board[i][j].getVal() != 0 && board[i-1][j].getVal() == 0)
                {
                    board[i-1][j].setVal(board[i][j].getVal());
                    board[i][j].setVal(0);
                }
            }
        }

    }
    public void moveDown()
    {
        combineVertical();
        for (int i = 0; i < BOARD_SIZE - 1; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                while (board[i][j].getVal() != 0 && board[i+1][j].getVal() == 0)
                {
                    board[i+1][j].setVal(board[i][j].getVal());
                    board[i][j].setVal(0);
                    //j--;
                }
            }
        }
    }

    public void draw (Graphics g)
    {
        g.setColor(new Color(203, 193, 181));
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
