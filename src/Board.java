import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private Tile[][] board;
    public static final int BOARD_SIZE = 4;
    public static final int BOARD_DIMENSIONS = 600;

    public Board()
    {
        board = new Tile[4][4];
        reset();
        generate();
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

    public boolean gameNotOver()
    {
        // Check if board is the same after being changed moved in each direction
        Board testingBoard = new Board();
        boolean notMoveable;

        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                testingBoard.board[row][col].setVal(this.board[row][col].getVal());
            }
        }
        testingBoard.moveLeft();
        notMoveable = this.equals(testingBoard);

        testingBoard.moveRight();
        notMoveable = notMoveable && this.equals(testingBoard);

        testingBoard.moveUp();
        notMoveable = notMoveable && this.equals(testingBoard);

        testingBoard.moveDown();
        notMoveable = notMoveable && this.equals(testingBoard);

        return !notMoveable;
    }

    public boolean equals(Board other)
    {
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                if (this.board[row][col].getVal() != other.board[row][col].getVal())
                {
                    return false;
                }
            }
        }
        return true;
    }

    public void reset()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Tile(0);
            }
        }
    }

    public void moveLeft()
    {
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 1; col < BOARD_SIZE;)
            {
                if (col > 0)
                {
                    board[row][col-1].combine(board[row][col]);
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

            for (int col = 0; col < BOARD_SIZE; col++)
            {
                board[row][col].setNewlyCombined(false);
            }
        }

       generate();
    }

    public void moveRight()
    {
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = BOARD_SIZE; col >= 0;)
            {
                if (col < BOARD_SIZE - 1)
                {
                    board[row][col + 1].combine(board[row][col]);
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

            for (int col = 0; col < BOARD_SIZE; col++)
            {
                board[row][col].setNewlyCombined(false);
            }
        }

        generate();
    }
    public void moveUp()
    {
        for (int col = 0; col < BOARD_SIZE; col++)
        {
            for (int row = 1; row < BOARD_SIZE;)
            {
                if (row > 0)
                {
                    board[row-1][col].combine(board[row][col]);
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

            for (int row = 0; row < BOARD_SIZE; row++)
            {
                board[row][col].setNewlyCombined(false);
            }
        }

        generate();
    }
    public void moveDown()
    {
        for (int col = 0; col < BOARD_SIZE; col++)
        {
            for (int row = BOARD_SIZE - 1; row >= 0;)
            {
                if (row < BOARD_SIZE - 1)
                {
                    board[row+1][col].combine(board[row][col]);
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

            for (int row = 0; row < BOARD_SIZE; row++)
            {
                board[row][col].setNewlyCombined(false);
            }
        }

        generate();
    }

    public void draw (Graphics g)
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                board[i][j].draw(g, j, i);
            }
        }
    }

    public void save() throws IOException
    {
        File wordFile = new File("rsc/save.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(wordFile, false));

        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                String output = board[row][col].getVal() + "";
                writer.append(output);
                writer.newLine();
            }
        }
        writer.close();
    }

    public void load()
    {
        Scanner s;
        File saveFile = new File("rsc/save.txt");
        try {
            s = new Scanner(saveFile);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open dictionary file.");
            return;
        }

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                board[row][col].setVal(Integer.parseInt(s.nextLine()));
            }
        }
    }
}
