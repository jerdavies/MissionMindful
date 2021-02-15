package model;

/**
 * A representation of an exercise prompt with the theme "Breathe".
 */

public class Breathe extends Exercise {
    private static final String CONGRATS_MSG = "Your mind and body thank you!";

    public Breathe(String exerciseDescription, String exerciseType) {
        super(exerciseDescription, exerciseType);
    }

    @Override
    // EFFECTS: returns congratulatory message to the user for completing an exercise
    public String congratulateUser() {
        return CONGRATS_MSG;
    }
}
