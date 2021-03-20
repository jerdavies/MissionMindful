package ui;

import model.ExerciseList;

public class Main {
    public static void main(String[] args) {
        new RootMenu(new ExerciseList(false));
    }
}
