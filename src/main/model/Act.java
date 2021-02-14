package model;

/**
 * A representation of an exercise prompt with the theme "Act".
 */

public class Act extends Exercise {
    private static final String CONGRATS_MSG = "Congrats for taking action!";

    public Act(String exerciseDescription, String exerciseType) {
        super(exerciseDescription, exerciseType);
    }

    @Override
    // EFFECTS: returns congratulatory message to the user for completing an exercise
    protected String congratulateUser() {
        return CONGRATS_MSG;
    }
}
