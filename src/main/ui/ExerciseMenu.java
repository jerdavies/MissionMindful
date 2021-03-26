package ui;

import exceptions.EmptyFieldsException;
import exceptions.InvalidActionSelection;
import model.Exercise;
import model.ExerciseList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import static model.ExerciseList.*;

/**
 * GUI menu page to select which mindfulness exercise to perform or add.
 */
public class ExerciseMenu implements ActionListener {
    private JFrame frame;
    private JPanel northPane;
    private JPanel centrePane;
    private JPanel southPane;
    protected JButton actButton;
    protected JButton breatheButton;
    protected JButton noticeButton;
    protected JButton relaxButton;
    protected JButton mainMenuButton;
    private JLabel southTextLabel;
    private List<JButton> exerciseMenuButtons;
    private ExerciseList exerciseList;
    private String menuPurpose;

    public ExerciseMenu(JFrame frame, ExerciseList exerciseList, String menuPurpose) throws InvalidActionSelection {
        if (!(menuPurpose.equals(RootMenu.CHOOSE) || menuPurpose.equals(RootMenu.ADD))) {
            throw new InvalidActionSelection();
        }
        this.menuPurpose = menuPurpose;
        this.frame = frame;
        this.exerciseList = exerciseList;

        initializeGraphics();
        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS:  edits the frame to display the exercise menu
    private void initializeGraphics() {
        frame.setLayout(new BorderLayout());

        configureNorthPane();
        frame.add(northPane, BorderLayout.NORTH);
        configureCentrePane();
        frame.add(centrePane, BorderLayout.CENTER);
        configureSouthPane();
        frame.add(southPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  adds listening capabilities for button actions
    private void initializeInteraction() {
        for (JButton b : exerciseMenuButtons) {
            b.addActionListener(this);
        }
        mainMenuButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: Creates JPanel located in NORTH position
    private void configureNorthPane() {
        northPane = new JPanel();
        northPane.add(createNorthTextLabel(), BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: Creates JPanel located in CENTER position
    private void configureCentrePane() {
        centrePane = new JPanel();
        centrePane.setLayout(new GridLayout(2,2));
        addButtonsToExerciseMenu();
    }

    // MODIFIES: this
    // EFFECTS: Creates JPanel located in SOUTH position
    private void configureSouthPane() {
        southPane = new JPanel();
        southPane.setLayout(new GridLayout(2,1));

        mainMenuButton = new JButton("Return to main menu");
        mainMenuButton.setFont(new Font("Dialog", Font.BOLD, 20));
        southPane.add(mainMenuButton);
        southPane.add(createSouthTextLabel());
    }

    // EFFECTS: returns the customized JLabel with the text to display at the top of the exercise menu
    private JLabel createNorthTextLabel() {
        JLabel northTextLabel = new JLabel();

        northTextLabel.setText(setNorthText());
        northTextLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        northTextLabel.setHorizontalAlignment(JTextField.CENTER);

        return northTextLabel;
    }

    // EFFECTS: returns the text to display at the top of exercise menu
    private String setNorthText() {
        String northText = "";
        if (this.menuPurpose == RootMenu.ADD) {
            northText = "Select the type of exercise you'd like to add:";
        } else if (this.menuPurpose == RootMenu.CHOOSE) {
            northText = "Select the type of exercise you'd like to do:";
        }
        return northText;
    }

    // EFFECTS: returns the customized JLabel with the text to display at the bottom of the exercise menu
    private JLabel createSouthTextLabel() {
        southTextLabel = new JLabel();

        southTextLabel.setText("");
        southTextLabel.setFont(new Font("Dialog", Font.ITALIC, 20));
        southTextLabel.setHorizontalAlignment(JTextField.CENTER);

        return southTextLabel;
    }

    // MODIFIES: this
    // EFFECTS:  adds exercise menu buttons to the frame
    public void addButtonsToExerciseMenu() {
        actButton = new JButton(DEFAULT_EX_TYPE_1);
        breatheButton = new JButton(DEFAULT_EX_TYPE_2);
        noticeButton = new JButton(DEFAULT_EX_TYPE_3);
        relaxButton = new JButton(DEFAULT_EX_TYPE_4);

        makeExerciseMenuButtonList();

        for (JButton b : exerciseMenuButtons) {
            b.setFont(new Font("Dialog", Font.BOLD, 20));
            centrePane.add(b);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Remove exercise menu buttons from the frame
    public void removeButtonsFromExerciseMenu() {
        for (JButton b : exerciseMenuButtons) {
            centrePane.remove(b);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Creates collection of all exercise menu buttons
    public void makeExerciseMenuButtonList() {
        exerciseMenuButtons = new LinkedList<JButton>();
        exerciseMenuButtons.add(this.actButton);
        exerciseMenuButtons.add(this.breatheButton);
        exerciseMenuButtons.add(this.noticeButton);
        exerciseMenuButtons.add(this.relaxButton);
    }

    // EFFECTS: Handles buttonEvent and brings user to next screen based on button clicked
    //          If mainMenuButton clicked, go to RootMenu
    //          Else if menuPurpose is CHOOSE, chooseNextExercise of selected type
    //          Else if menu purpose is not empty string, allow user to add custom exercise of selected type
    @Override
    public void actionPerformed(ActionEvent e) {
        String type = "";

        if (e.getSource() == mainMenuButton) {
            removePanes();
            new RootMenu(frame, exerciseList);
        } else {
            type = getChosenTypeAsString(e, type);

            if (menuPurpose == RootMenu.CHOOSE) {
                chooseNextExercise(type);
            } else if (!type.equals("")) {
                try {
                    addCustomExercise(type);
                } catch (EmptyFieldsException emptyFieldsException) {
                    southTextLabel.setText("Add operation cancelled. No exercise added");
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to type in a description and adds the exercise to exerciseList, plus prints add confirmation
    private void addCustomExercise(String type) throws EmptyFieldsException {
        String s = (String)JOptionPane.showInputDialog(frame, "Enter an exercise description:\n",
                "Add New " + type + " Exercise", JOptionPane.PLAIN_MESSAGE,
                null, null, "Type here");

        if (s == null || (s != null && ("".equals(s)))) {
            throw new EmptyFieldsException();
        } else {
            exerciseList.addExercise(type, s, false);
            southTextLabel.setText(type + " exercise was successfully added!");
        }
    }

    // EFFECTS: returns the type of the chosen exercise as a string
    private String getChosenTypeAsString(ActionEvent e, String type) {
        if (e.getSource() == actButton) {
            type = DEFAULT_EX_TYPE_1;
        } else if (e.getSource() == breatheButton) {
            type = DEFAULT_EX_TYPE_2;
        } else if (e.getSource() == noticeButton) {
            type = DEFAULT_EX_TYPE_3;
        } else if (e.getSource() == relaxButton) {
            type = DEFAULT_EX_TYPE_4;
        }
        return type;
    }

    // EFFECTS: Helper method to set up screen before the exercise prompt is displayed
    private void chooseNextExercise(String type) {
        if (!type.equals("")) {
            removePanes();
            try {
                presentExerciseToUser(type);
            } catch (InvalidActionSelection invalidActionSelection) {
                invalidActionSelection.printStackTrace();
            }
        }
    }

    // REQUIRES: exerciseType is one of: DEFAULT_EX_TYPE_1, DEFAULT_EX_TYPE_2, DEFAULT_EX_TYPE_3, DEFAULT_EX_TYPE_4
    // MODIFIES: this
    // EFFECTS: If there are still exercises to complete for the given type, present user with exercise of given type;
    //          otherwise, flag to user that there are no more exercises left of the selected type
    public void presentExerciseToUser(String type) throws InvalidActionSelection {
        Exercise exercise;

        exercise = this.exerciseList.getNextExercise(type);

        if (exercise == null) {
            displayNoExercisesDialog(type);
            new ExerciseMenu(frame, exerciseList, RootMenu.CHOOSE);
        } else {
            new ExercisePresenter(this.frame, exercise, this.exerciseList);
        }
    }

    // EFFECTS: Displays pop-up dialog to user telling them
    // there are currently no more exercises of selected type left
    private void displayNoExercisesDialog(String type) {
        JLabel noExercisesLeftLabel = new JLabel("\nNo more " + type + " exercises left. "
                + "If you wish, add a new one from the" + " main menu.");
        noExercisesLeftLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        JOptionPane.showMessageDialog(frame, noExercisesLeftLabel);
    }

    // MODIFIES: this
    // EFFECTS: removes the ExerciseMenu panels before displaying a different screen
    private void removePanes() {
        frame.remove(northPane);
        frame.remove(centrePane);
        frame.remove(southPane);
    }
}
