package model;

import java.util.ArrayList;
import java.util.HashMap;
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
    private static final String DEFAULT_EX_TYPE_1 = "Act";
    private static final String DEFAULT_EX_TYPE_2 = "Breathe";
    private static final String DEFAULT_EX_TYPE_3 = "Notice";
    private static final String DEFAULT_EX_TYPE_4 = "Relax";
    private static final String DEFAULT_EXERCISE_1 = "Write down 3 things you appreciate from today";
    private static final String DEFAULT_EXERCISE_2 = "Get some exercise, without your phone, and focus on the" +
            " physical sensations";
    private static final String DEFAULT_EXERCISE_3 = "De-clutter part of your house or office desk, helping the mind" +
            " to feel calmer and clearer";
    private static final String DEFAULT_EXERCISE_4 = "Take 20 deep breaths; inhale through nose; exhale with mouth";
    private static final String DEFAULT_EXERCISE_5 = "Every time your phone vibrates today, pause and take one breath";
    private static final String DEFAULT_EXERCISE_6 = "Follow along with a guided meditation on Youtube";
    private static final String DEFAULT_EXERCISE_7 = "Walk for 10 minutes today, without your phone, " +
            " focused on your surroundings";
    private static final String DEFAULT_EXERCISE_8 = "Notice the sensation as you change posture today from sitting" +
            " to standing or standing to sitting";
    private static final String DEFAULT_EXERCISE_9 = "Eat one meal alone, without any distractions at all, focusing" +
            " just on the tastes and smells";
    private static final String DEFAULT_EXERCISE_10 = "Listen to a favourite piece of music";
    private static final String DEFAULT_EXERCISE_11 = "Drink a mindful cup of tea or coffee";
    private static final String DEFAULT_EXERCISE_12 = "Pick up a good book and read it while lounging in a" +
            " comfortable spot";

    // fields to represent changing properties of an exercise list
    private List<Exercise> exerciseList;
    private List<String> defaultExerciseTypes;
    private int actLevel;
    private int breatheLevel;
    private int noticeLevel;
    private HashMap<String, int> currentCategoryLevels;


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



        createCategoryLevelsTracker();
        System.out.println(exerciseList.get(11).getDescription());
        System.out.println(defaultExerciseTypes.get(3));
        System.out.println(completedExercisesAsString());
    }

    // REQUIRES: exerciseType is one of: DEFAULT_EX_TYPE_1, DEFAULT_EX_TYPE_2, DEFAULT_EX_TYPE_3, DEFAULT_EX_TYPE_4
    // MODIFIES: this
    // EFFECTS: adds a new exercise to the collection of exercises
    public void addExercise(String exerciseDescription, String exerciseType) {
        Exercise exercise = new Exercise(exerciseDescription, exerciseType);  // !!! Eventually need if here to create diff types of exercises
        this.exerciseList.add(exercise);
    }

    // MODIFIES: this
    // EFFECTS: initializes all category levels to level 0
    public void createCategoryLevelsTracker() {
        this.currentCategoryLevels = new HashMap<String, int>();
        currentCategoryLevels.put(DEFAULT_EX_TYPE_1, 0);
        currentCategoryLevels.put(DEFAULT_EX_TYPE_2, 0);
        currentCategoryLevels.put(DEFAULT_EX_TYPE_3, 0);
        currentCategoryLevels.put(DEFAULT_EX_TYPE_4, 0);
    }

    // REQUIRES: exerciseType is one of: DEFAULT_EX_TYPE_1, DEFAULT_EX_TYPE_2, DEFAULT_EX_TYPE_3, DEFAULT_EX_TYPE_4
    // MODIFIES: this
    // EFFECTS: adds one to category level of "just completed" level for the relevant exercise type
    public void levelUp(String type) {
        currentCategoryLevels.put(type, currentCategoryLevels.get(type) + 1);
    }

    // EFFECTS: returns a string of the completed exercise type:description pairs
    public String completedExercisesAsString() {
        String exerciseStr = "[";
        String emptyNotification = "No mindfulness exercises completed yet";

        for (Exercise e: this.exerciseList) {
            if (e.getCompletionStatus()) {
                exerciseStr += e.getType() + ": " + e.getDescription();
            }
        }
        exerciseStr += "]";

        if (exerciseStr.length() <= 2) {
            exerciseStr = emptyNotification;
        }
        return exerciseStr;
    }


}
