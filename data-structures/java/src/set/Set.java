package set;

public class Set {
    private int[] memory = new int[10];
    private int count;

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean add(int element) {
        boolean wasPresent = contains(element);
        if (!wasPresent) {
            memory[count++] = element;
        }
        return wasPresent;
    }

    public boolean contains(int newItem) {
        boolean isInMemory = false;
        for (int element: memory) {
            if (newItem == element) {
                isInMemory = true;
                break;
            }
        }
        return isInMemory;
    }
}
