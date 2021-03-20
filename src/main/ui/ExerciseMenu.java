package ui;

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

    private void configureNorthPane() {
        northPane = new JPanel();
        northPane.add(createNorthTextLabel(), BorderLayout.NORTH);
    }

    private void configureCentrePane() {
        centrePane = new JPanel();
        centrePane.setLayout(new GridLayout(2,2));
        addButtonsToExerciseMenu();
    }

    private void configureSouthPane() {
        southPane = new JPanel();
        southPane.setLayout(new GridLayout(2,1));

        mainMenuButton = new JButton("Return to main menu");
        mainMenuButton.setFont(new Font("Dialog", Font.BOLD, 20));
        southPane.add(mainMenuButton);
        southPane.add(createSouthTextLabel());
    }

    // EFFECTS: returns the text to display at the top of exercise menu
    private String assignNorthText() {
        String northText = "";
        if (this.menuPurpose == RootMenu.ADD) {
            northText = "Select the type of exercise you'd like to add:";
        } else if (this.menuPurpose == RootMenu.CHOOSE) {
            northText = "Select the type of exercise you'd like to do:";
        }
        return northText;
    }

    // EFFECTS: returns the customized JLabel with the text to display at the top of the exercise menu
    private JLabel createNorthTextLabel() {
        JLabel northTextLabel = new JLabel();

        northTextLabel.setText(assignNorthText());
        northTextLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        northTextLabel.setHorizontalAlignment(JTextField.CENTER);

        return northTextLabel;
    }

    // EFFECTS: returns the text to display at the bottom of exercise menu
    private String assignSouthText() {
        String southText = "";
        southText = "!!! placeholder for now. Jeremy to fix";
        return southText;
    }

    // EFFECTS: returns the customized JLabel with the text to display at the bottom of the exercise menu
    private JLabel createSouthTextLabel() {
        JLabel southTextLabel = new JLabel();

        southTextLabel.setText(assignSouthText());
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
    @Override
    public void actionPerformed(ActionEvent e) {
        String type = "";

        if (e.getSource() == mainMenuButton) {
            System.out.println("!!! go to main menu!");
            removePanes();
            new RootMenu(frame, exerciseList);
        } else {
            type = getChosenTypeAsString(e, type);

            if (menuPurpose == RootMenu.CHOOSE) {
                chooseNextExercise(type);
            } else {
                System.out.println("!!! Jeremy needs to create the add-exercise functionality");
            }
        }
    }

    private String getChosenTypeAsString(ActionEvent e, String type) {
        if (e.getSource() == actButton) {
            System.out.println(DEFAULT_EX_TYPE_1);
            type = DEFAULT_EX_TYPE_1;
        } else if (e.getSource() == breatheButton) {
            System.out.println(DEFAULT_EX_TYPE_2);
            type = DEFAULT_EX_TYPE_2;
        } else if (e.getSource() == noticeButton) {
            System.out.println(DEFAULT_EX_TYPE_3);
            type = DEFAULT_EX_TYPE_3;
        } else if (e.getSource() == relaxButton) {
            System.out.println(DEFAULT_EX_TYPE_4);
            type = DEFAULT_EX_TYPE_4;
        }
        return type;
    }

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

    private void removePanes() {
        frame.remove(northPane);
        frame.remove(centrePane);
        frame.remove(southPane);
    }
}
