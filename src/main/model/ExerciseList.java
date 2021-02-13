package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of a collection of mindfulness exercises.
 *
 * Tasks can be added to the collection with the addExercise method.
 *
 * Exercises are maintained in the order in which they are added. The user starts
 * working on the exercise when the completeExercise method is called. Exercises
 * of the selected type will then be presented on in the
 * order in which they were added to the collection of exercises
 */
public class ExerciseList {
    // constants for the default exercises included with the game
    private static final String DEFAULT_EXERCISE_1 = "Write down 3 things you appreciate from today";
    private static final String DEFAULT_EX_TYPE_1 = "Act";
    private static final String DEFAULT_EXERCISE_2 = "Drink a mindful cup of tea or coffee";
    private static final String DEFAULT_EX_TYPE_2 = "Act";
    private static final String DEFAULT_EXERCISE_3 = "Listen to a favourite piece of music";
    private static final String DEFAULT_EX_TYPE_3 = "Act";
    private static final String DEFAULT_EXERCISE_4 = "Take 20 deep breaths; inhale through nose; exhale with mouth";
    private static final String DEFAULT_EX_TYPE_4 = "Breathe";
    private static final String DEFAULT_EXERCISE_5 = "Every time your phone vibrates today, pause and take one breath";
    private static final String DEFAULT_EX_TYPE_5 = "Breathe";
    private static final String DEFAULT_EXERCISE_6 = "Follow along with a guided meditation on Youtube";
    private static final String DEFAULT_EX_TYPE_6 = "Breathe";

    // fields to represent changing properties of an exercise list
    private List<Exercise> exerciseList;


    // EFFECTS: constructs an collection of the default exercises
    public ExerciseList() {
        this.exerciseList = new ArrayList<Exercise>();
        addExercise(DEFAULT_EXERCISE_1, DEFAULT_EX_TYPE_1);
        addExercise(DEFAULT_EXERCISE_2, DEFAULT_EX_TYPE_2);
        addExercise(DEFAULT_EXERCISE_3, DEFAULT_EX_TYPE_3);
        addExercise(DEFAULT_EXERCISE_4, DEFAULT_EX_TYPE_4);
        addExercise(DEFAULT_EXERCISE_5, DEFAULT_EX_TYPE_5);
        addExercise(DEFAULT_EXERCISE_6, DEFAULT_EX_TYPE_6);
        System.out.println(exerciseList.get(4).getDescription());
    }

    // REQUIRES: exerciseType is one of: "Act", "Breathe", "Notice", "Relax"
    // MODIFIES: this
    // EFFECTS: adds a new exercise to the collection of exercises
    public void addExercise(String exerciseDescription, String exerciseType) {
        Exercise exercise = new Exercise(exerciseDescription, exerciseType);  // !!! Eventually need if here to create diff types of exercises
        this.exerciseList.add(exercise);
    }


}
