package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.ExerciseList.*;
import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {

    private static final String CONGRATS_MSG_ACT = "Congrats for taking action!";
    private static final String CONGRATS_MSG_BREATHE = "Your mind and body thank you!";
    private static final String CONGRATS_MSG_NOTICE = "Well done for dialing into the present moment!";
    private static final String CONGRATS_MSG_RELAX = "Hope you feel like a million bucks!";

    private ExerciseList exerciseList;
    private Exercise exercise;
    private Exercise act;
    private Exercise breathe;
    private Exercise notice;
    private Exercise relax;

    @BeforeEach
    void runBefore() {
        exerciseList = new ExerciseList();
        act = new Act(DEFAULT_EXERCISE_1, DEFAULT_EX_TYPE_1);
        breathe = new Breathe(DEFAULT_EXERCISE_4, DEFAULT_EX_TYPE_2);
        notice = new Notice(DEFAULT_EXERCISE_7, DEFAULT_EX_TYPE_3);
        relax = new Relax(DEFAULT_EXERCISE_10, DEFAULT_EX_TYPE_4);
    }

    @Test
    void testCongratulateUser() {
        assertEquals(CONGRATS_MSG_ACT, act.congratulateUser());
        assertEquals(CONGRATS_MSG_BREATHE, breathe.congratulateUser());
        assertEquals(CONGRATS_MSG_NOTICE, notice.congratulateUser());
        assertEquals(CONGRATS_MSG_RELAX, relax.congratulateUser());
    }

    @Test
    void testMarkExerciseComplete() {
        this.exercise = this.exerciseList.getNextExercise(DEFAULT_EX_TYPE_2);
        assertFalse(exercise.getCompletionStatus());
        this.exercise.markExerciseComplete();
        assertTrue(exercise.getCompletionStatus());
    }
}