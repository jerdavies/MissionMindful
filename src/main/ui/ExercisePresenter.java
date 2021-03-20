package ui;

import model.Exercise;
import model.ExerciseList;
import static model.ExerciseList.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * GUI page/frame that presents the current mindfulness exercise to the user and handles the user inputted
 * completion status.
 */
public class ExercisePresenter implements ActionListener {

    public static final String IMAGEFILEPATH = "./data/";

    private JFrame frame;
    private JPanel topPane;
    private JPanel bottomPane;
    private JPanel southBottomPane;
    protected JButton markCompleteButton;
    protected JButton noButton;
    protected JButton notice;
    protected JButton relax;
    private JLabel exerciseTypeText;
    private JLabel exerciseDescriptionText;
    private JLabel picture;
    private List<JButton> exercisePresenterButtons;
    private Exercise exercise;

    // REQUIRES: exerciseType is one of: DEFAULT_EX_TYPE_1, DEFAULT_EX_TYPE_2, DEFAULT_EX_TYPE_3, DEFAULT_EX_TYPE_4
    public ExercisePresenter(JFrame frame, Exercise exercise) {
        this.frame = frame;
        this.exercise = exercise;
        try {
            initializeGraphics();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS:  edits the frame to display the exercise menu
    private void initializeGraphics() throws IOException {
        frame.setLayout(new GridLayout(2,1));

        configureTopPane();
        frame.add(topPane);

        configureBottomPane();
        frame.add(bottomPane);

        frame.setVisible(true);
    }

    private void configureTopPane() throws IOException {
        topPane = new JPanel();
        topPane.setLayout(new BorderLayout());

        customizeExerciseVisualOutput();

        topPane.add(exerciseTypeText, BorderLayout.NORTH);
        topPane.add(picture, BorderLayout.CENTER);
    }

    private void customizeExerciseVisualOutput() throws IOException {
        Image screenImage = null;
        screenImage = getImage();
        screenImage = screenImage.getScaledInstance(550, 300, Image.SCALE_DEFAULT);
        this.picture = new JLabel(new ImageIcon(screenImage));
        customizeDescriptionText();
    }

    private Image getImage() throws IOException {
        Image screenImage = null;
        if (this.exercise.getType() == DEFAULT_EX_TYPE_1) {
            screenImage = ImageIO.read(new File(IMAGEFILEPATH + "Act.jpg"));
        } else if (this.exercise.getType() == DEFAULT_EX_TYPE_2) {
            screenImage = ImageIO.read(new File(IMAGEFILEPATH + "Breathe.jpg"));
        } else if (this.exercise.getType() == DEFAULT_EX_TYPE_3) {
            screenImage = ImageIO.read(new File(IMAGEFILEPATH + "Notice.jpg"));
        } else if (this.exercise.getType() == DEFAULT_EX_TYPE_4) {
            screenImage = ImageIO.read(new File(IMAGEFILEPATH + "Relax.jpg"));
        }
        return screenImage;
    }

    private void customizeDescriptionText() {
        this.exerciseTypeText = new JLabel();
        exerciseTypeText.setText(exercise.getType());
        exerciseTypeText.setFont(new Font("SansSerif", Font.BOLD, 22));
        exerciseTypeText.setHorizontalAlignment(JTextField.CENTER);
    }

    private void configureBottomPane() {
        bottomPane = new JPanel();
        this.exerciseDescriptionText = new JLabel();

        bottomPane.setLayout(new GridLayout(2,1));
        formatExerciseDescriptionText();
        bottomPane.add(exerciseDescriptionText);

        southBottomPane = new JPanel();
        southBottomPane.setLayout(new GridBagLayout());
        addButtonsToSouthBottomPane();

        bottomPane.add(southBottomPane);
    }

    private void formatExerciseDescriptionText() {
        exerciseDescriptionText.setText(exercise.getDescription());
        exerciseDescriptionText.setFont(new Font("SansSerif", Font.ITALIC, 22));
        exerciseDescriptionText.setHorizontalAlignment(JTextField.CENTER);
    }

    // MODIFIES: this
    // EFFECTS:  listens for button actions
    private void initializeInteraction() {
        for (JButton b : exercisePresenterButtons) {
            b.addActionListener(this);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Add Y/N buttons to the panel to allow user to mark exercise as complete
    public void addButtonsToSouthBottomPane() {
        markCompleteButton = new JButton("Mark exercise as complete");

        makeExercisePresenterButtonList();

        for (JButton b : exercisePresenterButtons) {
            southBottomPane.add(b);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Creates collection of all ExercisePresenter frame bottom pane buttons
    public void makeExercisePresenterButtonList() {
        exercisePresenterButtons = new LinkedList<JButton>();
        exercisePresenterButtons.add(this.markCompleteButton);
    }

    // EFFECTS: Handles buttonEvent and brings user to next screen based on button clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == markCompleteButton) {
            System.out.println("act");

        } else if (e.getSource() == noButton) {
            System.out.println("breathe");
        } else if (e.getSource() == notice) {
            System.out.println("notice");
        } else if (e.getSource() == relax) {
            System.out.println("relax");
        }
    }
}
