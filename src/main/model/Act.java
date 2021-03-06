package model;

/**
 * A representation of an exercise prompt with the theme "Act".
 */

public class Act extends Exercise {
    private static final String CONGRATS_MSG = "Congrats for taking action!";

    public Act(String exerciseType, String exerciseDescription, Boolean isComplete) {
        super(exerciseType, exerciseDescription, isComplete);
    }

    @Override
    // EFFECTS: returns congratulatory message to the user for completing an exercise
    public String congratulateUser() {
        return CONGRATS_MSG;
    }
}
