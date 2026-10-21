# Java Regex Validators: Questions and Tests

## The original brief

1. Write a Java method that takes a string and checks whether it matches this pattern, rejecting any deviation: `p.nwankwo@native.semicolon.africa`. Use regex, and comment every line.
2. Write a password validator method with these rules:
    - between 8 and 16 characters
    - no spaces
    - at least one of these special characters: `@`, `#`, `_`, `+`
    - at least one digit

---

## The roots (read this before writing a line)

A regex check is a yes/no question: **does the entire string fit this shape?** Two Java details decide whether your answer is right or only looks right:

- `String.matches(...)` and `Matcher.matches()` require the **whole** string to fit. `find()` accepts a match anywhere inside, which is how `"hello t.okafor@staff.semicolon.africa.evil"` sneaks through.
- In regex, `.` means *any character*. To mean a literal dot you must escape it, and in a Java string that becomes `"\\."`.

For the password-style tasks, one giant pattern gets unreadable fast. Is it easier to check each rule separately and combine them, or to encode them all in one pattern with lookaheads? Either is acceptable, but you must be able to explain your choice.

---

## The six questions

For every method: comment every line (say *why*, not just *what*), and reject `null` without throwing an exception. Put all methods in a class called `Validators`, all `public static`.

### Part A: pattern matching

**1. `isValidStaffEmail(String)`**
The shape is `initial.surname@group.semicolon.africa`.
- The initial is exactly one lowercase letter.
- The surname is 2 to 20 lowercase letters.
- The group is either `native` or `staff`.
- The rest is fixed: `.semicolon.africa`.

**2. `isValidNigerianPhone(String)`**
- Starts with `0` or `+234`.
- Followed by exactly 10 digits, and the first of those 10 digits is 7, 8 or 9.
- No spaces or dashes.

**3. `isValidCohortId(String)`** *(worked example given)*
The shape is `SC-YYYY-NNNN`.
- `SC` is uppercase.
- The year runs from 2020 to 2029.
- `NNNN` is four digits but not `0000`.

### Part B: rule-based validation

**4. `isValidStrongPassword(String)`** *(worked example given)*
- 10 to 20 characters long.
- No spaces.
- At least one uppercase letter.
- At least two digits.
- At least one of `!`, `$`, `%`, `&`.

**5. `isValidUsername(String)`**
- 5 to 12 characters long.
- Starts with a letter.
- Contains only letters, digits and underscores.
- Does not end with an underscore.

**6. `isValidPasswordFor(String username, String password)`**
Your original password rule, plus one more:
- 8 to 16 characters long.
- No spaces.
- At least one of `@ # _ +`.
- At least one digit.
- Must not contain the username, ignoring case.

---

