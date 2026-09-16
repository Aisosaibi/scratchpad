package findMaxVariant;

public class FindMax {
//    public static int findMax(int[] input) {
//        int max = Integer.MIN_VALUE;
//        for (int i = 0; i < input.length; i++) {
//            if (input[i] > max) {
//                max = input[i];
//            }
//        }
//        return max;
//    }

//    public static int findSecMax(int[] input) {
//        int max = Integer.MIN_VALUE;
//        int secMax = Integer.MIN_VALUE;
//        for (int i = 0; i < input.length; i++) {
//            if (input[i] > max) {
//                secMax = max;
//                max = input[i];
//            }
//        }
//        return secMax;
//    }

    public static int findSecMax(int[] input) throws Exception {
        return sortIndex(input, 2);
    }

    public static int sortIndex(int[] input, int position) throws Exception {
        if (Math.abs(position) > input.length || position == 0) {
            throw new Exception("No such position in the array");
        }

        int[] positions = positions(input);

        int target = position > 0
                ? position - 1
                : input.length - Math.abs(position);

        return input[indexOfRank(positions, target)];

        /*
         * --- Alternative (sorting-based) approach, for reference ---
         *
         * int[] sorted = input.clone();
         * Arrays.sort(sorted); // ascending
         *
         * int index = position > 0
         *         ? sorted.length - position
         *         : Math.abs(position) - 1;
         *
         * return sorted[index];
         */
    }

    // positions[i] = how many other elements are >= input[i].
    // This is the *last* 0-indexed rank slot that input[i]'s tie group occupies
    // (e.g. 3 elements tied for the max all get count = 2, not 0).
    private static int[] positions(int[] input) {
        int[] positions = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int count = 0;
            for (int j = 0; j < input.length; j++) {
                if (i == j) continue;
                if (input[j] >= input[i]) {
                    count++;
                }
            }
            positions[i] = count;
        }
        return positions;
    }

    // Finds the index whose count is the smallest one still >= target.
    // Because every member of a tie group shares the same count (the "last"
    // slot the group covers), this correctly picks that group for any
    // target rank the group spans, even if the group isn't at index 0.
    private static int indexOfRank(int[] positions, int target) {
        int bestIndex = -1;
        int bestValue = Integer.MAX_VALUE;
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] >= target && positions[i] < bestValue) {
                bestValue = positions[i];
                bestIndex = i;
            }
        }
        return bestIndex;
    }
}