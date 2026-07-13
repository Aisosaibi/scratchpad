package queue;

public class Queue {
    private final int[] memory = new int [10];
    private int indexR;
    private int indexL;

    public boolean isEmpty() {
        return indexR == 0;
    }

    public void add(int item) {
        if (indexR == memory.length) {
            throw new QueueOverflowException("Queue is full");
        }
        memory[indexR++] = item;
    }

    public int element() {
        if (indexR == 0) {
            throw new QueueUnderflowException("Queue is empty");
        }
        return memory[indexL];
    }

    public int remove() {
        if (indexR == 0) {
            throw new QueueUnderflowException("Queue is empty");
        }
        indexR--;
        return memory[indexL++];
    }

    public int size() {
        return indexR;
    }
}


//package queue;
//
//public class Queue {
//    private final int[] memory = new int [10];
//    private int indexL = memory.length - 1;
//    private int indexR = memory.length - 1;
//
//    public boolean isEmpty() {
//        return indexL == memory.length - 1;
//    }
//
//    public void add(int item) {
//        if (indexL < 0) {
//            throw new QueueOverflowException("Queue is full");
//        }
//        memory[indexL] = item;
//        indexL--;
//    }
//
//    public int element() {
//        if (indexL == memory.length) {
//            throw new QueueUnderflowException("Queue is empty");
//        }
//        return memory[indexR--];
//    }
//
//    public int remove() {
//        if (indexL == memory.length) {
//            throw new QueueUnderflowException("Queue is empty");
//        }
//        indexL++;
//        return memory[indexR--];
//    }
//
//    public int size() {
//        return memory.length - indexL - 1;
//    }
//}