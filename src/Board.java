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

    public void combineLeft()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE - 1; j++)
            {
                board[i][j].combine(board[i][j+1]);
            }
        }
    }
    public void combineRight()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = BOARD_SIZE - 1; j > 0; j--)
            {
                board[i][j].combine(board[i][j-1]);
            }
        }
    }
    public void combineUp()
    {
        for (int i = 0; i < BOARD_SIZE - 1; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                board[i][j].combine(board[i+1][j]);
            }
        }
    }

    public void combineDown()
    {
        for (int i = BOARD_SIZE - 1; i > 0; i--)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                board[i][j].combine(board[i-1][j]);
            }
        }
    }
    public void moveLeft()
    {
        combineLeft();
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 1; j < BOARD_SIZE; j+=0)
            {
                if (j > 0 && board[i][j].getVal() != 0 && board[i][j-1].getVal() == 0)
                {
                    board[i][j-1].setVal(board[i][j].getVal());
                    board[i][j].setVal(0);
                    j--;
                }
                else
                {
                    j++;
                }
            }
        }
    }

    public void moveRight()
    {
        combineRight();
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = BOARD_SIZE; j >= 0; j+=0)
            {
                if (j < BOARD_SIZE - 1 && board[i][j].getVal() != 0 && board[i][j+1].getVal() == 0)
                {
                    board[i][j+1].setVal(board[i][j].getVal());
                    board[i][j].setVal(0);
                    j++;
                }
                else
                {
                    j--;
                }
            }
        }
    }
    public void moveUp()
    {
        combineUp();
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 1; j < BOARD_SIZE; j+=0)
            {
                if (j > 0 && board[j][i].getVal() != 0 && board[j-1][i].getVal() == 0)
                {
                    board[j-1][i].setVal(board[j][i].getVal());
                    board[j][i].setVal(0);
                    j--;
                }
                else
                {
                    j++;
                }
            }
        }
    }
    public void moveDown()
    {
        combineDown();
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = BOARD_SIZE - 1; j >= 0; j+=0)
            {
                if (j < BOARD_SIZE - 1 && board[j][i].getVal() != 0 && board[j+1][i].getVal() == 0)
                {
                    board[j+1][i].setVal(board[j][i].getVal());
                    board[j][i].setVal(0);
                    j++;
                }
                else
                {
                    j--;
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
