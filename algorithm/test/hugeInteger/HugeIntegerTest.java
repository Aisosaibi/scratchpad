package hugeInteger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HugeIntegerTest {

    @Test
    @DisplayName("Should successfully take up to 40 digits")
    void testThatHugeIntegerCanTakeIntegerUpTo40Digits() {
        HugeInteger aHugeInteger = new HugeInteger("1234567891011112131415161718192021222324");
        System.out.printf("aHugeInteger: %s%n", aHugeInteger);
        assertNotNull(aHugeInteger);
    }

    @Test
    @DisplayName("Should throw an exception if the input exceeds 40 digits")
    void testThatHugeIntegerThrowsExceptionAbove40Digits() {
        String fortyOneDigits = "12345678910111121314151617181920212223242";

        assertThrows(IllegalArgumentException.class, () -> {
            new HugeInteger(fortyOneDigits);
        });
    }


    @Test
    @DisplayName("Should successfully equate equivalent HugeInteger objects")
    void testThatEquivalentHugeIntegersAreEqual() {
        assertEquals(new HugeInteger("1234"), new HugeInteger("1234"));
    }

    @Test
    @DisplayName("Should successfully add single digit numbers without carries")
    void testSimpleSubtractionNoCarry() {
        HugeInteger num1 = new HugeInteger("3");
        HugeInteger result = num1.subtract("2");
        assertEquals("1", result.toString());
    }

    @Test
    @DisplayName("Should correctly handle zero values")
    void testSubtractionWithZero() {
        HugeInteger num1 = new HugeInteger("0");
        HugeInteger result = num1.subtract("0");
        assertEquals("0", result.toString());

        HugeInteger num2 = new HugeInteger("12345");
        assertEquals("12345", num2.subtract("0").toString());
    }

    @Test
    @DisplayName("Should correctly handle single element changes with a simple 'borrow'")
    void testSimpleSubtractionBorrow() {
        HugeInteger num1 = new HugeInteger("121");
        HugeInteger result = num1.subtract("8");
        assertEquals("113", result.toString()); // Length expands from 1 to 2
    }

    @Test
    @DisplayName("Should correctly process mismatched lengths where input is shorter")
    void testInputIsShorter() {
        HugeInteger num1 = new HugeInteger("1005");
        HugeInteger result = num1.subtract("99");
        assertEquals("906", result.toString());
    }

    @Test
    @DisplayName("Should successfully execute consecutive 'borrows' that reduce the array size")
    void testCascadingCarryAndExpansion() {
        HugeInteger num1 = new HugeInteger("10000");
        HugeInteger result = num1.subtract("9999");
        assertEquals("1", result.toString());
    }

    @Test
    @DisplayName("Should correctly handle single element with a negative result")
    void testSimpleSubtractionNegative() {
        HugeInteger num1 = new HugeInteger("7");
        HugeInteger result = num1.subtract("8");
        assertEquals("15", result.toString()); // Length expands from 1 to 2
    }

    @Test
    @DisplayName("Should correctly process mismatched lengths where output will be negative")
    void testInputIsLonger() {
        HugeInteger num1 = new HugeInteger("45");
        HugeInteger result = num1.subtract("12300");
        assertEquals("-12265", result.toString());
    }

//    @Test
//    @DisplayName("Should accurately add exceptionally huge integers")
//    void testVeryHugeIntegers() {
//        HugeInteger num1 = new HugeInteger("999999999999999999999999999999");
//        HugeInteger num2 = num1.add("111111111111111111111111111111");
//        assertEquals("1111111111111111111111111111110", num2.toString());
//    }

}