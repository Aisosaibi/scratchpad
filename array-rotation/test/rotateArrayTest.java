import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class rotateArrayTest {
    @Test
    public void rotateArrayTest() {
        int [] numbers = {2,3,4,5,6};
        int [] expected = {4,5,6,2,3};
        assertArrayEquals(expected, rotateArray.rotate(numbers,2));
    }

    @Test
    public void rotateArrayTestTwo() {
        int [] numbers = {2,3,10,4,5,6};
        int [] expected = {10,4,5,6,2,3};
        assertArrayEquals(expected, rotateArray.rotate(numbers,2));
    }

}
