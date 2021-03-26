package ui;


import exceptions.InvalidActionSelection;
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
import java.util.Scanner;

/**
 * Root Menu and base JFrame for graphical user interface for the Mission Mindful app.
 */
public class RootMenu extends JFrame implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    public static final String CHOOSE = "choose";
    public static final String ADD = "add";

    private JFrame frame;
    protected JButton chooseButton;
    protected JButton viewButton;
    protected JButton addButton;
    protected JButton saveButton;
    protected JButton loadButton;
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
        frame.setLayout(new GridLayout(5,1));
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
    }

    // EFFECTS: Handles buttonEvent and brings user to next screen based on button clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chooseButton) {
            displayExerciseMenu(CHOOSE);
        } else if (e.getSource() == viewButton) {
            removeButtonsFromRootMenu();
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
        removeButtonsFromRootMenu();
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
            System.out.println("Saved current mindfulness program to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads exerciseList (mindfulness program) from file
    private void loadExerciseList() {
        try {
            //ExerciseList loadedExerciseList;
            this.exerciseList = jsonReader.read();
            removeButtonsFromRootMenu();
            new RootMenu(frame, exerciseList);
            System.out.println("Loaded current mindfulness program from  " + JSON_STORE);

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
