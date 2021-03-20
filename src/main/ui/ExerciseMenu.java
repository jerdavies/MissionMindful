package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import static model.ExerciseList.*;
import static model.ExerciseList.DEFAULT_EX_TYPE_4;

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

    public ExerciseMenu(JFrame frame) {
        this.frame = frame;
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
    // EFFECTS:  listens for button actions
    private void initializeInteraction() {
        for (JButton b : exerciseMenuButtons) {
            b.addActionListener(this);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Adds exercise menu buttons to the frame
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
        if (e.getSource() == actButton) {
            removeButtonsFromExerciseMenu();
            new ExercisePresenter(this.frame, DEFAULT_EX_TYPE_1, "Test description");
            System.out.println("act");

        } else if (e.getSource() == breatheButton) {
            System.out.println("breathe");
        } else if (e.getSource() == noticeButton) {
            System.out.println("notice");
        } else if (e.getSource() == relaxButton) {
            System.out.println("relax");
        }
    }
}
