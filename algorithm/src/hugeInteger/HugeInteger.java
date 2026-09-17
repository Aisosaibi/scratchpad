package hugeInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class HugeInteger {
    private final int[] value;
    private final boolean isNegative;

    private static final Pattern VALID_NUMBER = Pattern.compile("-?\\d+");

    public HugeInteger(String number) {
        if (!VALID_NUMBER.matcher(number).matches()) {
            throw new IllegalArgumentException("Input must be an optional '-' followed by digits only!");
        }
        isNegative = number.startsWith("-");
        String digits = isNegative ? number.substring(1) : number;
        if (digits.length() > 40) { throw new IllegalArgumentException("Maximum value exceeded!"); }
        this.value = parse(digits);
    }

    public int[] parse(String input) {
        char[] chars = input.toCharArray();
        int[] value = new int[chars.length];
        for (int i = 0; i < input.length(); i++) {
            value[i] = Character.getNumericValue(chars[i]);
        }
        return value;
    }

    public int[] parse(int number) {
        return parse(String.valueOf(number));
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        if (isNegative) { string.append("-"); }
        for (int digit: value) {
             string.append(digit);
        }
        return string.toString();
    }

    @Override
    public boolean equals(Object object) {
        // 1. Memory optimization: check if they point to the exact same memory address
        if (this == object) { return true; }
        // 2. Safety check: return false if the other object is null or a different class type
        if (object == null || getClass() != object.getClass()) { return false; }
        // 3. Safe cast: now we know for a fact it's a HugeInteger
        HugeInteger aHugeInteger = (HugeInteger) object;
        // 4. Content check
        return this.isNegative == aHugeInteger.isNegative && Arrays.equals(this.value, aHugeInteger.value);
    }


    public HugeInteger add(String input) {
        int[] inputValue = parse(input);
        ArrayList<Integer> sum = new ArrayList<>();

        boolean inputIsLonger = inputValue.length > value.length;

        int[] numerator = inputIsLonger ? inputValue : this.value;
        int[] denominator = inputIsLonger ? this.value : inputValue;

        int numLength = numerator.length;
        int denLength = denominator.length;

        for (int i = denLength - 1; i >= 0; i--) {
            int numIx = i + (numLength - denLength);
            sum.addFirst(numerator[numIx] + denominator[i]);
        }

        for (int i = (numLength - denLength) - 1; i >= 0; i--) {
            sum.addFirst(numerator[i]);
        }
        round(sum);

        String resultStr = sum.toString().replaceAll("[\\[\\], ]", "");
        return new HugeInteger(resultStr);
    }


    private void round(ArrayList<Integer> input) {
        boolean carry = false;

        for (int i = input.size() - 1; i >= 0; i--) {
            if (carry) {
                input.set(i, input.get(i) + 1);
                carry = false;
            }

            if (input.get(i) > 9) {
                input.set(i, input.get(i) - 10);
                carry = true;
            }
        }
        // Insert a new digit at the front, do not overwrite index 0!
        if (carry) {
            input.addFirst(1);
        }
    }

    public HugeInteger subtract(String input) {
        int[] inputValue = parse(input);

        boolean inputIsLarger  = compareMagnitude(inputValue, this.value) > 0;
        ArrayList<Integer> difference = getDifference(inputIsLarger, inputValue);
        roundSubtract(difference);

        while (difference.size() > 1 && difference.getFirst() == 0) {
            difference.removeFirst();
        }

        String resultStr = difference.toString().replaceAll("[\\[\\], ]", "");
        if (inputIsLarger) { resultStr = "-" + resultStr; }
        return new HugeInteger(resultStr);
    }

    private ArrayList<Integer> getDifference(boolean inputIsLarger, int[] inputValue) {
        int[] minuend = inputIsLarger ? inputValue : this.value;
        int[] subtrahend = inputIsLarger ? this.value : inputValue;

        ArrayList<Integer> difference = new ArrayList<>();

        int numLength = minuend.length;
        int denLength = subtrahend.length;

        for (int i = denLength - 1; i >= 0; i--) {
            int numIx = i + (numLength - denLength);
            difference.addFirst(minuend[numIx] - subtrahend[i]);
        }

        for (int i = (numLength - denLength) - 1; i >= 0; i--) {
            difference.addFirst(minuend[i]);
        }
        return difference;
    }

    private int compareMagnitude(int[] inputValue, int[] value) {
        if(inputValue.length != value.length) {
            return Integer.compare(inputValue.length, value.length);
        }
        for (int i = 0; i < inputValue.length; i++) {
            if (inputValue[i] != value[i]) {
                return Integer.compare(inputValue[i], value[i]);
            }
        }
        return 0;
    }

    private void roundSubtract(ArrayList<Integer> input) {
        boolean borrow = false;

        for (int i = input.size() - 1; i >= 0; i--) {
            if (borrow) {
                input.set(i, input.get(i) - 1);
                borrow = false;
            }

            if (input.get(i) < 0) {
                input.set(i, input.get(i) + 10);
                borrow = true;
            }
        }
    }

//
//    public boolean isEqualTo(int x) {
//        return false;
////      Todo %b true or false...
//    }
//
//    public boolean isNotEqualTo(int x) {
//        return false;
//    }
//
//    public isGreaterThan(int x) {
//        return false;
//    }
//
//    public isLessThan(int x) {
//        return false;
//    }
//
//    public isGreaterThanOrEqualTo(int x) {
//        return false;
//    }
//
//    public isLessThanOrEqualTo(int x) {
//        return false;
//    }

//    Todo multiply, divide, remainder




//  ===============================
//    ALTERNATIVE CODE LOGIC:
//  ===============================

//    private void round(ArrayList<Integer> input) {
//        int carry = 0;
//
//        // 1. Loop BACKWARDS (from right-to-left) because carries move to larger digits
//        for (int i = input.size() - 1; i >= 0; i--) {
//            int currentTotal = input.get(i) + carry;
//
//            // Extract the single digit (e.g., 15 % 10 = 5)
//            input.set(i, currentTotal % 10);
//
//            // Calculate the carry for the next column to the left (e.g., 15 / 10 = 1)
//            carry = currentTotal / 10;
//        }
//
//        // 2. If there is a leftover carry at the very end, insert it at the front!
//        if (carry > 0) {
//            input.add(0, carry);
//        }
//    }
//
//    public HugeInteger add(String input) {
//        int[] inputValue = parse(input);
//        ArrayList<Integer> sum = new ArrayList<>();
//
//        // Start pointers at the last digit (right-most) of both arrays
//        int i = this.value.length - 1;
//        int j = inputValue.length - 1;
//
//        // Loop backwards until both numbers are fully processed
//        while (i >= 0 || j >= 0) {
//            int digit1 = (i >= 0) ? this.value[i] : 0;
//            int digit2 = (j >= 0) ? inputValue[j] : 0;
//
//            // Insert at index 0 so the digits maintain standard left-to-right reading order
//            sum.add(0, digit1 + digit2);
//
//            i--;
//            j--;
//        }
//        // Your method to handle carries and normalize the digits
//        round(sum);
//
//        // Strip brackets and commas from ArrayList string representation
//        String resultStr = sum.toString().replaceAll("[\\[\\], ]", "");
//        return new HugeInteger(resultStr);
//    }
//
//    public HugeInteger subtract(String input) {
//        int[] inputValue = parse(input);
//
//        // Decide which digit array is numerically larger so we always
//        // subtract the smaller from the larger, then reapply the sign after.
//        boolean inputIsLarger = compareMagnitude(inputValue, this.value) > 0;
//        int[] minuend    = inputIsLarger ? inputValue : this.value;
//        int[] subtrahend = inputIsLarger ? this.value : inputValue;
//
//        ArrayList<Integer> difference = new ArrayList<>();
//
//        int i = minuend.length - 1;
//        int j = subtrahend.length - 1;
//
//        while (i >= 0 || j >= 0) {
//            int digit1 = (i >= 0) ? minuend[i] : 0;
//            int digit2 = (j >= 0) ? subtrahend[j] : 0;
//
//            difference.add(0, digit1 - digit2);
//
//            i--;
//            j--;
//        }
//
//        roundSubtract(difference);
//
//        while (difference.size() > 1 && difference.get(0) == 0) {
//            difference.remove(0);
//        }
//
//        String resultStr = difference.toString().replaceAll("[\\[\\], ]", "");
//        if (inputIsLarger) {
//            resultStr = "-" + resultStr;
//        }
//        return new HugeInteger(resultStr);
//    }

//    private void roundSubtract(ArrayList<Integer> input) {
//        int borrow = 0;
//
//        // Loop BACKWARDS since a borrow moves left, into more significant digits
//        for (int i = input.size() - 1; i >= 0; i--) {
//            int currentTotal = input.get(i) - borrow;
//
//            if (currentTotal < 0) {
//                currentTotal += 10;
//                borrow = 1;   // next (more significant) digit owes us one
//            } else {
//                borrow = 0;
//            }
//            input.set(i, currentTotal);
//        }
//        // Unlike round()'s leftover carry, a leftover borrow here would mean
//        // subtrahend > minuend — which compareMagnitude() already prevents.
//    }

}
