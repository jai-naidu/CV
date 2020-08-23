package cs3500.animator.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cs3500.animator.model.MovieModel;
import cs3500.animator.model.ShapeAnimation;
import cs3500.animator.model.shape.Shape;

import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * A class which visualizes an animation by playing as a movie. The movie is displayed in a pop-up
 * window.
 */
public class EditorView extends JFrame implements MovieView, ActionListener, ChangeListener {

  private final JButton deleteButton;
  private final JTextField tickField;
  private final JTextField xField;
  private final JTextField yField;
  private final JTextField wField;
  private final JTextField hField;
  private final JTextField aField;
  private final JButton submitButton;
  private final JButton playButton;
  private final JButton restartButton;
  private final JButton loopButton;
  private final JTextField speedField;
  private final JButton speedButton;

  private final JTextField nameField;
  private final JButton makeEllipse;
  private final JButton makeRectangle;
  private final JButton deleteShape;
  private final JButton addLayer;


  private final JPanel main;
  private final JButton deleteLayer;
  private final JButton layerUp;
  private final JButton layerDown;
  private JPanel layerPanel;

  private JLabel colorChooserDisplay;
  private final JPanel shapePanel;
  private Color selectedColor;

  private EditorDisplayView displayView;

  private MovieModel model;
  private Timer timer;
  private ShapeAnimation selected;
  private int selectedTick;
  private JComboBox<String> shapeBox;

  private JComboBox<Integer> tickBox;

  private JComboBox<String> layerBox;

  private int maxLayer;
  private JSlider scrubber;
  private String selectedLayer;

  private JTextField outField;
  private JButton export;

  /**
   * Initializes a VisualView with the default parameters for a movie.
   */
  public EditorView() {
    super();
    setTitle("ExCELlence Editor");
    setSize(600, 720);

    main = new JPanel();
    main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
    this.layerPanel = new JPanel();
    layerPanel.setBorder(BorderFactory.createTitledBorder("Layers"));

    this.maxLayer = 1;

    this.shapePanel = new JPanel();
    shapePanel.setBorder(BorderFactory.createTitledBorder("Shape menu"));
    JLabel shapeLabel = new JLabel("Pick a shape to edit.");
    shapePanel.add(shapeLabel);

    this.nameField = new JTextField(10);
    nameField.setBorder(BorderFactory.createTitledBorder("Enter a shape name"));

    this.deleteShape = new JButton("Delete Selected Shape");
    deleteShape.setActionCommand("Delete Shape");
    deleteShape.addActionListener(this);

    this.makeEllipse = new JButton("Add Ellipse");
    makeEllipse.setActionCommand("Add ellipse");
    makeEllipse.addActionListener(this);

    this.makeRectangle = new JButton("Add Rectangle");
    makeRectangle.setActionCommand("Add rectangle");
    makeRectangle.addActionListener(this);

    this.tickField = new JTextField(10);
    tickField.setBorder(BorderFactory.createTitledBorder("Or enter the tick of the Keyframe to "
            + "add"));
    JButton keyButton = new JButton("Enter KeyFrame");
    keyButton.addActionListener(this);

    this.deleteButton = new JButton("Delete KeyFrame");
    deleteButton.setActionCommand("Delete KeyFrame");
    deleteButton.addActionListener(this);

    JPanel tickPanel = new JPanel();
    tickPanel.setBorder(BorderFactory.createTitledBorder("Choose new attributes"));
    JLabel tickLabel = new JLabel("Pick a keyframe to edit for this shape.");
    shapePanel.add(tickLabel);

    this.xField = new JTextField(20);
    xField.setBorder(BorderFactory.createTitledBorder("Enter new x-coordinate."));

    this.yField = new JTextField(20);
    yField.setBorder(BorderFactory.createTitledBorder("Enter new y-coordinate."));

    this.wField = new JTextField(20);
    wField.setBorder(BorderFactory.createTitledBorder("Enter new width."));

    this.hField = new JTextField(20);
    hField.setBorder(BorderFactory.createTitledBorder("Enter new height."));

    this.aField = new JTextField(20);
    aField.setBorder(BorderFactory.createTitledBorder("Enter new angle."));

    // add layer-----------------------------------------------
    this.layerPanel = new JPanel();
    layerPanel.setBorder(BorderFactory.createTitledBorder("Layers"));

    this.addLayer = new JButton("add layer");
    addLayer.setActionCommand("add layer");
    addLayer.addActionListener(this);

    this.deleteLayer = new JButton("delete layer");
    deleteLayer.setActionCommand("delete layer");
    deleteLayer.addActionListener(this);

    this.layerUp = new JButton("move up");
    layerUp.setActionCommand("layer up");
    layerUp.addActionListener(this);

    this.layerDown = new JButton("move down");
    layerDown.setActionCommand("layer down");
    layerDown.addActionListener(this);
    //---------------------------------------------------------

    this.submitButton = new JButton("Edit keyframe");
    submitButton.setActionCommand("Submit changes");
    submitButton.addActionListener(this);

    playButton = new JButton("play/pause");
    playButton.setActionCommand("play/pause");
    playButton.addActionListener(this);

    restartButton = new JButton("restart");
    restartButton.setActionCommand("restart");
    restartButton.addActionListener(this);

    loopButton = new JButton("loop");
    loopButton.setActionCommand("loop");
    loopButton.addActionListener(this);

    speedButton = new JButton("Set speed");
    speedButton.setActionCommand("Set speed");
    speedButton.addActionListener(this);
    speedField = new JTextField(20);
    speedField.setBorder(BorderFactory.createTitledBorder("Enter frames per second."));

    this.scrubber = new JSlider(JSlider.HORIZONTAL);
    scrubber.setBorder(BorderFactory.createTitledBorder("Pick a time to start playing from."));
    scrubber.addChangeListener(this);

    this.export = new JButton("export svg");
    export.setActionCommand("export");
    export.addActionListener(this);
    outField = new JTextField(20);
    outField.setBorder(BorderFactory.createTitledBorder("Enter the name of the txt file to output"
            + "."));
  }

