package set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetTest {
    @Test
    public void testIsEmptyReturnsTrue() {
        Set set = new Set();
        assertTrue(set.isEmpty());
    }

    @Test
    public void testIsEmptyReturnsFalse() {
        Set set = new Set();
        set.add(5);
        assertFalse(set.isEmpty());
    }

    @Test
    public void testContainsReturnsFalseIfItemIsUnique() {
        Set set = new Set();
        boolean canAdd = set.contains(5);
        assertFalse(canAdd);
    }

    @Test
    public void testContainsReturnsTrueIfItemIsAlreadyPresent() {
        Set set = new Set();
        set.add(5);
        boolean canAdd = set.contains(5);
        assertTrue(canAdd);
    }

    @Test
    public void testAddReturnsTrueIfItemIsUnique() {
        Set set = new Set();
        boolean isInSet = set.add(5);
        assertTrue(isInSet);
    }

    @Test
    public void testAddReturnsFalseIfItemIsADuplicate() {
        Set set = new Set();
        set.add(5); set.add(6);
        boolean canAdd5 = set.add(5);
        assertFalse(canAdd5);
    }

}