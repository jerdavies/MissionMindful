package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import static model.ExerciseList.*;
import static model.ExerciseList.DEFAULT_EX_TYPE_4;

public class ExerciseMenu implements ActionListener {

    private JFrame frame;
    protected JButton act;
    protected JButton breathe;
    protected JButton notice;
    protected JButton relax;
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
        addButtonsToExerciseMenu(frame);
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
    public void addButtonsToExerciseMenu(JFrame frame) {
        act = new JButton(DEFAULT_EX_TYPE_1);
        breathe = new JButton(DEFAULT_EX_TYPE_2);
        notice = new JButton(DEFAULT_EX_TYPE_3);
        relax = new JButton(DEFAULT_EX_TYPE_4);

        makeExerciseMenuButtonList();

        for (JButton b : exerciseMenuButtons) {
            frame.add(b);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Creates collection of all exercise menu buttons
    public void makeExerciseMenuButtonList() {
        exerciseMenuButtons = new LinkedList<JButton>();
        exerciseMenuButtons.add(this.act);
        exerciseMenuButtons.add(this.breathe);
        exerciseMenuButtons.add(this.notice);
        exerciseMenuButtons.add(this.relax);
    }

    // EFFECTS: Handles buttonEvent and brings user to next screen based on button clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == act) {
            System.out.println("act");
        } else if (e.getSource() == breathe) {
            System.out.println("breathe");
        } else if (e.getSource() == notice) {
            System.out.println("notice");
        } else if (e.getSource() == relax) {
            System.out.println("relax");
        }
    }
}
