package model;

import exceptions.InvalidTypeException;
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
        exerciseList = new ExerciseList(false);
    }

    @Test
    void testAddExercise(){
        Exercise lastExercise;
        int originalSize = this.exerciseList.size();

        try {
            this.exerciseList.addExercise(DEFAULT_EX_TYPE_1, ACT_DESCRIPT, false);
            lastExercise = this.exerciseList.get(exerciseList.size() - 1);
            assertEquals(ACT_DESCRIPT,lastExercise.getDescription());
            assertEquals(originalSize + 1,exerciseList.size());
            assertFalse(lastExercise.getCompletionStatus());
        } catch (InvalidTypeException e) {
            fail("Should not have been thrown");
        }


        try {
            this.exerciseList.addExercise(DEFAULT_EX_TYPE_4, RELAX_DESCRIPT, false);
            lastExercise = this.exerciseList.get(exerciseList.size() - 1);
            assertEquals(RELAX_DESCRIPT,lastExercise.getDescription());
            assertEquals(originalSize + 2,exerciseList.size());
        } catch (InvalidTypeException e) {
            fail("Should not have been thrown");
        }
    }

    @Test
    void testAddExerciseInvalidType(){
        Exercise lastExercise;
        int originalSize = this.exerciseList.size();

        try {
            this.exerciseList.addExercise("Type", ACT_DESCRIPT, false);
            fail("Should not be run");
        } catch (InvalidTypeException e) {
            System.out.println("Exception caught as intended");
        }
    }

    @Test
    void testGetNextExerciseWithoutMarkingAsComplete() {
        for (int i = 1; i <= 3; i++) {
            try {
                this.exercise = this.exerciseList.getNextExercise(DEFAULT_EX_TYPE_1);
                assertEquals(DEFAULT_EXERCISE_1, exercise.getDescription());
            } catch (InvalidTypeException e) {
                fail("Should not have been thrown");
            }


            try {
                this.exercise = this.exerciseList.getNextExercise(DEFAULT_EX_TYPE_3);
                assertEquals(DEFAULT_EXERCISE_7, exercise.getDescription());
            } catch (InvalidTypeException e) {
                fail("Should not have been thrown");
            }
        }
    }

    @Test
    void testGetNextExerciseAfterMarkingAsComplete() {
        try {
            this.exercise = this.exerciseList.getNextExercise(DEFAULT_EX_TYPE_2);
            assertEquals(DEFAULT_EXERCISE_4, exercise.getDescription());
            this.exercise.markExerciseComplete();
        } catch (InvalidTypeException e) {
            fail("Should not have been thrown");
        }


        try {
            this.exercise = this.exerciseList.getNextExercise(DEFAULT_EX_TYPE_2);
            assertEquals(DEFAULT_EXERCISE_5, exercise.getDescription());
        } catch (InvalidTypeException e) {
            fail("Should not have been thrown");
        }
    }

    @Test
    void testGetNextExerciseWhenNoneLeftOfType() {
        for (int i = 1; i <= 3; i++) {
            try {
                this.exercise = this.exerciseList.getNextExercise(DEFAULT_EX_TYPE_1);
                this.exercise.markExerciseComplete();
            } catch (InvalidTypeException e) {
                fail("Should not have been thrown");
            }

        }
        try {
            this.exercise = this.exerciseList.getNextExercise(DEFAULT_EX_TYPE_1);
            assertNull(exercise);
        } catch (InvalidTypeException e) {
            fail("Should not have been thrown");
        }
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

        try {
            exercise = exerciseList.getNextExercise(DEFAULT_EX_TYPE_3);
            exercise.markExerciseComplete();
        } catch (InvalidTypeException e) {
            fail("Should not have been thrown");
        }

        result = this.exerciseList.getCompletedExercisesAsString();
        expected = "- " + DEFAULT_EX_TYPE_3 + ": " + DEFAULT_EXERCISE_7 + "\n";

        assertEquals(expected, result);
    }

    @Test
    void testGetCompletedExercisesMany() {
        String result;
        String expected;

        try {
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
        } catch (InvalidTypeException e) {
            fail("Should not have been thrown");
        }
    }

    @Test
    void testGetCompletedExerciseArrayEmpty() {
        String[][] array = this.exerciseList.getCompletedExerciseArray();
        assertEquals(0, array.length);
    }

    @Test
    void testGetCompletedExerciseArrayOneComplete() {
        try {
            exercise = exerciseList.getNextExercise(DEFAULT_EX_TYPE_3);
            exercise.markExerciseComplete();

            String[][] array = this.exerciseList.getCompletedExerciseArray();

            assertEquals(1, array.length);
            assertEquals(DEFAULT_EX_TYPE_3, array[0][0]);
            assertEquals(DEFAULT_EXERCISE_7, array[0][1]);
        } catch (InvalidTypeException e) {
            fail("Should not have been thrown");
        }

    }

    @Test
    void testGetCompletedExerciseArrayManyComplete() {
        try {
            exercise = exerciseList.getNextExercise(DEFAULT_EX_TYPE_3);
            exercise.markExerciseComplete();
            exercise = exerciseList.getNextExercise(DEFAULT_EX_TYPE_3);
            exercise.markExerciseComplete();
            exercise = exerciseList.getNextExercise(DEFAULT_EX_TYPE_4);
            exercise.markExerciseComplete();

            String[][] array = this.exerciseList.getCompletedExerciseArray();

            assertEquals(3, array.length);
            assertEquals(DEFAULT_EX_TYPE_3, array[0][0]);
            assertEquals(DEFAULT_EXERCISE_7, array[0][1]);
            assertEquals(DEFAULT_EX_TYPE_3, array[1][0]);
            assertEquals(DEFAULT_EXERCISE_8, array[1][1]);
            assertEquals(DEFAULT_EX_TYPE_4, array[2][0]);
            assertEquals(DEFAULT_EXERCISE_10, array[2][1]);
        } catch (InvalidTypeException e) {
            fail("Should not have been thrown");
        }
    }

    @Test
    void testGetNextExerciseInvalidType() {
        try {
            exercise = exerciseList.getNextExercise("Running");
            fail("Should not be run");
        } catch (InvalidTypeException e) {
            System.out.println("Exception caught - as intended");
        }
    }
}