package ui;

import model.Exercise;
import model.ExerciseList;

import java.util.Scanner;

import static model.ExerciseList.*;

/**
 * User interface for the Mission Mindful app.
 */
public class MindfulnessApp {
    private ExerciseList exerciseList;
    private Scanner input;

    // EFFECTS: runs the mindfulness application
    public MindfulnessApp() {
        runMissionMindful();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMissionMindful() {
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
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            doExercise();
        } else if (command.equals("v")) {
            printExercises();
        } else if (command.equals("a")) {
            addExercise();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes mindful exercise list
    private void init() {
        exerciseList = new ExerciseList();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> Choose a mindfulness exercise");
        System.out.println("\tv -> View completed exercises");
        System.out.println("\ta -> Add my own exercise");
        System.out.println("\te -> Exit");
    }

    // MODIFIES: this
    // EFFECTS: adds a new exercise of the specified type to the collection of exercises
    private void addExercise() {
        System.out.println("\nWhich type of exercise would you like to add?");
        String type = selectExerciseType();

        System.out.print("\nEnter short description of the " + type + " exercise to add: \n");
        String exerciseDescription = input.nextLine();
        exerciseDescription = input.nextLine(); //if this is not here the input prompt will be skipped

        exerciseList.addExercise(exerciseDescription, type);
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
            System.out.println("\nExercise marked as complete. Pat yourself on the back!");
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
}
