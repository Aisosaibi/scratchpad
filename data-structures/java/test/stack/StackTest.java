package stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    private Stack stack;

    @BeforeEach
    public void setUp(){
        stack = new Stack();
    }

    @Test
    public void testThatStackIsEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testThatStackIsNotEmpty() {
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testThatStackIsNowEmpty() {
        stack.push(1);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testThatPeekReturnsLastPushedItem() {
        int test = 1;
        stack.push(test);
        int result = stack.peek();
        assertFalse(stack.isEmpty());
        assertEquals(test, result);
    }

//    @Test
//    public void testThatPopEmptiesStackWithOneItem() {
//        int test = 1;
//        stack.push(test);
//        stack.pop();
//        assertTrue(stack.isEmpty());
//    }

    @Test
    public void testThatPushTwiceThenPopReturnsItemPushedLast() {
        stack.push(10);
        stack.push(20);
        int lastPushed = stack.pop();
        assertEquals(20, lastPushed);
    }

    @Test
    public void testThatPushThriceThenPopThenPeekReturnsItemPushedSecond() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.pop();
        int secondPushed = stack.peek();
        assertEquals(20, secondPushed);
    }

    @Test
    public void testThatSearchReturns1basedIndexOfItemIfFound() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        int position = stack.search(10);
        assertEquals(3, position);
    }

    @Test
    public void testThatSearchReturnsMinus1basedIndexOfItemIfNotFound() {
        stack.push(10);
        stack.push(20);
        stack.push(30);
        int position = stack.search(100);
        assertEquals(-1, position);
    }

    @Test
    public void testThatStackReturnsStackOverflowErrorWhenAddingMoreThanStackSize() {
        for (int i = 0; i < 10; i++) {
            stack.push(10);
        }
        assertThrows(StackOverflowException.class, () -> stack.push(30));
    }

    @Test
    public void testThatStackReturnsStackUnderflowErrorWhenAddingMoreThanStackSize() {
        assertThrows(StackUnderflowException.class, () -> stack.pop());
    }

    @Test
    public void testThatStackReturnsStackUnderflowErrorWhenPeepingIntoEmptyStack() {
        assertThrows(StackUnderflowException.class, () -> stack.peek());
    }
}
