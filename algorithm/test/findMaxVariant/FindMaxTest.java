package findMaxVariant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindMaxTest {
//    @Test
//    void testThatFindSecMaxReturnsSecondMaximumNumber() {
//        int[] input = {2, 7, 6, 8, 1};
//        int result = FindMax.findSecMax(input);
//
//        assertEquals(7, result);
//    }

        // --- distinct values ---

    @Test
    void testThatFindMaxReturnsMaximumNumber() throws Exception {
        int[] input = {2, 7, 6, 8, 1};
        assertEquals(8, FindMax.sortIndex(input, 1));
    }

    @Test
    void testThatFindMaxReturnsSecondMaximumNumber() throws Exception {
        int[] input = {2, 7, 6, 8, 1};
        assertEquals(7, FindMax.sortIndex(input, 2));
    }

    @Test
    void testThatFindSecMaxReturnsSecondMaximumNumber() throws Exception {
        int[] input = {2, 7, 6, 8, 1};
        assertEquals(7, FindMax.findSecMax(input));
    }

    @Test
    void testThatFindMaxReturnsLowestNumber() throws Exception {
        int[] input = {2, 7, 6, 8, 1};
        assertEquals(1, FindMax.sortIndex(input, -1));
    }

    @Test
    void testThatFindMaxReturnsSecondLowestNumber() throws Exception {
        int[] input = {2, 7, 6, 8, 1};
        assertEquals(2, FindMax.sortIndex(input, -2));
    }

    // --- duplicate values ---

    @Test
    void testThatFindMaxReturnsMaximumNumberWithDuplicates() throws Exception {
        int[] input = {2, 7, 2, 6, 1};
        assertEquals(7, FindMax.sortIndex(input, 1));
    }

    @Test
    void testThatFindMaxReturnsSecondMaximumNumberWithDuplicates() throws Exception {
        int[] input = {2, 7, 2, 6, 1};
        assertEquals(6, FindMax.sortIndex(input, 2));
    }

    @Test
    void testThatFindMaxReturnsThirdMaximumNumberWithDuplicates() throws Exception {
        // sorted desc: 7, 6, 2, 2, 1 -> position 3 lands on the tied "2"
        int[] input = {2, 7, 2, 6, 1};
        assertEquals(2, FindMax.sortIndex(input, 3));
    }

    @Test
    void testThatFindMaxReturnsLowestNumberWithDuplicates() throws Exception {
        int[] input = {2, 7, 2, 6, 1};
        assertEquals(1, FindMax.sortIndex(input, -1));
    }

    @Test
    void testThatFindMaxReturnsSecondLowestNumberWithDuplicates() throws Exception {
        // sorted asc: 1, 2, 2, 6, 7 -> the second-lowest is the tied "2"
        int[] input = {2, 7, 2, 6, 1};
        assertEquals(2, FindMax.sortIndex(input, -2));
    }

    // --- error cases ---

    @Test
    void testThatPositionZeroThrows() {
        int[] input = {2, 7, 6, 8, 1};
        assertThrows(Exception.class, () -> FindMax.sortIndex(input, 0));
    }

    @Test
    void testThatPositionBeyondArrayLengthThrows() {
        int[] input = {2, 7, 6, 8, 1};
        assertThrows(Exception.class, () -> FindMax.sortIndex(input, 6));
    }

    @Test
    void testThatNegativePositionBeyondArrayLengthThrows() {
        int[] input = {2, 7, 6, 8, 1};
        assertThrows(Exception.class, () -> FindMax.sortIndex(input, -6));
    }
}
