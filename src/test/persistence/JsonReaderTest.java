package persistence;

import model.Exercise;
import static model.ExerciseList.*;

import model.ExerciseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Citation: Code taken and modified from JsonReaderTest.java class in JsonSerializationDemo
class JsonReaderTest extends JsonTest {
    protected static final String ACT_DESCRIPT = "Act description";
    protected static final String BREATHE_DESCRIPT = "Breathe description";
    protected static final String NOTICE_DESCRIPT = "Notice description";
    protected static final String RELAX_DESCRIPT = "Relax description";

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ExerciseList el = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExerciseList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExerciseList.json");
        try {
            ExerciseList el = reader.read();
            assertEquals(0, el.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralExerciseList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExerciseList.json");
        try {
            ExerciseList el = reader.read();
            List<Exercise> exerciseList = el.getExercises();
            assertEquals(4, exerciseList.size());
            checkExercise(DEFAULT_EX_TYPE_1, ACT_DESCRIPT, true, exerciseList.get(0));
            checkExercise(DEFAULT_EX_TYPE_2, BREATHE_DESCRIPT, false, exerciseList.get(1));
            checkExercise(DEFAULT_EX_TYPE_3, NOTICE_DESCRIPT, true, exerciseList.get(2));
            checkExercise(DEFAULT_EX_TYPE_4, RELAX_DESCRIPT, false, exerciseList.get(3));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
