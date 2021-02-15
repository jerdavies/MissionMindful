package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.ExerciseList.*;
import static org.junit.jupiter.api.Assertions.*;

class ExerciseListTest {
    protected static final String ACT_DESCRIPT = "Act description";
    protected static final String BREATHE_DESCRIPT = "Breathe description";
    protected static final String NOTICE_DESCRIPT = "Notice description";
    protected static final String RELAX_DESCRIPT = "Relax description";

    private ExerciseList exerciseList;
    private Exercise exercise;


    @BeforeEach
    void runBefore() {
        exerciseList = new ExerciseList();
    }

    @Test
    void testAddExercise(){
        Exercise lastExercise;
        int originalSize = this.exerciseList.size();

        this.exerciseList.addExercise(ACT_DESCRIPT, DEFAULT_EX_TYPE_1);
        lastExercise = this.exerciseList.get(exerciseList.size() - 1);
        assertEquals(ACT_DESCRIPT,lastExercise.getDescription());
        assertEquals(originalSize + 1,exerciseList.size());
        assertFalse(lastExercise.getCompletionStatus());

        this.exerciseList.addExercise(RELAX_DESCRIPT, DEFAULT_EX_TYPE_4);
        lastExercise = this.exerciseList.get(exerciseList.size() - 1);
        assertEquals(RELAX_DESCRIPT,lastExercise.getDescription());
        assertEquals(originalSize + 2,exerciseList.size());
    }

    @Test
    void testGetNextExerciseWithoutMarkingAsComplete() {
        for (int i = 1; i <= 3; i++) {
            this.exercise = this.exerciseList.getNextExercise(DEFAULT_EX_TYPE_1);
            assertEquals(DEFAULT_EXERCISE_1, exercise.getDescription());

            this.exercise = this.exerciseList.getNextExercise(DEFAULT_EX_TYPE_3);
            assertEquals(DEFAULT_EXERCISE_7, exercise.getDescription());
        }
    }

    @Test
    void testGetNextExerciseAfterMarkingAsComplete() {
        this.exercise = this.exerciseList.getNextExercise(DEFAULT_EX_TYPE_2);
        assertEquals(DEFAULT_EXERCISE_4, exercise.getDescription());
        this.exercise.markExerciseComplete();

        this.exercise = this.exerciseList.getNextExercise(DEFAULT_EX_TYPE_2);
        assertEquals(DEFAULT_EXERCISE_5, exercise.getDescription());
    }

    @Test
    void testGetNextExerciseWhenNoneLeftOfType() {
        for (int i = 1; i <= 3; i++) {
            this.exercise = this.exerciseList.getNextExercise(DEFAULT_EX_TYPE_1);
            this.exercise.markExerciseComplete();
        }
        this.exercise = this.exerciseList.getNextExercise(DEFAULT_EX_TYPE_1);
        assertNull(exercise);
    }

    @Test
    void testGetCompletedExercisesNone() {
        String result = this.exerciseList.getCompletedExercisesAsString();
        String emptyNotification = "No mindfulness exercises completed yet";

        assertEquals(emptyNotification, result);
    }

    @Test
    void testGetCompletedExercisesOne() {
        String result;
        String expected;

        exercise = exerciseList.getNextExercise(DEFAULT_EX_TYPE_3);
        exercise.markExerciseComplete();

        result = this.exerciseList.getCompletedExercisesAsString();
        expected = "- " + DEFAULT_EX_TYPE_3 + ": " + DEFAULT_EXERCISE_7 + "\n";

        assertEquals(expected, result);
    }

    @Test
    void testGetCompletedExercisesMany() {
        String result;
        String expected;

        exercise = exerciseList.getNextExercise(DEFAULT_EX_TYPE_3);
        exercise.markExerciseComplete();
        exercise = exerciseList.getNextExercise(DEFAULT_EX_TYPE_3);
        exercise.markExerciseComplete();
        exercise = exerciseList.getNextExercise(DEFAULT_EX_TYPE_4);
        exercise.markExerciseComplete();

        result = this.exerciseList.getCompletedExercisesAsString();
        expected = "- " + DEFAULT_EX_TYPE_3 + ": " + DEFAULT_EXERCISE_7 + "\n"
                + "- " + DEFAULT_EX_TYPE_3 + ": " + DEFAULT_EXERCISE_8 + "\n"
                + "- " + DEFAULT_EX_TYPE_4 + ": " + DEFAULT_EXERCISE_10 + "\n";

        assertEquals(expected, result);
    }
}