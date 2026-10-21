package ng.pharmacy.data.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testUser_checkPasswordReturnsTrue_ifPasswordIsCorrect() {
        User user = new User();
        user.setFullName("Full Name");
        user.setUsername("username");
        user.setPassword("password123");
        assertTrue(user.checkPassword("password123"));
    }

    @Test
    public void testUser_checkPasswordReturnsFalse_ifPasswordIsIncorrect() {
        User user = new User();
        user.setFullName("Full Name");
        user.setUsername("username");
        user.setPassword("password123");
        assertFalse(user.checkPassword("Password123"));
    }
}