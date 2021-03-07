package persistence;

import model.Exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Citation: Code taken and modified from JsonTest.java class in JsonSerializationDemo
public class JsonTest {
    protected void checkExercise(String type, String description, Boolean isComplete, Exercise exercise) {
        assertEquals(type, exercise.getType());
        assertEquals(description, exercise.getDescription());
        assertEquals(isComplete, exercise.getCompletionStatus());
    }
}
