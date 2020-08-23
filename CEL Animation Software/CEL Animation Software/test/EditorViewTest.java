import static org.junit.Assert.assertEquals;

import cs3500.animator.model.MovieModel;
import cs3500.animator.model.MovieModelImpl;
import cs3500.animator.view.EditorView;
import java.awt.event.ActionEvent;
import org.junit.Test;

/**
 * Tests for the editor view.
 */
public class EditorViewTest {

  @Test
  public void testActionPerformed() {

    EditorView edit = new EditorView();
    MockEditorView mEdit = new MockEditorView();
    MovieModel movie1 = new MovieModelImpl();
    movie1.addTranslateToShape("r", 0, 30,
        20, 50, 40, 100);
    movie1.addTranslateToShape("r", 30, 35,
        40, 100, 40, 10);
    movie1.addResizeToShape("r", 0, 35,
        40, 40, 20, 20);
    movie1.addColorChangeToShape("r", 0, 35,
        0, 0, 0, 255, 255, 0);
    mEdit.runAnimation(new MovieModelImpl());

    mEdit.actionPerformed(new ActionEvent(mEdit, 1, "Submit changes"));
    assertEquals("received Submit changes", mEdit.log);
    mEdit.actionPerformed(new ActionEvent(mEdit, 1, "Add ellipse"));
    assertEquals("received Add ellipse", mEdit.log);
    mEdit.actionPerformed(new ActionEvent(mEdit, 1, "Add rectangle"));
    assertEquals("received Add rectangle", mEdit.log);
  }
}