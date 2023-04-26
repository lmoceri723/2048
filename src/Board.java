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
        board[0][3].setVal(2);
        board[0][2].setVal(2);
        board[1][2].setVal(2);
        board[2][1].setVal(2);
        board[3][0].setVal(2);
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
                while (board[i][j].getVal() != 0 && board[i][j-1].getVal() == 0)
                {
                    board[i][j-1].setVal(board[i][j].getVal());
                    board[i][j].setVal(0);
                    //j--;
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
                    //j--;
                }
            }
        }
    }
    public void moveUp()
    {
        combineVertical();
        for (int i = 1; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                while (board[i][j].getVal() != 0 && board[i-1][j].getVal() == 0)
                {
                    board[i-1][j].setVal(board[i][j].getVal());
                    board[i][j].setVal(0);
                    //j--;
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
