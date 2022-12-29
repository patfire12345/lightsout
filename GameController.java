import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GameController implements ActionListener, ItemListener {

  private int width;
  private int height;
  private GameModel model;
  private GameView view;

  /**
    // Constructor used for initializing the controller. It creates the game's view 
    // and the game's model instances
     * 
     * @param width the width of the board on which the game will be played
     * @param height the height of the board on which the game will be played
     */
  public GameController(int width, int height) {
    this.width = width;
    this.height = height;
    model = new GameModel(width, height);
    view = new GameView(model, this);
  }

  /**
   * Callback used when the user clicks a button (reset,
   * random or quit)
   *
   * @param e the ActionEvent
   */

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Reset")) {
      model.reset();
      view.update(model);
    }

    if (e.getActionCommand().equals("Random")) {
      model.randomize();
      view.update(model);
    }

    if (e.getActionCommand().equals("Quit")) {
      System.exit(0);
    }

    if (e.getSource() instanceof GridButton) {
      GridButton tmp = (GridButton) e.getSource();
      model.click(tmp.getRow(), tmp.getColumn());
      view.update(model);
    }

    if (e.getActionCommand().equals("Play Again")) {
      model.reset();
      view.update(model);
    }
  }

  /**
   * Callback used when the user select/unselects
   * a checkbox
   *
   * @param e the ItemEvent
   */

  public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == 1) {
      view.setSolutionShown(true);
      view.update(model);
    }

    if (e.getStateChange() != 1) {
      view.setSolutionShown(false);
      view.update(model);
    }
  }
}
