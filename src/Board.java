import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    private final Tile[][] board;
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
        ArrayList<Integer[]> spaces = new ArrayList<>();
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
        boolean notMovable;

        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                testingBoard.board[row][col].setVal(this.board[row][col].getVal());
            }
        }
        testingBoard.moveLeft();
        notMovable = this.equals(testingBoard);

        testingBoard.moveRight();
        notMovable = notMovable && this.equals(testingBoard);

        testingBoard.moveUp();
        notMovable = notMovable && this.equals(testingBoard);

        testingBoard.moveDown();
        notMovable = notMovable && this.equals(testingBoard);

        return !notMovable;
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

        generate();
    }

    public void moveLeft()
    {
        boolean hasChanged = false;
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 1; col < BOARD_SIZE;)
            {
                if (col > 0)
                {
                    hasChanged = board[row][col-1].combine(board[row][col]) || hasChanged;
                }
                if (col > 0 && board[row][col].getVal() != 0 && board[row][col-1].getVal() == 0)
                {
                    board[row][col-1].setVal(board[row][col].getVal());
                    board[row][col].setVal(0);
                    col--;
                    hasChanged = true;
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
        if (hasChanged)
        {
            generate();
        }
    }

    public void moveRight()
    {
        boolean hasChanged = false;
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = BOARD_SIZE; col >= 0;)
            {
                if (col < BOARD_SIZE - 1)
                {
                    hasChanged = board[row][col + 1].combine(board[row][col]) || hasChanged;
                }

                if (col < BOARD_SIZE - 1 && board[row][col].getVal() != 0 && board[row][col+1].getVal() == 0)
                {
                    board[row][col+1].setVal(board[row][col].getVal());
                    board[row][col].setVal(0);
                    col++;
                    hasChanged = true;
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

        if (hasChanged)
        {
            generate();
        }
    }
    public void moveUp()
    {
        boolean hasChanged = false;
        for (int col = 0; col < BOARD_SIZE; col++)
        {
            for (int row = 1; row < BOARD_SIZE;)
            {
                if (row > 0)
                {
                    hasChanged = board[row-1][col].combine(board[row][col]) || hasChanged;
                }
                if (row > 0 && board[row][col].getVal() != 0 && board[row-1][col].getVal() == 0)
                {
                    board[row-1][col].setVal(board[row][col].getVal());
                    board[row][col].setVal(0);
                    row--;
                    hasChanged = true;
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

        if (hasChanged)
        {
            generate();
        }
    }
    public void moveDown()
    {
        boolean hasChanged = false;
        for (int col = 0; col < BOARD_SIZE; col++)
        {
            for (int row = BOARD_SIZE - 1; row >= 0;)
            {
                if (row < BOARD_SIZE - 1)
                {
                    hasChanged = board[row+1][col].combine(board[row][col]) || hasChanged;
                }

                if (row < BOARD_SIZE - 1 && board[row][col].getVal() != 0 && board[row+1][col].getVal() == 0)
                {
                    board[row+1][col].setVal(board[row][col].getVal());
                    board[row][col].setVal(0);
                    row++;
                    hasChanged = true;
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

        if (hasChanged)
        {
            generate();
        }
    }

    public void draw (Graphics g)
    {
        g.setColor(GameViewer.BORDER_COLOR);
        g.fillRoundRect(GameViewer.SCREEN_HEIGHT / 20 + GameViewer.SCREEN_WIDTH / 3,
                GameViewer.SCREEN_HEIGHT / 20 + 30,
                GameViewer.SCREEN_HEIGHT / 20 * 18,
                GameViewer.SCREEN_HEIGHT / 20 * 18, 25, 25);

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
                writer.append(String.valueOf(board[row][col].getVal()));
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
