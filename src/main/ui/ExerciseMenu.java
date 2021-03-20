package ui;

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
    protected JButton actButton;
    protected JButton breatheButton;
    protected JButton noticeButton;
    protected JButton relaxButton;
    private List<JButton> exerciseMenuButtons;
    private ExerciseList exerciseList;

    public ExerciseMenu(JFrame frame, ExerciseList exerciseList) {
        this.frame = frame;
        this.exerciseList = exerciseList;

        initializeGraphics();
        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS:  edits the frame to display the exercise menu
    private void initializeGraphics() {
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // May not be necessary. Holding here until certain
        //frame.setLocationRelativeTo(null);   // May not be necessary. Holding here until certain
        frame.setLayout(new GridLayout(2,2));
        addButtonsToExerciseMenu();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  adds listening capabilities for button actions
    private void initializeInteraction() {
        for (JButton b : exerciseMenuButtons) {
            b.addActionListener(this);
        }
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
            frame.add(b);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Remove exercise menu buttons from the frame
    public void removeButtonsFromExerciseMenu() {
        for (JButton b : exerciseMenuButtons) {
            frame.remove(b);
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

        if (!type.equals("")) {
            removeButtonsFromExerciseMenu();
            presentExerciseToUser(type);
        }
    }

    // REQUIRES: exerciseType is one of: DEFAULT_EX_TYPE_1, DEFAULT_EX_TYPE_2, DEFAULT_EX_TYPE_3, DEFAULT_EX_TYPE_4
    // MODIFIES: this
    // EFFECTS: If there are still exercises to complete for the given type, present user with exercise of given type;
    //          otherwise, flag to user that there are no more exercises left of the selected type
    public void presentExerciseToUser(String type) {
        Exercise exercise;

        exercise = this.exerciseList.getNextExercise(type);

        if (exercise == null) {
            System.out.println("No more exercises left.  !!! Jeremy needs to make a user pop-up message");
        } else {
            new ExercisePresenter(this.frame, exercise);
        }
    }
}
