import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The class GameView provides the current view of the entire Game. It extends
 * JFrame and lays out a matrix of GridButton (the actual game) and
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 */

public class GameView extends JFrame {

  private JLabel clickCounter;
  private JButton reset, random, quit;
  private JCheckBox checkBox;
  private GridButton grid[][];
  private boolean solutionShown;

  private GameModel gameModel;
  private GameController gameController;
  private JPanel buttons;
  private JPanel board;
  private JFrame frame;
  private JFrame endFrame;

  /**
   * Constructor used for initializing the Frame
   *
   * @param gameModel the model of the game (already initialized)
   * @param gameController the controller
   */

  public GameView(GameModel gameModel, GameController gameController) {
    this.gameModel = gameModel;
    this.gameController = gameController;
    solutionShown = false;

    frame = new JFrame();
    endFrame = new JFrame();
    buttons = new JPanel();
    board = new JPanel();

    frame.setSize(700, 700);
    buttons.setLayout(new GridLayout(4, 0));
    board.setLayout(
      new GridLayout(gameModel.getHeight(), gameModel.getWidth())
    );

    clickCounter =
      new JLabel(
        "Number of steps: " + Integer.toString(gameModel.getNumberOfSteps())
      );

    reset = new JButton("Reset");
    random = new JButton("Random");
    quit = new JButton("Quit");

    checkBox = new JCheckBox("Solution");

    reset.addActionListener(gameController);
    quit.addActionListener(gameController);
    random.addActionListener(gameController);
    checkBox.addItemListener(gameController);

    buttons.add(reset);
    buttons.add(quit);
    buttons.add(random);
    buttons.add(checkBox);

    frame.add(buttons, BorderLayout.EAST);
    frame.add(clickCounter, BorderLayout.SOUTH);

    grid = new GridButton[gameModel.getHeight()][gameModel.getWidth()];

    for (int x = 0; x < gameModel.getHeight(); x++) {
      for (int y = 0; y < gameModel.getWidth(); y++) {
        GridButton gridButton = new GridButton(y, x, this);
        grid[x][y] = gridButton;
        grid[x][y].addActionListener(gameController);
        board.add(grid[x][y]);
      }
    }

    frame.add(board);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setTitle("Lights Out -- by Patrick Huang");

    frame.setVisible(true);
  }

  /**
   * updates the status of the board's GridButton instances based
   * on the current game model, then redraws the view
   */

  public void update(GameModel model) {
    int counter = 0;

    gameModel = model;
    gameModel.setSolution();

    clickCounter.setText(
      "Number of steps: " + Integer.toString(gameModel.getNumberOfSteps())
    );

    for (int i = 0; i < gameModel.getHeight(); i++) {
      for (int j = 0; j < gameModel.getWidth(); j++) {
        if (gameModel.isON(i, j)) {
          counter++;
        }

        grid[i][j].setState(
            gameModel.isON(i, j),
            gameModel.solutionSelects(i, j)
          );
      }
    }

    if (counter == gameModel.getHeight() * gameModel.getWidth()) {
      int playAgain = JOptionPane.showConfirmDialog(
        null,
        "Congratulations, you won in " +
        gameModel.getNumberOfSteps() +
        " steps! Would you like to play again?",
        "You won!",
        JOptionPane.YES_NO_OPTION
      );

      if (playAgain == 0) {
        gameModel.reset();
        update(gameModel);
      } else if (playAgain == 1) {
        System.exit(0);
      }
    }
  }

  /**
   * returns true if the ``solution'' checkbox
   * is checked
   *
   * @return the status of the ``solution'' checkbox
   */

  public boolean solutionShown() {
    return solutionShown;
  }

  public void setSolutionShown(boolean solution) {
    solutionShown = solution;
  }
}