## The tests (JUnit 5)

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class org.aispace.ValidatorsTest {

    // ---------- 1. Staff email ----------
    @Test
    void staffEmail_acceptsValidShapes() {
        assertTrue(Validators.isValidStaffEmail("t.okafor@staff.semicolon.africa"));
        assertTrue(Validators.isValidStaffEmail("p.nwankwo@native.semicolon.africa"));
    }

    @Test
    void staffEmail_rejectsDeviations() {
        assertFalse(Validators.isValidStaffEmail(null));
        assertFalse(Validators.isValidStaffEmail(""));
        assertFalse(Validators.isValidStaffEmail("T.okafor@staff.semicolon.africa"));      // uppercase initial
        assertFalse(Validators.isValidStaffEmail("tokafor@staff.semicolon.africa"));       // missing dot
        assertFalse(Validators.isValidStaffEmail("tt.okafor@staff.semicolon.africa"));     // two-letter initial
        assertFalse(Validators.isValidStaffEmail("t.o@staff.semicolon.africa"));           // surname too short
        assertFalse(Validators.isValidStaffEmail("t.okafor@intern.semicolon.africa"));     // wrong group
        assertFalse(Validators.isValidStaffEmail("t.okafor@staff.semicolon.com"));         // wrong TLD
        assertFalse(Validators.isValidStaffEmail("t.okafor@staffxsemicolon.africa"));      // unescaped-dot trap
        assertFalse(Validators.isValidStaffEmail(" t.okafor@staff.semicolon.africa"));     // leading space
        assertFalse(Validators.isValidStaffEmail("t.okafor@staff.semicolon.africa.evil")); // trailing junk
        assertFalse(Validators.isValidStaffEmail("hi t.okafor@staff.semicolon.africa"));   // matches() vs find()
    }

    // ---------- 2. Nigerian phone ----------
    @Test
    void phone_acceptsValid() {
        assertTrue(Validators.isValidNigerianPhone("08031234567"));
        assertTrue(Validators.isValidNigerianPhone("07012345678"));
        assertTrue(Validators.isValidNigerianPhone("09012345678"));
        assertTrue(Validators.isValidNigerianPhone("+2348031234567"));
    }

    @Test
    void phone_rejectsDeviations() {
        assertFalse(Validators.isValidNigerianPhone(null));
        assertFalse(Validators.isValidNigerianPhone(""));
        assertFalse(Validators.isValidNigerianPhone("06031234567"));      // 6 not allowed
        assertFalse(Validators.isValidNigerianPhone("8031234567"));       // no prefix
        assertFalse(Validators.isValidNigerianPhone("0803123456"));       // too short
        assertFalse(Validators.isValidNigerianPhone("080312345678"));     // too long
        assertFalse(Validators.isValidNigerianPhone("+234 803 1234567")); // spaces
        assertFalse(Validators.isValidNigerianPhone("+23408031234567"));  // 0 kept after +234
        assertFalse(Validators.isValidNigerianPhone("0803-123-4567"));    // dashes
    }

    // ---------- 3. Cohort ID ----------
    @Test
    void cohortId_acceptsValid() {
        assertTrue(Validators.isValidCohortId("SC-2026-0042"));
        assertTrue(Validators.isValidCohortId("SC-2020-9999"));
        assertTrue(Validators.isValidCohortId("SC-2029-0001"));
    }

    @Test
    void cohortId_rejectsDeviations() {
        assertFalse(Validators.isValidCohortId(null));
        assertFalse(Validators.isValidCohortId("SC-2019-0042")); // year too early
        assertFalse(Validators.isValidCohortId("SC-2030-0042")); // year too late
        assertFalse(Validators.isValidCohortId("SC-2026-0000")); // all zeros
        assertFalse(Validators.isValidCohortId("sc-2026-0042")); // lowercase
        assertFalse(Validators.isValidCohortId("SC-2026-042"));  // 3 digits
        assertFalse(Validators.isValidCohortId("SC-2026-00420"));// 5 digits
        assertFalse(Validators.isValidCohortId("SC2026-0042"));  // missing hyphen
    }

    // ---------- 4. Strong password ----------
    @Test
    void strongPassword_acceptsValid_includingBoundaries() {
        assertTrue(Validators.isValidStrongPassword("Abcdefg!12"));            // exactly 10
        assertTrue(Validators.isValidStrongPassword("Abcdefghijklmnopq!12"));  // exactly 20
        assertTrue(Validators.isValidStrongPassword("Zebra$crossing99"));
    }

    @Test
    void strongPassword_rejectsDeviations() {
        assertFalse(Validators.isValidStrongPassword(null));
        assertFalse(Validators.isValidStrongPassword("Abcdef!12"));             // 9 chars
        assertFalse(Validators.isValidStrongPassword("Abcdefghijklmnopqr!12")); // 21 chars
        assertFalse(Validators.isValidStrongPassword("Abcdefg!1x"));            // only one digit
        assertFalse(Validators.isValidStrongPassword("abcdefg!12"));            // no uppercase
        assertFalse(Validators.isValidStrongPassword("Abcdefg12x"));            // no special
        assertFalse(Validators.isValidStrongPassword("Abcdefg#12"));            // # not allowed here
        assertFalse(Validators.isValidStrongPassword("Abcdef g!12"));           // space
    }

    // ---------- 5. Username ----------
    @Test
    void username_acceptsValid() {
        assertTrue(Validators.isValidUsername("ada_1"));          // exactly 5
        assertTrue(Validators.isValidUsername("abcdefghijkl"));   // exactly 12
        assertTrue(Validators.isValidUsername("Ada_Lovelace"));
    }

    @Test
    void username_rejectsDeviations() {
        assertFalse(Validators.isValidUsername(null));
        assertFalse(Validators.isValidUsername("ada1"));           // 4 chars
        assertFalse(Validators.isValidUsername("abcdefghijklm"));  // 13 chars
        assertFalse(Validators.isValidUsername("1ada_x"));         // starts with digit
        assertFalse(Validators.isValidUsername("_adaxx"));         // starts with underscore
        assertFalse(Validators.isValidUsername("ada_x_"));         // ends with underscore
        assertFalse(Validators.isValidUsername("ada-xx"));         // hyphen
        assertFalse(Validators.isValidUsername("ada xx"));         // space
    }

    // ---------- 6. Password that must not contain the username ----------
    @Test
    void passwordFor_acceptsValid() {
        assertTrue(Validators.isValidPasswordFor("ada_01", "Str0ng#Pass"));
        assertTrue(Validators.isValidPasswordFor("ada_01", "a#1bcdef"));            // exactly 8
        assertTrue(Validators.isValidPasswordFor("ada_01", "abcdefghijkl#_+1"));    // exactly 16
    }

    @Test
    void passwordFor_rejectsDeviations() {
        assertFalse(Validators.isValidPasswordFor("ada_01", null));
        assertFalse(Validators.isValidPasswordFor(null, "Str0ng#Pass"));
        assertFalse(Validators.isValidPasswordFor("ada_01", "a#1bcde"));            // 7 chars
        assertFalse(Validators.isValidPasswordFor("ada_01", "abcdefghijkl#_+12"));  // 17 chars
        assertFalse(Validators.isValidPasswordFor("ada_01", "Str0ng #Pass"));       // space
        assertFalse(Validators.isValidPasswordFor("ada_01", "Strong#Pass"));        // no digit
        assertFalse(Validators.isValidPasswordFor("ada_01", "Str0ngPass9"));        // no special
        assertFalse(Validators.isValidPasswordFor("ada_01", "Str0ng!Pass"));        // ! not allowed here
        assertFalse(Validators.isValidPasswordFor("ada_01", "ADA_01#pass9"));       // contains username, any case
    }
}
```

---

## How grading works

Send your `Main.java` when ready. It will be graded on four things:

1. **Correctness:** do all tests pass?
2. **Explanation:** does every line have a comment that says *why*, not just *what*?
3. **Robustness:** are `null` and the boundary lengths handled?
4. **Design:** are patterns compiled once rather than on every call, and is the logic readable?

When a test fails, don't patch blindly. Ask what the regex engine is actually doing with that input, and write down what you found.