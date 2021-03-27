package ui;


import ui.exceptions.InvalidActionSelection;
import model.ExerciseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Root Menu and base JFrame for graphical user interface for the Mission Mindful app.
 */
public class RootMenu extends JFrame implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static final String CHOOSE = "choose";
    public static final String ADD = "add";

    private JFrame frame;
    private JPanel northPane;
    private JPanel centrePane;
    private JPanel southPane;
    protected JButton chooseButton;
    protected JButton viewButton;
    protected JButton addButton;
    protected JButton saveButton;
    protected JButton loadButton;
    private JLabel southTextLabel;
    private List<JButton> rootMenuButtons;
    ExerciseList exerciseList;
    private static final String JSON_STORE = "./data/exerciseList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Constructor for brand new App called from Main
    public RootMenu(ExerciseList exerciseList) {
        this.exerciseList = exerciseList;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeBaseFrame();
        initializeInteraction();
    }

    // Constructor for resetting the app to the RootMenu
    public RootMenu(JFrame frame, ExerciseList exerciseList) {
        this.frame = frame;
        this.exerciseList = exerciseList;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeGraphics();
        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS:  constructs the JFrame window where the root menu will display
    private void initializeBaseFrame() {
        frame = new JFrame("Mission Mindful");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS:  sets the frame layout and adds the root menu buttons
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
    // EFFECTS:  listens for button actions
    private void initializeInteraction() {
        for (JButton b : rootMenuButtons) {
            b.addActionListener(this);
        }
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
        centrePane.setLayout(new GridLayout(5,1));
        addButtonsToMenu();
    }

    // MODIFIES: this
    // EFFECTS: Creates JPanel located in SOUTH position
    private void configureSouthPane() {
        southPane = new JPanel();
        southTextLabel = new JLabel();

        southTextLabel.setText("");
        southTextLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        southTextLabel.setHorizontalAlignment(JTextField.CENTER);
        southPane.add(southTextLabel);
    }

    // MODIFIES: this
    // EFFECTS:  Adds root menu buttons to the frame
    public void addButtonsToMenu() {
        chooseButton = new JButton("Choose a mindfulness exercise");
        viewButton = new JButton("View completed exercises");
        addButton = new JButton("Add my own exercise");
        saveButton = new JButton("Save mindfulness program to file");
        loadButton = new JButton("Load mindfulness program from file");

        makeRootMenuButtonList();

        for (JButton b : rootMenuButtons) {
            b.setFont(new Font("Dialog", Font.BOLD, 20));
            centrePane.add(b);
        }
    }

    // EFFECTS: returns the customized JLabel with the text to display at the top of the menu
    private JLabel createNorthTextLabel() {
        JLabel northTextLabel = new JLabel();

        northTextLabel.setText("Welcome! Select from the menu below:");
        northTextLabel.setFont(new Font("Dialog", Font.ITALIC, 20));
        northTextLabel.setHorizontalAlignment(JTextField.CENTER);

        return northTextLabel;
    }

    // MODIFIES: this
    // EFFECTS:  Remove 3 panes from the frame
    public void removePanesFromRootMenu() {
        frame.remove(northPane);
        frame.remove(centrePane);
        frame.remove(southPane);
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
    }

    // EFFECTS: Handles buttonEvent and brings user to next screen based on button clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chooseButton) {
            displayExerciseMenu(CHOOSE);
        } else if (e.getSource() == viewButton) {
            removePanesFromRootMenu();
            new CompletedExerciseList(frame, exerciseList);
        } else if (e.getSource() == addButton) {
            displayExerciseMenu(ADD);
        } else if (e.getSource() == saveButton) {
            saveExerciseList();
        } else if (e.getSource() == loadButton) {
            loadExerciseList();
        }
    }

    // MODIFIES: this
    // EFFECTS: create new exercise type menu so user can choose which exercise type to do
    private void displayExerciseMenu(String menuPurpose) {
        removePanesFromRootMenu();
        try {
            new ExerciseMenu(this.frame, exerciseList, menuPurpose);
        } catch (InvalidActionSelection invalidActionSelection) {
            invalidActionSelection.printStackTrace();
        }
    }

    // EFFECTS: saves the exerciseList (mindfulness program) to file
    private void saveExerciseList() {
        try {
            jsonWriter.open();
            jsonWriter.write(exerciseList);
            jsonWriter.close();
            southTextLabel.setText("Saved current mindfulness program to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads exerciseList (mindfulness program) from file
    private void loadExerciseList() {
        try {
            this.exerciseList = jsonReader.read();
            southTextLabel.setText("Loaded current mindfulness program from " + JSON_STORE);
            System.out.println("Loaded current mindfulness program from " + JSON_STORE);

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
