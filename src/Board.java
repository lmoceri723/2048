// By Landon Moceri on 5/12
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    // Instance Variables
    private final Tile[][] board;
    public static final int BOARD_SIZE = 4;

    // Constructor for Board
    public Board()
    {
        board = new Tile[4][4];
        reset();
    }

    // Generates a tile on a random empty space using the helper method getEmptySpaces
    public void generate()
    {
        ArrayList<Integer[]> spaces = getEmptySpaces();

        if (!spaces.isEmpty())
        {
            int index = (int) (Math.random() * spaces.size());
            board[spaces.get(index)[0]][spaces.get(index)[1]] = new Tile();
        }

    }

    // Checks all spaces and returns the empty ones in an ArrayList
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

    // Checks if the game is not lost
    public boolean gameNotOver()
    {
        // Creates a new board and copies over the board to it
        Board testingBoard = new Board();
        boolean notMovable;

        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                testingBoard.board[row][col].setVal(this.board[row][col].getVal());
            }
        }

        // Moves the board in each direction and checks for differences,
        // Keeping track of an immovable board using boolean notMovable
        testingBoard.moveLeft();
        notMovable = this.equals(testingBoard);

        testingBoard.moveRight();
        notMovable = notMovable && this.equals(testingBoard);

        testingBoard.moveUp();
        notMovable = notMovable && this.equals(testingBoard);

        testingBoard.moveDown();
        notMovable = notMovable && this.equals(testingBoard);

        // Returns the opposite to correspond to the name "gameNotOver"
        return !notMovable;
    }

    // checks if two boards are identical
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

    // Resets a board to all empty squares and then adds a single tile to it
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

    // Moves the board left, row by row
    public void moveLeft()
    {
        // Tracks if the board has changed
        boolean hasChanged = false;

        // Iterates over the board in row-major traversal
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 1; col < BOARD_SIZE;)
            {
                // Checks if the col index is valid
                if (col > 0)
                {
                    // Attempts to combine the tile and the one next to it and keeps track of the change
                    hasChanged = board[row][col-1].combine(board[row][col]) || hasChanged;
                }
                // Checks if the tile can be moved to the left
                if (col > 0 && board[row][col].getVal() != 0 && board[row][col-1].getVal() == 0)
                {
                    // Moves it to the left by swapping the places of the two tiles and notes the change
                    board[row][col-1].setVal(board[row][col].getVal());
                    board[row][col].setVal(0);
                    // Moves back one square to attempt to move or combine the square further
                    col--;
                    hasChanged = true;
                }
                // Increases to the next square
                else
                {
                    col++;
                }
            }

            // Makes sure no squares retain the newlyCombined state of true
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                board[row][col].setNewlyCombined(false);
            }
        }

        // Generates a new tile if the board has changed in the movement
        if (hasChanged)
        {
            generate();
        }
    }

    // Moves the board right, row by row
    public void moveRight()
    {
        // Tracks if the board has changed
        boolean hasChanged = false;
        // Iterates over the board in row-major traversal
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = BOARD_SIZE; col >= 0;)
            {
                // Checks if the col index is valid
                if (col < BOARD_SIZE - 1)
                {
                    // Attempts to combine the tile and the one next to it and keeps track of the change
                    hasChanged = board[row][col + 1].combine(board[row][col]) || hasChanged;
                }
                // Checks if the tile can be moved to the right
                if (col < BOARD_SIZE - 1 && board[row][col].getVal() != 0 && board[row][col+1].getVal() == 0)
                {
                    // Moves it to the right by swapping the places of the two tiles and notes the change
                    board[row][col+1].setVal(board[row][col].getVal());
                    board[row][col].setVal(0);
                    // Moves back one square to attempt to move or combine the square further
                    col++;
                    hasChanged = true;
                }
                // Decreases to the next square
                else
                {
                    col--;
                }
            }

            // Makes sure no squares retain the newlyCombined state of true
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                board[row][col].setNewlyCombined(false);
            }
        }

        // Generates a new tile if the board has changed in the movement
        if (hasChanged)
        {
            generate();
        }
    }

    // Moves the board up, column by column
    public void moveUp()
    {
        // Tracks if the board has changed
        boolean hasChanged = false;
        // Iterates over the board in column-major traversal
        for (int col = 0; col < BOARD_SIZE; col++)
        {
            for (int row = 1; row < BOARD_SIZE;)
            {
                // Checks if the row index is valid
                if (row > 0)
                {
                    // Attempts to combine the tile and the one next to it and keeps track of the change
                    hasChanged = board[row-1][col].combine(board[row][col]) || hasChanged;
                }
                // Checks if the tile can be moved up
                if (row > 0 && board[row][col].getVal() != 0 && board[row-1][col].getVal() == 0)
                {
                    // Moves it up by swapping the places of the two tiles and notes the change
                    board[row-1][col].setVal(board[row][col].getVal());
                    board[row][col].setVal(0);
                    // Moves back one square to attempt to move or combine the square further
                    row--;
                    hasChanged = true;
                }
                // Increases to the next square
                else
                {
                    row++;
                }
            }

            // Makes sure no squares retain the newlyCombined state of true
            for (int row = 0; row < BOARD_SIZE; row++)
            {
                board[row][col].setNewlyCombined(false);
            }
        }

        // Generates a new tile if the board has changed in the movement
        if (hasChanged)
        {
            generate();
        }
    }

    // Moves the board down, column by column
    public void moveDown()
    {
        // Tracks if the board has changed
        boolean hasChanged = false;
        // Iterates over the board in column-major traversal
        for (int col = 0; col < BOARD_SIZE; col++)
        {
            for (int row = BOARD_SIZE - 1; row >= 0;)
            {
                // Checks if the row index is valid
                if (row < BOARD_SIZE - 1)
                {
                    // Attempts to combine the tile and the one next to it and keeps track of the change
                    hasChanged = board[row+1][col].combine(board[row][col]) || hasChanged;
                }
                // Checks if the tile can be moved down
                if (row < BOARD_SIZE - 1 && board[row][col].getVal() != 0 && board[row+1][col].getVal() == 0)
                {
                    // Moves it down by swapping the places of the two tiles and notes the change
                    board[row+1][col].setVal(board[row][col].getVal());
                    board[row][col].setVal(0);
                    // Moves back one square to attempt to move or combine the square further
                    row++;
                    hasChanged = true;
                }
                // Decreases to the next square
                else
                {
                    row--;
                }
            }

            // Makes sure no squares retain the newlyCombined state of true
            for (int row = 0; row < BOARD_SIZE; row++)
            {
                board[row][col].setNewlyCombined(false);
            }
        }

        // Generates a new tile if the board has changed in the movement
        if (hasChanged)
        {
            generate();
        }
    }

    // Draws the board
    public void draw (Graphics g)
    {
        // Draws the background of the board, which ends up looking like the borders of the squares
        g.setColor(GameViewer.BORDER_COLOR);
        g.fillRoundRect(GameViewer.SCREEN_HEIGHT / 20 + GameViewer.SCREEN_WIDTH / 3,
                GameViewer.SCREEN_HEIGHT / 20 + 30,
                GameViewer.SCREEN_HEIGHT / 20 * 18,
                GameViewer.SCREEN_HEIGHT / 20 * 18, 25, 25);

        // Draws each tile on the board
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                board[row][col].draw(g, col, row);
            }
        }
    }

    // Attempts to save the board
    public void save() throws IOException
    {
        // Initializes the file and writer
        File wordFile = new File("rsc/save.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(wordFile, false));

        // Writes the data of the save to the file using a new line for each value
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

    // Loads the board values from the save
    public void load()
    {
        // Initializes the scanner and file
        Scanner s;
        File saveFile = new File("rsc/save.txt");
        try {
            s = new Scanner(saveFile);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open dictionary file.");
            return;
        }

        // Reads the file, setting each value to its corresponding line
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                board[row][col].setVal(Integer.parseInt(s.nextLine()));
            }
        }
    }
}
