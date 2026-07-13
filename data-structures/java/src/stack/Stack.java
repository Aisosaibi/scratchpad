package stack;

public class Stack {
    private final int[] memory = new int[10];
    private int count;

    public boolean isEmpty() { return count == 0; }

    public void push(int item) {
        if (count == memory.length) {
            throw new StackOverflowException("Stack is full");
        }
        memory[count++] = item;
    }

    public int peek() {
        if (count == 0) {
            throw new StackUnderflowException(("Stack is empty"));
        }
        return memory[count - 1];
    }

    public int pop() {
        if (count == 0) {
            throw new StackUnderflowException(("Stack is empty"));
        }
        return memory[--count];
    }

    public int search(int item) {
        for (int i = 0; i < count; i++) {
            if (item == memory[i]) return count - i;
        }
        return -1;
    }
}

// When I push(10), count is increased to 1 and 10 is placed at index 1 of memory.
// peek() gives us memory[1] which is 10, yeah. I couldn't figure out a way to use index 0
// Although I could. I could make my test work for count only less than 0.
// Then push actually places the counter at the next place while filling only the current