package ui;

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

    private JFrame frame;
    private JPanel topPane;
    private JPanel bottomPane;
    private JPanel southBottomPane;
    protected JButton markCompleteButton;
    protected JButton noButton;
    protected JButton notice;
    protected JButton relax;
    private List<JButton> exercisePresenterButtons;

    // REQUIRES: exerciseType is one of: DEFAULT_EX_TYPE_1, DEFAULT_EX_TYPE_2, DEFAULT_EX_TYPE_3, DEFAULT_EX_TYPE_4
    public ExercisePresenter(JFrame frame, String exerciseType, String exerciseDescription) {
        this.frame = frame;
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
        topPane = new JPanel();

        topPane.setLayout(new BorderLayout());
        Image myPicture = ImageIO.read(new File("./data/Action.jpg"));
        JLabel picture = new JLabel(new ImageIcon(myPicture));
        JLabel exerciseTypeText = new JLabel();
        JLabel exerciseDescriptionText = new JLabel();

        exerciseTypeText.setText("HARD CODED - Act");
        exerciseTypeText.setFont(new Font("SansSerif", Font.BOLD, 22));
        exerciseTypeText.setHorizontalAlignment(JTextField.CENTER);

        topPane.add(exerciseTypeText, BorderLayout.NORTH);
        topPane.add(picture, BorderLayout.CENTER);
        frame.add(topPane);

        configureBottomPane(exerciseDescriptionText);
        frame.add(bottomPane);
        frame.setVisible(true);
    }

    private void configureBottomPane(JLabel exerciseDescriptionText) {
        bottomPane = new JPanel();
        bottomPane.setLayout(new GridLayout(2,1));
        exerciseDescriptionText.setText("HARD CODED - Take 10 breaths each time you feel worry");
        exerciseDescriptionText.setFont(new Font("SansSerif", Font.ITALIC, 22));
        exerciseDescriptionText.setHorizontalAlignment(JTextField.CENTER);
        bottomPane.add(exerciseDescriptionText);

        southBottomPane = new JPanel();
        southBottomPane.setLayout(new GridBagLayout());

        addButtonsToSouthBottomPane();

        bottomPane.add(southBottomPane);
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
