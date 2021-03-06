package model;

/**
 * A representation of an exercise prompt with the theme "Notice".
 */

public class Notice extends Exercise {
    private static final String CONGRATS_MSG = "Well done for dialing into the present moment!";

    public Notice(String exerciseType, String exerciseDescription, Boolean isComplete) {
        super(exerciseType, exerciseDescription, isComplete);
    }

    @Override
    // EFFECTS: returns congratulatory message to the user for completing an exercise
    public String congratulateUser() {
        return CONGRATS_MSG;
    }
}
