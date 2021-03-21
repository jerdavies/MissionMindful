package ui;

import model.Exercise;
import model.ExerciseList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

import java.awt.event.ActionListener;

public class CompletedExerciseList implements ActionListener {
    private JFrame frame;
    private JPanel northPane;
    private JPanel centerPane;
    private JPanel southPane;
    private JLabel northTextLabel;
    protected JButton mainMenuButton;
    private ExerciseList exerciseList;

    // REQUIRES: exerciseType is one of: DEFAULT_EX_TYPE_1, DEFAULT_EX_TYPE_2, DEFAULT_EX_TYPE_3, DEFAULT_EX_TYPE_4
    public CompletedExerciseList(JFrame frame, ExerciseList exerciseList) {
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

        configureCenterpane();
        frame.add(centerPane, BorderLayout.CENTER);

        configureSouthPane();
        frame.add(southPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void configureNorthPane() {
        northPane = new JPanel();
        northTextLabel = new JLabel();

        northTextLabel.setText("Below is the list of completed mindfulness exercises: ");
        northTextLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        northPane.add(northTextLabel);
    }

    private void configureCenterpane() {
        centerPane = new JPanel();

        String[] columnNames = {"Type", "Description"};

        String[][] data = exerciseList.getCompletedExerciseArray();
        JTable table;
        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(700, 500));
        table.setFillsViewportHeight(true);


        JScrollPane scrollPane = new JScrollPane(table);

//        scrollpane.add("TEST", null);

        centerPane.add(scrollPane);
    }

    private void configureSouthPane() {
        southPane = new JPanel();
        mainMenuButton = new JButton("Return to main menu");

        mainMenuButton.setFont(new Font("Dialog", Font.BOLD, 20));
        southPane.add(mainMenuButton);
    }

    // MODIFIES: this
    // EFFECTS:  listens for button actions
    private void initializeInteraction() {
        mainMenuButton.addActionListener(this);
    }


    // EFFECTS: Handles buttonEvent and brings user to next screen based on button clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenuButton) {
            removePanes();
            new RootMenu(frame, exerciseList);
        }
    }

    private void removePanes() {
        frame.remove(northPane);
        frame.remove(centerPane);
        frame.remove(southPane);
    }
}
