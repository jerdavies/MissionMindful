package ui;

import model.Exercise;
import model.ExerciseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static model.ExerciseList.*;

/**
 * User interface for the Mission Mindful app.
 * Citation: Large portions of code taken and modified from ui package in CPSC210 TellerApp.
 */
public class MindfulnessApp {
    private static final String JSON_STORE = "./data/exerciseList.json";
    private ExerciseList exerciseList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the mindfulness application
    public MindfulnessApp() throws FileNotFoundException {
        runMissionMindful();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMissionMindful() throws FileNotFoundException {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes mindful exercise list, input scanner, and json read/writer
    private void init() throws FileNotFoundException {
        exerciseList = new ExerciseList(false);
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays main menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> Choose a mindfulness exercise");
        System.out.println("\tv -> View completed exercises");
        System.out.println("\ta -> Add my own exercise");
        System.out.println("\ts -> Save mindfulness program to file");
        System.out.println("\tl -> Load mindfulness program from file");
        System.out.println("\te -> Exit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            doExercise();
        } else if (command.equals("v")) {
            printExercises();
        } else if (command.equals("a")) {
            addExercise();
        } else if (command.equals("s")) {
            saveExerciseList();
        } else if (command.equals("l")) {
            loadExerciseList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new exercise of the specified type to the collection of exercises
    private void addExercise() {
        System.out.println("\nWhich type of exercise would you like to add?");
        String type = selectExerciseType();

        System.out.print("\nEnter short description of the " + type + " exercise to add: \n");
        String exerciseDescription = input.nextLine();
        exerciseDescription = input.nextLine(); //if this is not here the input prompt will be skipped

        exerciseList.addExercise(type, exerciseDescription, false);
        System.out.println("\nExercise successfully added!");

        selectNextAction();
    }

    // MODIFIES: this
    // EFFECTS: prompts user with a mindfulness exercise and allows user to mark the exercise complete
    private void doExercise() {
        Exercise exercise;
        String type;
        String description;

        System.out.println("\nWhich type of mindfulness exercise would you like to do?");
        type = selectExerciseType();

        exercise = this.exerciseList.getNextExercise(type);

        if (exercise == null) {
            System.out.println("\nNo more " + type + " exercises left. If you wish, add a new one from the"
                    + " main menu.");
        } else {
            description = exercise.getDescription();
            System.out.println("\nHere is your mindfulness exercise. Enjoy:\n" + description);
            markExerciseComplete(exercise);
        }
        selectNextAction();
    }

    // EFFECTS: prompts user to select a mindfulness category (type)
    private String selectExerciseType() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("a") || selection.equals("b") || selection.equals("n") || selection.equals("r"))) {
            System.out.println("\ta -> " + DEFAULT_EX_TYPE_1);
            System.out.println("\tb -> " + DEFAULT_EX_TYPE_2);
            System.out.println("\tn -> " + DEFAULT_EX_TYPE_3);
            System.out.println("\tr -> " + DEFAULT_EX_TYPE_4);
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("a")) {
            return DEFAULT_EX_TYPE_1;
        } else if (selection.equals("b")) {
            return DEFAULT_EX_TYPE_2;
        } else if (selection.equals("n")) {
            return DEFAULT_EX_TYPE_3;
        } else {
            return DEFAULT_EX_TYPE_4;
        }
    }

    // EFFECTS: prompts user to select to return the main menu or exit the app
    private void selectNextAction() {
        System.out.println("\nWhere would you like to go next?:");
        String selection = "";  // force entry into loop

        while (!(selection.equals("m") || selection.equals("e"))) {
            System.out.println("\tm -> " + "return to Main Menu");
            System.out.println("\te -> " + "Exit the app");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("m")) {
            return;
        } else if (selection.equals("e")) {
            System.out.println("\nGoodbye!");
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts and allows user if they want to mark an exercise as complete
    private void markExerciseComplete(Exercise exercise) {
        System.out.println("\nWhen you are ready, you can mark the exercise as complete:");
        String selection = "";

        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("\ty -> " + "Yes, mark exercise as completed");
            System.out.println("\tn -> " + "No, I will complete the exercise another time");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("y")) {
            exercise.markExerciseComplete();
            String congratsMsg = exercise.congratulateUser();
            System.out.println("\nExercise marked as complete. " + congratsMsg);
        }

        if (selection.equals("n")) {
            System.out.println("\nNo problem - you can do it next time.");
        }
    }

    // EFFECTS: prints a list of the completed exercise type:description pairs
    public void printExercises() {
        System.out.println("\nBelow is a list of completed exercises so far:");
        System.out.println(exerciseList.getCompletedExercisesAsString());
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
            exerciseList = jsonReader.read();
            System.out.println("Loaded current mindfulness program from  " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
