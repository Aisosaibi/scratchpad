package org.aispace;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorsTest {

    // ---------- 1. Email ----------
    @Test
    void email_acceptsValidShapes() {
        assertTrue(Validators.isValidEmail("t.okafor@staff.semicolon.africa"));
        assertTrue(Validators.isValidEmail("p.nwankwo@native.semicolon.africa"));
    }

    @Test
    void email_rejectsDeviations() {
        assertFalse(Validators.isValidEmail(null));
        assertFalse(Validators.isValidEmail(""));
        assertFalse(Validators.isValidEmail("T.okafor@staff.semicolon.africa"));      // uppercase initial
        assertFalse(Validators.isValidEmail("tokafor@staff.semicolon.africa"));       // missing dot
        assertFalse(Validators.isValidEmail("tt.okafor@staff.semicolon.africa"));     // two-letter initial
        assertFalse(Validators.isValidEmail("t.o@staff.semicolon.africa"));           // surname too short
        assertFalse(Validators.isValidEmail("t.okafor@intern.semicolon.africa"));     // wrong group
        assertFalse(Validators.isValidEmail("t.okafor@staff.semicolon.com"));         // wrong TLD
        assertFalse(Validators.isValidEmail("t.okafor@staffxsemicolon.africa"));      // unescaped-dot trap
        assertFalse(Validators.isValidEmail(" t.okafor@staff.semicolon.africa"));     // leading space
        assertFalse(Validators.isValidEmail("t.okafor@staff.semicolon.africa.evil")); // trailing junk
        assertFalse(Validators.isValidEmail("hi t.okafor@staff.semicolon.africa"));   // matches() vs find()
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

    // ---------- 6. Passwords that are suitable ----------
    @Test
    void password_acceptsValid() {
        assertTrue(Validators.isValidPassword("Str0ng#Pass"));
        assertTrue(Validators.isValidPassword("a#1bcdef"));            // exactly 8
        assertTrue(Validators.isValidPassword("abcdefghijkl#_+1"));    // exactly 16
    }

    @Test
    void password_rejectsDeviations() {
        assertFalse(Validators.isValidPassword(null));
        assertFalse(Validators.isValidPassword("a#1bcde"));            // 7 chars
        assertFalse(Validators.isValidPassword("abcdefghijkl#_+12"));  // 17 chars
        assertFalse(Validators.isValidPassword("Str0ng #Pass"));       // space
        assertFalse(Validators.isValidPassword("Strong#Pass"));        // no digit
        assertFalse(Validators.isValidPassword("Str0ngPass9"));        // no special
        assertFalse(Validators.isValidPassword("Str0ng!Pass"));        // ! not allowed here
    }

    // ---------- 7. Password that must not contain the username ----------
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