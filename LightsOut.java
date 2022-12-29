import java.util.ArrayList;

/**
 * The class LightsOut is the
 * class that implements the method to
 * computes solutions of the Lights Out game.
 * It contains the main of our application.
 */

public class LightsOut {

  /**
   * default width of the game.
   */
  public static final int DEFAULT_WIDTH = 3;
  /**
   * default height of the game.
   */
  public static final int DEFAULT_HEIGHT = 3;

  /**
   * The method solve finds all the
   * solutions to the Lights Out game
   * for an initially completely ``off'' board
   * of size width x height, using a
   * Breadth-First Search algorithm.
   *
   * It returns an ArrayList<Solution>
   * containing all the valid solutions to the
   * problem.
   *
   * This version does not continue exploring a
   * partial solution that is known to be
   * impossible. It will also attempt to complete
   * a solution as soon as possible
   *
   * During the computation of the solution, the
   * method prints out a message each time a new
   * solution  is found, along with the total time
   * it took (in milliseconds) to find that solution.
   *
   * @param width the width of the board
   * @param height the height of the board
   * @return an instance of ArrayList<Solution> containing all the solutions
   */
  public static ArrayList<Solution> solve(int width, int height) {
    GameModel model = new GameModel(width, height);
    return solve(model);
  }

  public static ArrayList<Solution> solve(GameModel model) {
    Queue<Solution> q = new QueueImplementation<Solution>();
    ArrayList<Solution> solutions = new ArrayList<Solution>();

    q.enqueue(new Solution(model.getWidth(), model.getHeight()));
    long start = System.currentTimeMillis();
    while (!q.isEmpty()) {
      Solution s = q.dequeue();
      if (s.isReady()) {
        // by construction, it is successful
        solutions.add(s);
      } else {
        boolean withTrue = s.stillPossible(true, model);
        boolean withFalse = s.stillPossible(false, model);
        if (withTrue && withFalse) {
          Solution s2 = new Solution(s);
          s.setNext(true);
          q.enqueue(s);
          s2.setNext(false);
          q.enqueue(s2);
        } else if (withTrue) {
          s.setNext(true);
          if (s.finish(model)) {
            q.enqueue(s);
          }
        } else if (withFalse) {
          s.setNext(false);
          if (s.finish(model)) {
            q.enqueue(s);
          }
        }
      }
    }
    return solutions;
  }

  public static Solution solveShortest(GameModel model) {
    ArrayList<Solution> s = solve(model);
    int lowCounter = model.getHeight() * model.getWidth();
    int index = 0;

    for (int x = 0; x < s.size(); x++) {
      if (s.get(x).getSize() < lowCounter) {
        lowCounter = s.get(x).getSize();
        index = x;
      }
    }

    return s.get(index);
  }

  /**
   * main method  calls the method solve
   * and then prints out the number of solutions found,
   * as well as the details of each solution.
   *
   * The width and height used by the
   * main are passed as runtime parameters to
   * the program. If no runtime parameters are passed
   * to the program, or if the parameters are incorrect,
   * then the default values are used.
   *
   * @param args Strings array of runtime parameters
   */
  public static void main(String[] args) {
    int width = DEFAULT_WIDTH;
    int height = DEFAULT_HEIGHT;

    StudentInfo.display();

    if (args.length == 2) {
      try {
        width = Integer.parseInt(args[0]);
        if (width < 1) {
          System.out.println("Invalid width, using default...");
          width = DEFAULT_WIDTH;
        }
        height = Integer.parseInt(args[1]);
        if (height < 1) {
          System.out.println("Invalid height, using default...");
          height = DEFAULT_HEIGHT;
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid argument, using default...");
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
      }
    }
    GameController game = new GameController(width, height);
  }
}
