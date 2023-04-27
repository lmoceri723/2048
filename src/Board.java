import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
        ArrayList<Integer[]> spaces = getEmptySpaces();

        if (!spaces.isEmpty())
        {
            int index = (int) (Math.random() * spaces.size());
            board[spaces.get(index)[0]][spaces.get(index)[1]] = new Tile();
        }

    }

    public ArrayList<Integer[]> getEmptySpaces()
    {
        ArrayList<Integer[]> spaces = new ArrayList<Integer[]>();
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                if (board[i][j].getVal() == 0)
                {
                    spaces.add(new Integer[]{i, j});
                }
            }
        }
        return spaces;
    }
    public void reset()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Tile(0);
            }
        }

        generate();
    }

    public void moveLeft()
    {
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 1; col < BOARD_SIZE;)
            {
                if (col > 0)
                {
                    board[row][col].combine(board[row][col-1]);
                }
                if (col > 0 && board[row][col].getVal() != 0 && board[row][col-1].getVal() == 0)
                {
                    board[row][col-1].setVal(board[row][col].getVal());
                    board[row][col].setVal(0);
                    col--;
                }
                else
                {
                    col++;
                }
            }
        }
    }

    public void moveRight()
    {
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = BOARD_SIZE; col >= 0;)
            {
                if (col < BOARD_SIZE - 1)
                {
                    board[row][col].combine(board[row][col+1]);
                }

                if (col < BOARD_SIZE - 1 && board[row][col].getVal() != 0 && board[row][col+1].getVal() == 0)
                {
                    board[row][col+1].setVal(board[row][col].getVal());
                    board[row][col].setVal(0);
                    col++;
                }
                else
                {
                    col--;
                }
            }
        }
    }
    public void moveUp()
    {
        for (int col = 0; col < BOARD_SIZE; col++)
        {
            for (int row = 1; row < BOARD_SIZE;)
            {
                if (row > 0)
                {
                    board[row][col].combine(board[row-1][col]);
                }
                if (row > 0 && board[row][col].getVal() != 0 && board[row-1][col].getVal() == 0)
                {
                    board[row-1][col].setVal(board[row][col].getVal());
                    board[row][col].setVal(0);
                    row--;
                }
                else
                {
                    row++;
                }
            }
        }
    }
    public void moveDown()
    {
        for (int col = 0; col < BOARD_SIZE; col++)
        {
            for (int row = BOARD_SIZE - 1; row >= 0;)
            {
                if (row < BOARD_SIZE - 1)
                {
                    board[row + 1][col].combine(board[row][col]);
                }

                if (row < BOARD_SIZE - 1 && board[row][col].getVal() != 0 && board[row+1][col].getVal() == 0)
                {
                    board[row+1][col].setVal(board[row][col].getVal());
                    board[row][col].setVal(0);
                    row++;
                }
                else
                {
                    row--;
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
