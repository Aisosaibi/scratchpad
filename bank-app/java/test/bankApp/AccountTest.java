package bankApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {
    Account newAccount;

    @BeforeEach
    public void setUp() {
        newAccount = new Account();
    }

    @Test
    public void testThatNewlyCreatedAccountHasAccountBalanceOfZero() {
        int balance = newAccount.checkBalance("1234");
        assertEquals(0, balance);
    }

    @Test
    public void testThatInvalidPinCannotCheckBalance() {
        assertThrows(InvalidPinException.class, () -> newAccount.checkBalance("5678"));
    }

    @Test
    public void testThatInvalidPinCannotWithdraw() {
        newAccount.deposit(1000);
        assertThrows(InvalidPinException.class, () -> newAccount.withdraw(500, "5678"));
    }

    @Test
    public void testThatDepositAmountThrowsInvalidAmountExceptionIfAmountIsZero() {
        assertEquals(0, newAccount.checkBalance("1234"));
        assertThrows(InvalidAmountException.class, () -> newAccount.deposit(0));
    }

    @Test
    public void testThatDepositAmountThrowsInvalidAmountExceptionIfAmountIsLessThanZero() {
        assertEquals(0, newAccount.checkBalance("1234"));
        assertThrows(InvalidAmountException.class, () -> newAccount.deposit(-100));
    }

    @Test
    public void testThatDepositAmountIncreasesBalanceByAmountsGreaterThanZero() {
        assertEquals(0, newAccount.checkBalance("1234"));
        newAccount.deposit(10_000);
        assertEquals(10_000, newAccount.checkBalance("1234"));
    }

    @Test
    public void testThatCanOnlyWithdrawAmountLessThanBalanceIfPinIsValid() {
        assertEquals(0, newAccount.checkBalance("1234"));
        newAccount.deposit(10_000);
        newAccount.withdraw(5_000, "1234");
        assertEquals(5_000, newAccount.checkBalance("1234"));
    }

    @Test
    public void testThatCanOnlyWithdrawAmountEqualToBalanceIfPinIsValid() {
        assertEquals(0, newAccount.checkBalance("1234"));
        newAccount.deposit(10_000);
        newAccount.withdraw(10_000, "1234");
        assertEquals(0, newAccount.checkBalance("1234"));
    }

    @Test
    public void testThatWithdrawGreaterThanBalanceIfPinIsValidThrowsInsufficientFundsException() {
        assertEquals(0, newAccount.checkBalance("1234"));
        newAccount.deposit(10_000);
        assertThrows(InsufficientFundsException.class, () -> newAccount.withdraw(11_000, "1234"));
    }

    @Test
    public void testThatWithdrawalAmountCannotBeEqualToZero() {
        assertEquals(0, newAccount.checkBalance("1234"));
        newAccount.deposit(10_000);
        assertThrows(InvalidAmountException.class, () -> newAccount.withdraw(0, "1234"));
    }

    @Test
    public void testThatWithdrawalAmountCannotBeLessThanZero() {
        assertEquals(0, newAccount.checkBalance("1234"));
        newAccount.deposit(10_000);
        assertThrows(InvalidAmountException.class, () -> newAccount.withdraw(-1, "1234"));
    }
}
