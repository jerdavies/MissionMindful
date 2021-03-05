package ui;

import model.ExerciseList;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new MindfulnessApp();
        } catch (FileNotFoundException e) {
            System.out.println("Ooops. Unable to run the app file not found");
        }
    }
}
