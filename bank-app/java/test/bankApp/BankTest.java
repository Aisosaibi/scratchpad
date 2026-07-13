package bankApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BankTest {
    Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void testThatRegisteredCustomerCanBeFoundByAccountNumber() {
        Account created = bank.registerCustomer("Aisosaibi", "1234", "1234");
        Account found = bank.findAccount(created.getAccountNumber());
        assertEquals(created, found);
    }

    @Test
    public void testThatConsecutivelyRegisteredAccountsGetDifferentNumbers() {
        Account first = bank.registerCustomer("Aisosaibi", "1234", "1234");
        Account second = bank.registerCustomer("Priscilla", "5678", "5678");
        assertNotEquals(first.getAccountNumber(), second.getAccountNumber());
    }
}
