package model;

/**
 * A representation of an exercise prompt with the theme "Relax".
 */

public class Relax extends Exercise {
    private static final String CONGRATS_MSG = "Hope you feel like a million bucks!";

    public Relax(String exerciseType, String exerciseDescription, Boolean isComplete) {
        super(exerciseType, exerciseDescription, isComplete);
    }

    @Override
    // EFFECTS: returns congratulatory message to the user for completing an exercise
    public String congratulateUser() {
        return CONGRATS_MSG;
    }
}
