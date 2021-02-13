package model;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Exercise {
    private String type;
    private String description;
    private Boolean isComplete;

    // EFFECTS: constructs an exercise with an associated
    // exercise description and exercise type
    public Exercise(String exerciseDescription, String exerciseType) {
        this.type = exerciseType;
        this.description = exerciseDescription;
        this.isComplete = false;
    }

    // EFFECTS: returns exercise type of the current exercise
    public String getType() {
        return this.type;
    }

    // EFFECTS: returns exercise description of the current exercise
    public String getDescription() {
        return this.description;
    }
}


