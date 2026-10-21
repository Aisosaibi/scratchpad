package arrayExercise;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestArrayExercise {
    @Test
    public void testThatArrayIsFlippedTo_248312() {
        int[] array = {2, 4, 3, 8, 2, 1};
        assertArrayEquals(new int[]{2, 4, 8, 3, 1, 2}, ArrayExercise.spFlipArray(array));
    }

    @Test
    public void testThatArrayIsFlippedTo_135622() {
        int[] array = {1, 3, 5, 6, 2, 2};
        assertArrayEquals(new int[]{1, 3, 6, 5, 2, 2}, ArrayExercise.spFlipArray(array));
    }

    @Test
    public void testThatArrayIsFlippedTo_1_3569711137() {
        int[] array = {1, -2, 5, 6, 9, 7, 11, 14, 7};
        assertArrayEquals(new int[]{-2, 1, 6, 5, 9, 7, 14, 11, 7}, ArrayExercise.spFlipArray(array));
    }
}
