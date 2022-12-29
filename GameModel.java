import java.util.Random;

public class GameModel {

  // instance variables
  private int width;
  private int height;
  private boolean[][] gameBoard;
  private int numberOfClicks = 0;
  private Solution solution;

  // constructor
  public GameModel(int width, int height) {
    this.width = width;
    this.height = height;
    gameBoard = new boolean[height][width];
  }

  // getter methods
  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  // checks to see if a spot on the board is ON
  public boolean isON(int i, int j) {
    return gameBoard[i][j];
  }

  // resets the model to all OFF
  public void reset() {
    numberOfClicks = 0;
    for (int x = 0; x < height; x++) {
      for (int y = 0; y < width; y++) {
        gameBoard[x][y] = false;
      }
    }
  }

  // sets the location (i,j) of the model to "value" (column i and row j)
  public void set(int i, int j, boolean value) {
    gameBoard[i][j] = value;
  }

  // canonical string representation of GameModel
  public String toString() {
    String strGameBoard = "[";

    for (int x = 0; x < height; x++) {
      strGameBoard += "[";

      for (int y = 0; y < width; y++) {
        if (y != width - 1) {
          strGameBoard += gameBoard[x][y] + ",";
        } else {
          strGameBoard += gameBoard[x][y];
        }
      }

      strGameBoard += "]";
    }
    strGameBoard += "]";

    return strGameBoard;
  }

  public void click(int i, int j) {
    numberOfClicks++;

    if (gameBoard[i][j]) {
      set(i, j, false);
    } else {
      set(i, j, true);
    }

    if (height == 1 && width == 1) {} else if (i == 0 && height == 1) {
      if (j == 0) {
        if (gameBoard[i][j + 1]) {
          set(i, j + 1, false);
        } else {
          set(i, j + 1, true);
        }
      } else if (j == width - 1) {
        if (gameBoard[i][j - 1]) {
          set(i, j - 1, false);
        } else {
          set(i, j - 1, true);
        }
      } else {
        if (gameBoard[i][j + 1]) {
          set(i, j + 1, false);
        } else {
          set(i, j + 1, true);
        }

        if (gameBoard[i][j - 1]) {
          set(i, j - 1, false);
        } else {
          set(i, j - 1, true);
        }
      }
    } else if (j == 0 && width == 1) {
      if (i == 0) {
        if (gameBoard[i + 1][j]) {
          set(i + 1, j, false);
        } else {
          set(i + 1, j, true);
        }
      } else if (i == height - 1) {
        if (gameBoard[i - 1][j]) {
          set(i - 1, j, false);
        } else {
          set(i - 1, j, true);
        }
      } else {
        if (gameBoard[i + 1][j]) {
          set(i + 1, j, false);
        } else {
          set(i + 1, j, true);
        }

        if (gameBoard[i - 1][j]) {
          set(i - 1, j, false);
        } else {
          set(i - 1, j, true);
        }
      }
    } else if (i == 0 && j == 0) {
      if (gameBoard[i + 1][j]) {
        set(i + 1, j, false);
      } else {
        set(i + 1, j, true);
      }

      if (gameBoard[i][j + 1]) {
        set(i, j + 1, false);
      } else {
        set(i, j + 1, true);
      }
    } else if (i == 0 && j == width - 1) {
      if (gameBoard[i + 1][j]) {
        set(i + 1, j, false);
      } else {
        set(i + 1, j, true);
      }

      if (gameBoard[i][j - 1]) {
        set(i, j - 1, false);
      } else {
        set(i, j - 1, true);
      }
    } else if (i == height - 1 && j == 0) {
      if (gameBoard[i - 1][j]) {
        set(i - 1, j, false);
      } else {
        set(i - 1, j, true);
      }

      if (gameBoard[i][j + 1]) {
        set(i, j + 1, false);
      } else {
        set(i, j + 1, true);
      }
    } else if (i == height - 1 && j == width - 1) {
      if (gameBoard[i - 1][j]) {
        set(i - 1, j, false);
      } else {
        set(i - 1, j, true);
      }

      if (gameBoard[i][j - 1]) {
        set(i, j - 1, false);
      } else {
        set(i, j - 1, true);
      }
    } else if (i == 0) {
      if (gameBoard[i][j - 1]) {
        set(i, j - 1, false);
      } else {
        set(i, j - 1, true);
      }

      if (gameBoard[i][j + 1]) {
        set(i, j + 1, false);
      } else {
        set(i, j + 1, true);
      }

      if (gameBoard[i + 1][j]) {
        set(i + 1, j, false);
      } else {
        set(i + 1, j, true);
      }
    } else if (i == height - 1) {
      if (gameBoard[i][j - 1]) {
        set(i, j - 1, false);
      } else {
        set(i, j - 1, true);
      }

      if (gameBoard[i][j + 1]) {
        set(i, j + 1, false);
      } else {
        set(i, j + 1, true);
      }

      if (gameBoard[i - 1][j]) {
        set(i - 1, j, false);
      } else {
        set(i - 1, j, true);
      }
    } else if (j == 0) {
      if (gameBoard[i - 1][j]) {
        set(i - 1, j, false);
      } else {
        set(i - 1, j, true);
      }

      if (gameBoard[i + 1][j]) {
        set(i + 1, j, false);
      } else {
        set(i + 1, j, true);
      }
      if (gameBoard[i][j + 1]) {
        set(i, j + 1, false);
      } else {
        set(i, j + 1, true);
      }
    } else if (j == width - 1) {
      if (gameBoard[i - 1][j]) {
        set(i - 1, j, false);
      } else {
        set(i - 1, j, true);
      }

      if (gameBoard[i + 1][j]) {
        set(i + 1, j, false);
      } else {
        set(i + 1, j, true);
      }
      if (gameBoard[i][j - 1]) {
        set(i, j - 1, false);
      } else {
        set(i, j - 1, true);
      }
    } else {
      if (gameBoard[i][j + 1]) {
        set(i, j + 1, false);
      } else {
        set(i, j + 1, true);
      }

      if (gameBoard[i][j - 1]) {
        set(i, j - 1, false);
      } else {
        set(i, j - 1, true);
      }

      if (gameBoard[i + 1][j]) {
        set(i + 1, j, false);
      } else {
        set(i + 1, j, true);
      }
      if (gameBoard[i - 1][j]) {
        set(i - 1, j, false);
      } else {
        set(i - 1, j, true);
      }
    }
  }

  public int getNumberOfSteps() {
    return numberOfClicks;
  }

  public boolean isFinished() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (gameBoard[i][j] == false) {
          return false;
        }
      }
    }

    return true;
  }

  public void randomize() {
    reset();

    boolean solvable = false;
    Random rand;

    while (!solvable) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          rand = new Random();
          gameBoard[i][j] = rand.nextBoolean();
        }
      }

      if (LightsOut.solve(this).size() > 0) {
        solvable = true;
      }
    }
  }

  public void setSolution() {
    solution = LightsOut.solveShortest(this);
  }

  public boolean solutionSelects(int i, int j) {
    if (solution == null) {
      return false;
    }

    return solution.get(i, j);
  }
}
