package model;

/**
 * A representation of an exercise prompt with the theme "Breathe".
 */

public class Breathe extends Exercise {
    private static final String CONGRATS_MSG = "Your mind and body thank you!";

    public Breathe(String exerciseType, String exerciseDescription, Boolean isComplete) {
        super(exerciseType, exerciseDescription, isComplete);
    }

    @Override
    // EFFECTS: returns congratulatory message to the user for completing an exercise
    public String congratulateUser() {
        return CONGRATS_MSG;
    }
}
