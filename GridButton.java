import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class GridButton extends JButton {

  JButton button;
  int row, column;
  GameView view;
  ImageIcon[] icons;

  /**
   * Constructor used for initializing a GridButton at a specific
   * Board location.
   *
   * @param column the column of this Cell
   * @param row the row of this Cell
   */

  public GridButton(int column, int row, GameView view) {
    this.row = row;
    this.column = column;
    this.view = view;

    icons = new ImageIcon[4];
    icons[0] =
      new ImageIcon(GridButton.class.getResource("/Icons/Light-0.png"));
    icons[1] =
      new ImageIcon(GridButton.class.getResource("/Icons/Light-1.png"));
    icons[2] =
      new ImageIcon(GridButton.class.getResource("/Icons/Light-2.png"));
    icons[3] =
      new ImageIcon(GridButton.class.getResource("/Icons/Light-3.png"));

    setBackground(Color.WHITE);
    Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
    setBorder(emptyBorder);
    setBorderPainted(false);

    setIcon(getImageIcon(1));
  }

  /**
   * sets the icon of the button to reflect the
   * state specified by the parameters
   * @param isOn true if that location is ON
   * @param isClicked true if that location is
   * tapped in the model's current solution
   */
  public void setState(boolean isOn, boolean isClicked) {
    if (isOn) {
      if (isClicked && view.solutionShown()) {
        setIcon(getImageIcon(2));
      } else if (!isClicked || !view.solutionShown()) {
        setIcon(getImageIcon(0));
      }
    }

    if (!isOn) {
      if (isClicked && view.solutionShown()) {
        setIcon(getImageIcon(3));
      } else if (!isClicked || !view.solutionShown()) {
        setIcon(getImageIcon(1));
      }
    }
  }

  /**
   * Getter method for the attribute row.
   *
   * @return the value of the attribute row
   */

  public int getRow() {
    return row;
  }

  /**
   * Getter method for the attribute column.
   *
   * @return the value of the attribute column
   */

  public int getColumn() {
    return column;
  }

  public ImageIcon getImageIcon(int index) {
    return icons[index];
  }
}
