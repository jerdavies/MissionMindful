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

    // MODIFIES: this
    // EFFECTS: creates pane in North border position and
    // adds text indicating that the list of completed exercises is below
    private void configureNorthPane() {
        northPane = new JPanel();
        northTextLabel = new JLabel();

        northTextLabel.setText("Below is the list of completed mindfulness exercises: ");
        northTextLabel.setFont(new Font("Dialog", Font.BOLD, 20));

        northPane.add(northTextLabel);
    }

    // MODIFIES: this
    // EFFECTS: creates pane in Center layout position and
    // adds table of completed exercises
    private void configureCenterpane() {
        centerPane = new JPanel();

        JTable table = createjTable();

        JScrollPane scrollPane = new JScrollPane(table);
        centerPane.add(scrollPane);
    }

    // EFFECTS: creates table of completed exercises list
    private JTable createjTable() {
        String[] columnNames = {"Type", "Description"};
        String[][] data = exerciseList.getCompletedExerciseArray();
        JTable table;
        table = new JTable(data, columnNames);
        table.getColumnModel().getColumn(1).setPreferredWidth(550);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(32, 136, 203));
        table.getTableHeader().setForeground(new Color(255, 255, 255));
        table.setPreferredScrollableViewportSize(new Dimension(800, 500));
        table.setRowHeight(40);
        table.setFillsViewportHeight(true);
        return table;
    }

    // MODIFIES: this
    // EFFECTS: creates pane in South border position and
    // adds button to return to Main (Root) Menu
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


    // MODIFIES: this
    // EFFECTS: Handles buttonEvent and brings user to next screen based on button clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenuButton) {
            removePanes();
            new RootMenu(frame, exerciseList);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes north, center, south panes from this.frame
    private void removePanes() {
        frame.remove(northPane);
        frame.remove(centerPane);
        frame.remove(southPane);
    }
}
