package queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    private Queue queue;

    @BeforeEach
    public void setUp() {
        queue = new Queue();
    }

    @Test
    public void testThatQueueIsEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testThatQueueIsNotEmpty() {
        queue.add(20);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testThatAddOnAFullQueueThrowsException() {
        for (int i = 0; i < 10; i++) {
            queue.add(1);
        }
        assertThrows(QueueOverflowException.class, () -> queue.add(2));
    }

    @Test
    public void testThatElementReturnsFirstEnqueuedItem() {
        queue.add(20);
        queue.add(50);
        int result = queue.element();
        assertEquals(20, result);
    }

    @Test
    public void testThatElementDoesNotRemoveFirstEnqueuedItem() {
        queue.add(20);
        queue.add(50);
        queue.element(); queue.element();
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testThatRemoveReturnsEnqueuedItem() {
        queue.add(20); queue.add(10);
        int result = queue.remove();
        assertEquals(20, result);
    }

    @Test
    public void testThatRemoveReducesQueueSize() {
        queue.add(50);
        int result = queue.remove();
        assertEquals(50, result);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testThatSizeReturnsQueueSize() {
        int SIZE = 5;
        for (int i = 0; i < 5; i++) {
            queue.add(50);
        }
        int result = queue.size();
        assertEquals(SIZE, result);
    }

    @Test
    public void testThatSizeReturnsZeroQueueSize() {
        int result = queue.size();
        assertEquals(0, result);
    }

    @Test
    public void testThatSizeThrowsUnderflowOnEmptyQueue() {
        assertThrows(QueueUnderflowException.class, queue::remove); //recommended version
    }
}
