package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of a collection of mindfulness exercises.
 * <p>
 * Tasks can be added to the collection with the addExercise method.
 * <p>
 * Exercises are maintained in the order in which they are added. The user starts
 * working on the exercise when the completeExercise method is called. Exercises
 * of the selected type will then be presented on in the
 * order in which they were added to the collection of exercises
 */
public class ExerciseList {
    // constants for the default exercises included with the game
    public static final String DEFAULT_EX_TYPE_1 = "Act";
    public static final String DEFAULT_EX_TYPE_2 = "Breathe";
    public static final String DEFAULT_EX_TYPE_3 = "Notice";
    public static final String DEFAULT_EX_TYPE_4 = "Relax";

    public static final String DEFAULT_EXERCISE_1 = "Write down 3 things you appreciate from today";
    public static final String DEFAULT_EXERCISE_2 = "Get some exercise, without your phone, and focus on the"
            + " physical sensations";
    public static final String DEFAULT_EXERCISE_3 = "De-clutter part of your house or office desk, helping the mind"
            + " to feel calmer and clearer";
    public static final String DEFAULT_EXERCISE_4 = "Take 20 deep breaths; inhale through nose, exhale with mouth";
    public static final String DEFAULT_EXERCISE_5 = "Every time your phone vibrates today, pause and take one breath";
    public static final String DEFAULT_EXERCISE_6 = "Follow along with a guided meditation on Youtube";
    public static final String DEFAULT_EXERCISE_7 = "Walk for 10 minutes today, without your phone,"
            + " focused on your surroundings";
    public static final String DEFAULT_EXERCISE_8 = "Notice the sensation as you change posture today from sitting"
            + " to standing or standing to sitting";
    public static final String DEFAULT_EXERCISE_9 = "Eat one meal alone, without any distractions at all, focusing"
            + " just on the tastes and smells";
    public static final String DEFAULT_EXERCISE_10 = "Listen to a favourite piece of music";
    public static final String DEFAULT_EXERCISE_11 = "Drink a mindful cup of tea or coffee";
    public static final String DEFAULT_EXERCISE_12 = "Pick up a good book and read it while lounging in a"
            + " comfortable spot";

    // fields to represent changing properties of an exercise list
    private List<Exercise> exerciseList;

    // EFFECTS: constructs an collection of the default exercises
    public ExerciseList() {
        this.exerciseList = new ArrayList<Exercise>();
        addExercise(DEFAULT_EXERCISE_1, DEFAULT_EX_TYPE_1);
        addExercise(DEFAULT_EXERCISE_2, DEFAULT_EX_TYPE_1);
        addExercise(DEFAULT_EXERCISE_3, DEFAULT_EX_TYPE_1);
        addExercise(DEFAULT_EXERCISE_4, DEFAULT_EX_TYPE_2);
        addExercise(DEFAULT_EXERCISE_5, DEFAULT_EX_TYPE_2);
        addExercise(DEFAULT_EXERCISE_6, DEFAULT_EX_TYPE_2);
        addExercise(DEFAULT_EXERCISE_7, DEFAULT_EX_TYPE_3);
        addExercise(DEFAULT_EXERCISE_8, DEFAULT_EX_TYPE_3);
        addExercise(DEFAULT_EXERCISE_9, DEFAULT_EX_TYPE_3);
        addExercise(DEFAULT_EXERCISE_10, DEFAULT_EX_TYPE_4);
        addExercise(DEFAULT_EXERCISE_11, DEFAULT_EX_TYPE_4);
        addExercise(DEFAULT_EXERCISE_12, DEFAULT_EX_TYPE_4);
    }

    // REQUIRES: exerciseType is one of: DEFAULT_EX_TYPE_1, DEFAULT_EX_TYPE_2, DEFAULT_EX_TYPE_3, DEFAULT_EX_TYPE_4
    // MODIFIES: this
    // EFFECTS: adds a new exercise of the specified type to the collection of exercises
    public void addExercise(String exerciseDescription, String exerciseType) {
        Exercise exercise;
        if (exerciseType.equals(DEFAULT_EX_TYPE_1)) {
            exercise = new Act(exerciseDescription, exerciseType);
        } else if (exerciseType.equals(DEFAULT_EX_TYPE_2)) {
            exercise = new Breathe(exerciseDescription, exerciseType);
        } else if (exerciseType.equals(DEFAULT_EX_TYPE_3)) {
            exercise = new Notice(exerciseDescription, exerciseType);
        } else {
            exercise = new Relax(exerciseDescription, exerciseType);
        }
        this.exerciseList.add(exercise);
    }

    // REQUIRES: exerciseType is one of: DEFAULT_EX_TYPE_1, DEFAULT_EX_TYPE_2, DEFAULT_EX_TYPE_3, DEFAULT_EX_TYPE_4
    // EFFECTS: returns the first non-completed exercise of the specified type or null object if no more to do
    public Exercise getNextExercise(String type) {
        for (Exercise e: this.exerciseList) {
            if (!(e.getCompletionStatus()) && e.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }

    // EFFECTS: returns a (list-resembling) string of the completed exercise type:description pairs
    public String getCompletedExercisesAsString() {
        String exerciseStr = "";
        String emptyNotification = "No mindfulness exercises completed yet";

        for (Exercise e: this.exerciseList) {
            if (e.getCompletionStatus()) {
                exerciseStr += "- " + e.getType() + ": " + e.getDescription() + "\n";
            }
        }

        if (exerciseStr.length() == 0) {
            exerciseStr = emptyNotification;
        }
        return exerciseStr;
    }

    // EFFECTS: Returns the number of exercises in the ExerciseSet
    public int size() {
        return this.exerciseList.size();
    }

    // EFFECTS: getter method for list indexing
    public Exercise get(int i) {
        return this.exerciseList.get(i);
    }
}
