import cs3500.animator.model.MovieModel;
import cs3500.animator.view.MovieView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

/**
 * Mock class which tests that an editor can take in an action.
 */
public class MockEditorView implements MovieView, ActionListener {
  public String log;
  public MovieModel m;

  @Override
  public void runAnimation(MovieModel model) {
    this.m = model;
  }

  @Override
  public void setOut(PrintWriter out) {
    return ;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.log = "received " + e.getActionCommand();
  }

  /*
  bl.actionPerformed(new ActionEvent(mv, 2, "OK Remove Shape")); so if bl is a button listener,
  and mv is a mock view, you can simulate a button click with the action command "OK remove shape"
  like this
   */
}
