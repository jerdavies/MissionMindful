package model;

/**
 * A representation of an exercise prompt with the theme "Notice".
 */

public class Notice extends Exercise {
    private static final String CONGRATS_MSG = "Well done for dialing into the present moment!";

    public Notice(String exerciseDescription, String exerciseType) {
        super(exerciseDescription, exerciseType);
    }

    @Override
    // EFFECTS: returns congratulatory message to the user for completing an exercise
    protected String congratulateUser() {
        return CONGRATS_MSG;
    }
}