  @Override
  public void runAnimation(MovieModel model) {
    this.model = model;
    this.model.fitMovieToMax();
    this.displayView = new EditorDisplayView();

    displayView.runAnimation(model);
    scrubber.setMinimum(1);
    scrubber.setMaximum(model.getRuntime());
    this.selectedColor = null;
    this.timer = this.displayView.getTimer();
    displayView.setScrubber(scrubber);

    addGUI();

    List<String> listOfShapes = new ArrayList<>();
    for (ShapeAnimation sa : model.getShapeAnimations()) {
      listOfShapes.add(sa.getShape().getName());
    }
    this.shapeBox = new JComboBox<String>();
    this.tickBox = new JComboBox<Integer>();
    tickBox.setActionCommand("Shape Keyframes");
    tickBox.addActionListener(this);

    shapeBox.setActionCommand("Shape Names");
    shapeBox.addActionListener(this);
    for (int i = 0; i < listOfShapes.size(); i++) {
      shapeBox.addItem(listOfShapes.get(i));
    }

    shapePanel.add(shapeBox);
    // test
    shapePanel.add(tickBox);

    this.layerBox = new JComboBox<>();
    layerBox.setActionCommand("select layer");
    layerBox.addActionListener(this);
    for (int i = 0; i < model.getLayers().size(); i++) {
      layerBox.addItem(model.getLayers().get(i));
    }
    layerPanel.add(layerBox);

    this.makeVisible();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void setOut(PrintWriter out) {
    return;
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e == null) {
      return;
    }
    switch (e.getActionCommand()) {
      case "Shape Names":
        if (e.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) e.getSource();
          String name = ((String) box.getSelectedItem());
          for (String shapeName : model.getLayerShapeNames(selectedLayer)) {
            if (shapeName.equals(name)) {
              for (ShapeAnimation sa : model.getShapeAnimations()) {
                if (sa.getShape().getName().equals(name)) {
                  selected = sa;
                  updateKeyFrameBox();
                  return ;
                }
              }
            }
          }
          if (selected == null) {
            return;
          }

        }
        break;
      case "Shape Keyframes":
        if (e.getSource() instanceof JComboBox) {
          JComboBox<Integer> box = (JComboBox<Integer>) e.getSource();
          if (tickBox.getItemCount() > 0) {
            this.selectedTick = (int) box.getSelectedItem();
          }
        }
        break;
      case "select layer":
        if (e.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) e.getSource();
          String name = ((String) box.getSelectedItem());
          selectedLayer = name;
          updateLayerShapeBox();
        }
        break;
      case "add layer":
        maxLayer++;
        model.addLayer("Layer " + maxLayer);
        layerBox.addItem("Layer " + maxLayer);
        layerBox.setSelectedItem("Layer " + maxLayer);
        selectedLayer = "Layer " + maxLayer;
        updateKeyFrameBox();
        layerBox.repaint();
        break;
      case "delete layer":
        try {
          model.deleteLayer(selectedLayer);
          layerBox.removeItem(selectedLayer);
          layerBox.repaint();
          shapeBox.repaint();
          updateKeyFrameBox();
        } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "Please choose a layer to delete.",
                  "Unselected layer", JOptionPane.PLAIN_MESSAGE);
          return;
        }
        break;
      case "layer up":
        try {
          model.moveLayerUp(selectedLayer);
          layerBox.repaint();
        } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "Please choose a layer before editing.",
                  "Unselected layer", JOptionPane.PLAIN_MESSAGE);
          return;
        }
        break;
      case "layer down":
        try {
          model.moveLayerDown(selectedLayer);
          layerBox.repaint();
        } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "Please choose a layer before editing.",
                  "Unselected layer", JOptionPane.PLAIN_MESSAGE);
          return;
        }
        break;
      case "Color chooser":
        this.selectedColor = JColorChooser.showDialog(EditorView.this, "Choose a color",
                colorChooserDisplay.getBackground());
        colorChooserDisplay.setBackground(selectedColor);
        break;
      case "Submit changes":
        Shape s = null;
        try {
          s = selected.getShapeAtTick(selectedTick);

        } catch (NullPointerException ex) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "Please choose a shape before editing.",
                  "Unselected shape", JOptionPane.PLAIN_MESSAGE);
          return;
        }
        double x = s.getX();
        double y = s.getY();
        double w = s.getWidth();
        double h = s.getHeight();
        double a = s.getAngle();
        if (selectedColor == null) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "Please select a color",
                  "Color.", JOptionPane.PLAIN_MESSAGE);
          return;
        }
        try {
          if (!tickField.getText().isBlank()) {
            this.selectedTick = Integer.parseInt(tickField.getText());
          }
          x = Integer.parseInt(xField.getText());
          y = Integer.parseInt(yField.getText());
          w = Integer.parseInt(wField.getText());
          h = Integer.parseInt(hField.getText());
          a = Integer.parseInt(aField.getText());
        } catch (NumberFormatException exception) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "Please enter valid values for all attributes",
                  "Invalid attributes.", JOptionPane.PLAIN_MESSAGE);
          return;
        }
        model.addKeyFrame(s.getName(), selectedTick, x, y,
                w, h, Math.round(selectedColor.getRed()), Math.round(selectedColor.getGreen()),
                Math.round(selectedColor.getBlue()), a);
        updateKeyFrameBox();
        updateScrubber();
        break;
      case "Delete KeyFrame":
        try {
          model.deleteKeyFrame(selected.getShape().getName(), selectedTick);
        } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "There is no keyframe at that tick to delete",
                  "Error", JOptionPane.PLAIN_MESSAGE);
          return;
        }
        updateKeyFrameBox();
        updateScrubber();
        break;
      case "Add rectangle":
        if (nameField.getText().isBlank()) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "Please give a name",
                  "name missing.", JOptionPane.PLAIN_MESSAGE);
          return;
        }

        String name = nameField.getText();
        try {
          model.addRectangleShape(name);
          model.moveShapeToLayer(name, selectedLayer);
        } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "This name already exists",
                  "existing name.", JOptionPane.PLAIN_MESSAGE);
          return;
        }
        this.shapeBox.addItem(name);
        this.repaint();
        break;
      case "Add ellipse":
        if (nameField.getText().isBlank()) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "Please give a name",
                  "name missing.", JOptionPane.PLAIN_MESSAGE);
          return;
        }

        name = nameField.getText();
        try {
          model.addEllipseShape(name);
          model.moveShapeToLayer(name, selectedLayer);
        } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "This name already exists",
                  "existing name.", JOptionPane.PLAIN_MESSAGE);
          return;
        }
        this.shapeBox.addItem(name);
        this.updateScrubber();
        break;
      case "Delete Shape":
        try {
          model.deleteShape(selected.getShape().getName());
          this.shapeBox.removeItem(selected.getShape().getName());
          updateKeyFrameBox();
          updateScrubber();
        } catch (IllegalArgumentException ex) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "Please choose a shape before editing.",
                  "Unselected shape", JOptionPane.PLAIN_MESSAGE);
          return;
        }
        break;
      case "play/pause":
        if (timer.isRunning()) {
          timer.stop();
        } else {
          timer.start();
        }
        break;
      case "loop":
        displayView.toggleLoop();
        break;
      case "restart":
        scrubber.setValue(0);
        displayView.resetTick();
        break;
      case "Set speed":
        try {
          int speed = Integer.parseInt(speedField.getText());
          model.setSpeed(speed);
          timer.setDelay(1000 / speed);
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "Please enter a valid speed", "Error", JOptionPane.PLAIN_MESSAGE);
        }
        break;
      case "export":
        MovieView svgView = new SVGView();
        if (outField.getText().isBlank()) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "Please give a name for the file", "No name", JOptionPane.PLAIN_MESSAGE);
          return;
        }
        try {
          PrintWriter file = new PrintWriter(outField.getText());
          svgView.setOut(file);
          svgView.runAnimation(model);
        } catch (FileNotFoundException ex) {
          JOptionPane.showMessageDialog(EditorView.this,
                  "Please give a valid name for the file", "No name", JOptionPane.PLAIN_MESSAGE);
          return;
        }
        break;
      default:
        break;
    }
  }

  /**
   * Updates the scrubber according to the length of the movie.
   */
  private void updateScrubber() {
    model.fitMovieToMax();
    this.scrubber.setMaximum(model.getRuntime());
    this.repaint();
  }

  /**
   * Updates the list of Key frames for the selected shape.
   */
  private void updateKeyFrameBox() {
    if (tickBox != null) {
      tickBox.removeAllItems();
    }
    if (model.getLayerShapeNames(selectedLayer).size() == 0) {
      return;
    }
    for (int t : selected.getStartTicks()) {
      tickBox.addItem(t);
    }
    if (selected.getStartTicks().size() > 1) {
      tickBox.addItem(selected.getMaxTick());
    }
    tickBox.repaint();
  }

  /**
   * Updates the list in the box containing all shapes for the selected layer.
   */
  private void updateLayerShapeBox() {
    if (shapeBox != null) {
      shapeBox.removeAllItems();
    }
    for (String s : model.getLayerShapeNames(selectedLayer)) {
      shapeBox.addItem(s);
    }
    shapeBox.repaint();
  }

  /**
   * Puts together the buttons and panels in this Editor's GUI.
   */
  private void addGUI() {

    JPanel layerControls = new JPanel();
    //layerControls.add(layerBox);
    layerControls.add(addLayer);
    layerControls.add(deleteLayer);
    layerControls.add(layerUp);
    layerControls.add(layerDown);
    main.add(layerPanel);
    main.add(layerControls);

    main.add(shapePanel);
    main.add(deleteButton);
    main.add(tickField);
    main.add(xField);
    main.add(yField);
    main.add(wField);
    main.add(hField);
    main.add(aField);

    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    main.add(dialogBoxesPanel);
    JPanel colorChooserPanel = new JPanel();
    colorChooserPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(colorChooserPanel);
    JButton colorChooserButton = new JButton("Choose a color");
    colorChooserButton.setActionCommand("Color chooser");
    colorChooserButton.addActionListener(this);
    colorChooserPanel.add(colorChooserButton);
    colorChooserDisplay = new JLabel("      ");
    colorChooserDisplay.setOpaque(true); //so that background color shows up
    colorChooserDisplay.setBackground(Color.WHITE);
    colorChooserPanel.add(colorChooserDisplay);

    main.add(submitButton);

    main.add(nameField);
    JPanel shapeBar = new JPanel();
    shapeBar.add(makeEllipse);
    shapeBar.add(makeRectangle);
    shapeBar.add(deleteShape);
    main.add(shapeBar);

    JPanel movieBar = new JPanel();

    movieBar.add(playButton);
    movieBar.add(restartButton);
    movieBar.add(loopButton);
    main.add(movieBar);

    JPanel speedBar = new JPanel();
    speedBar.add(speedButton);
    speedBar.add(speedField);
    main.add(speedBar);

    JPanel outBar = new JPanel();
    outBar.add(outField);
    outBar.add(export);
    main.add(outBar);

    scrubber.setMajorTickSpacing(10);
    scrubber.setMinorTickSpacing(1);
    scrubber.setPaintTicks(true);
    scrubber.setPaintLabels(true);
    scrubber.setValue(1);
    main.add(scrubber);

    JScrollPane scrollPane = new JScrollPane(main);
    this.add(scrollPane);
  }

  /**
   * Makes this movie visible.
   */
  private void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider) e.getSource();
    if (source.getValueIsAdjusting()) {
      displayView.setFrame(source.getValue());
    }
  }

  /**
   * An extended version of a visual view that enables extra functionality necessary for the editor
   * view.
   */
  private class EditorDisplayView extends VisualView implements ActionListener {
    private boolean doesLoop;
    private JSlider scrubber;

    EditorDisplayView() {
      super();
      this.doesLoop = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      super.actionPerformed(e);
      if (this.tick == model.getRuntime() + 1) {
        if (this.doesLoop) {
          this.tick = 1;
          this.timer.restart();
        } else {
          this.timer.stop();
        }
      }
      moveScrubber(scrubber);
      scrubber.repaint();
    }

    /**
     * Set the scrubber.
     *
     * @param scrubber the scrubber
     */
    public void setScrubber(JSlider scrubber) {
      this.scrubber = scrubber;
    }

    /**
     * Places the scrubber in the appropriate position based on movie progress and user
     * interaction with the GUI.
     *
     * @param scrubber the scrubber
     */
    private void moveScrubber(JSlider scrubber) {
      scrubber.setValue(this.tick);
      this.scrubber = scrubber;
    }

    public Timer getTimer() {
      return this.timer;
    }

    /**
     * Resets the tick field in order to restart or loop the editor's visual view.
     */
    public void resetTick() {
      this.tick = 0;
      this.timer.restart();
    }

    /**
     * Toggles the switch that tells us whether or not to loop the visual in the editor view.
     */
    public void toggleLoop() {
      this.doesLoop = !this.doesLoop;
    }

    /**
     * Sets the tick of the visual view to the given view.
     */
    public void setTick(int tick) {
      this.tick = tick;
    }

    public int getTick() {
      return this.tick;
    }

    public void setFrame(int tick) {
      this.timer.stop();
      List<Shape> res = new ArrayList<Shape>();
      for (ShapeAnimation sa : model.getShapeAnimations()) {
        res.add(sa.getShapeAtTick(tick));
      }
      panel.setShapes(res);
      this.tick = tick;
      this.refresh();
    }
  }
}