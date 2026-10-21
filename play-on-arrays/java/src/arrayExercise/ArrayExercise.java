package arrayExercise;

public class ArrayExercise {
    public static int[] spFlipArray(int[] array) {
        int leftPointer = 0;
        int rightPointer = 1;
        int temp;

        for (int index = 0; index < array.length/2; index++) {
            if ((array[leftPointer] + array[rightPointer]) % 2 == 0){
                leftPointer +=2; rightPointer+=2;
            } else {
                temp = array[leftPointer];
                array[leftPointer] = array[rightPointer];
                array[rightPointer] = temp;
                leftPointer +=2; rightPointer+=2;
            }
        }
        return array;
    }
}

