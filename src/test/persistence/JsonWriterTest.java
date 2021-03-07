package persistence;

import static model.ExerciseList.*;

import model.Exercise;
import model.ExerciseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Citation: Code taken and modified from JsonWriterTest.java class in JsonSerializationDemo
public class JsonWriterTest extends JsonTest {
    protected static final String ACT_DESCRIPT = "Act description";
    protected static final String BREATHE_DESCRIPT = "Breathe description";
    protected static final String NOTICE_DESCRIPT = "Notice description";
    protected static final String RELAX_DESCRIPT = "Relax description";

    @Test
    void testWriterInvalidFile() {
        try {
            ExerciseList el = new ExerciseList(true);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyExerciseList() {
        try {
            ExerciseList el = new ExerciseList(true);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExerciseList.json");
            writer.open();
            writer.write(el);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExerciseList.json");
            el = reader.read();
            assertEquals(0, el.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralExerciseList() {
        try {
            ExerciseList el = new ExerciseList(true);
            el.addExercise(DEFAULT_EX_TYPE_1, ACT_DESCRIPT, true);
            el.addExercise(DEFAULT_EX_TYPE_2, BREATHE_DESCRIPT, false);
            el.addExercise(DEFAULT_EX_TYPE_3, NOTICE_DESCRIPT, true);
            el.addExercise(DEFAULT_EX_TYPE_4, RELAX_DESCRIPT, false);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExerciseList.json");
            writer.open();
            writer.write(el);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExerciseList.json");
            el = reader.read();
            List<Exercise> exerciseList = el.getExercises();
            assertEquals(4, exerciseList.size());
            checkExercise(DEFAULT_EX_TYPE_1, ACT_DESCRIPT, true, exerciseList.get(0));
            checkExercise(DEFAULT_EX_TYPE_2, BREATHE_DESCRIPT, false, exerciseList.get(1));
            checkExercise(DEFAULT_EX_TYPE_3, NOTICE_DESCRIPT, true, exerciseList.get(2));
            checkExercise(DEFAULT_EX_TYPE_4, RELAX_DESCRIPT, false, exerciseList.get(3));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
