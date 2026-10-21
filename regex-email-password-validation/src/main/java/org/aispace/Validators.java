package org.aispace;

import java.util.regex.Pattern;

public class Validators { // the class the test file expects

    private static final Pattern VALID_EMAIL = Pattern.compile(
            "^[a-z]"
                + "\\.[a-z]{2,20}"
                + "@(?:native|staff)"
                + "\\.semicolon\\.africa$");

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return VALID_EMAIL.matcher(email).matches();
    }


    private static final Pattern VALID_NIGERIAN_PHONE = Pattern.compile(
            "(?:0|\\+234)[789]\\d{9}$");

    public static boolean isValidNigerianPhone(String phone) {
        if (phone == null) return false;
        return VALID_NIGERIAN_PHONE.matcher(phone).matches();
    }


    // Compiled once when the class loads, then reused by every call.
    private static final Pattern COHORT_ID = Pattern.compile(
            "SC-"            // literal uppercase SC and a hyphen; a hyphen outside [] is not special
                    + "202[0-9]"       // year: fixed "202" plus one digit, so only 2020-2029 pass
                    + "-"              // literal hyphen between year and number
                    + "(?!0000)"       // negative lookahead: the next four characters must not be 0000
                    + "\\d{4}");       // exactly four digits (\\d is \d, with the backslash escaped for Java)

    public static boolean isValidCohortId(String id) { // takes the text to check, returns yes or no
        if (id == null)
            return false;         // matcher(null) would throw an exception, so guard first because null is not a valid ID, and we reject it quietly
        return COHORT_ID.matcher(id).matches();         // matches() demands the WHOLE string fit, so "xSC-2026-0042" fails
    }


    // Compiled once. matches() anchors both ends, so no ^ or $ is needed.
    private static final Pattern STRONG_PASSWORD = Pattern.compile(
            "(?=.*[A-Z])"        // lookahead: somewhere ahead there is at least one uppercase letter
                    + "(?=(?:.*\\d){2})"   // lookahead: "anything then a digit" can happen twice, so two digits or more
                    + "(?=.*[!$%&])"       // lookahead: at least one of the allowed specials ! $ % &
                    + "\\S{10,20}");       // the real match: 10 to 20 non-whitespace characters, whole string

    public static boolean isValidStrongPassword(String password) { // takes the candidate, returns yes or no
        if (password == null)
            return false;                                   // guard: a null would throw inside matcher() because null is rejected without an exception
        return STRONG_PASSWORD.matcher(password).matches();         // every lookahead must pass and the body must span the whole string
    }


    private static final Pattern VALID_PASSWORD = Pattern.compile(
            "^(?=\\S{8,16}$)"
                    + "(?=.*\\d)"
                    + "(?=.*[@#_+])"
                    + ".*$");

    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        return VALID_PASSWORD.matcher(password).matches();
    }

    public static boolean isValidPasswordFor(String username, String password) {
        if (password == null || username == null) return false;
        if (!isValidPassword(password)) return false;  // reuse the shape rules (length, no spaces, digit, special)
        return !password.toLowerCase().contains(username.toLowerCase());
    }


    private static final Pattern VALID_USERNAME = Pattern.compile(
            "[A-Za-z]"               // first character: a letter of either case, which enforces "starts with a letter"
                + "[A-Za-z0-9_]{3,10}"     // middle run: letters, digits or underscores (3 to 10 characters)
                + "[A-Za-z0-9]");          // last character: NOT an underscore, which enforces "doesn't end with _"

//            "^(?=[A-Za-z])"
//                + "^(?=\\w{5,12}$)"
//                + ".*"
//                + "(?<!_)$");

    public static boolean isValidUsername(String username) {
        if (username == null) return false;
        return VALID_USERNAME.matcher(username).matches();
    }
}
