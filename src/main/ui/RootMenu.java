package ui;


import model.ExerciseList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Root Menu and base JFrame for graphical user interface for the Mission Mindful app.
 */
public class RootMenu extends JFrame implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private JFrame frame;
    protected JButton chooseButton;
    protected JButton viewButton;
    protected JButton addButton;
    protected JButton saveButton;
    protected JButton loadButton;
    protected JButton exitButton;
    private List<JButton> rootMenuButtons;
    ExerciseList exerciseList;

    public RootMenu(ExerciseList exerciseList) {
        this.exerciseList = exerciseList;
        initializeGraphics();
        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where the root menu will display
    private void initializeGraphics() {
        frame = new JFrame("Mission Mindful");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(6,1));
        addButtonsToMenu();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  listens for button actions
    private void initializeInteraction() {
        for (JButton b : rootMenuButtons) {
            b.addActionListener(this);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Adds root menu buttons to the frame
    public void addButtonsToMenu() {
        chooseButton = new JButton("Choose a mindfulness exercise");
        viewButton = new JButton("View completed exercises");
        addButton = new JButton("Add my own exercise");
        saveButton = new JButton("Save mindfulness program to file");
        loadButton = new JButton("Load mindfulness program from file");
        exitButton = new JButton("Exit");

        makeRootMenuButtonList();

        for (JButton b : rootMenuButtons) {
            b.setFont(new Font("Dialog", Font.BOLD, 20));
            frame.add(b);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Remove root menu buttons from the frame
    public void removeButtonsFromRootMenu() {
        for (JButton b : rootMenuButtons) {
            frame.remove(b);
        }
    }

    // MODIFIES: this
    // EFFECTS:  Adds root menu button to form a collection of buttons
    public void makeRootMenuButtonList() {
        rootMenuButtons = new LinkedList<JButton>();
        rootMenuButtons.add(this.chooseButton);
        rootMenuButtons.add(this.viewButton);
        rootMenuButtons.add(this.addButton);
        rootMenuButtons.add(this.saveButton);
        rootMenuButtons.add(this.loadButton);
        rootMenuButtons.add(this.exitButton);
    }

    // EFFECTS: Handles buttonEvent and brings user to next screen based on button clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chooseButton) {
            System.out.println("b1 was pressed");
            removeButtonsFromRootMenu();
            new ExerciseMenu(this.frame, exerciseList);
        } else if (e.getSource() == viewButton) {
            System.out.println("b2 was pressed"); // !!!
        } else if (e.getSource() == addButton) {
            System.out.println("b3 was pressed");
        } else if (e.getSource() == saveButton) {
            System.out.println("b4 was pressed");
        } else if (e.getSource() == loadButton) {
            System.out.println("b5 was pressed");
        } else if (e.getSource() == exitButton) {
            System.out.println("b6 was pressed");
        }
    }
}
