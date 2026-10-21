public class rotateArray {
    public static int [] rotate(int[] numbers, int shift) {
        int [] result = new int [numbers.length];

        for(int index = 0; index < numbers.length; index++){
            if(index > (numbers.length - 1) - shift){
                int swapIx = numbers.length - shift;
                result[index] = numbers[index - swapIx];
            }
            else {
                result[index] = numbers[index + shift];
            }
        }
        return result;
    }
}
