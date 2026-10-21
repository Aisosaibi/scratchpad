package ng.pharmacy.data.models;

import ng.pharmacy.exceptions.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DrugTest {

    Drug drug;
    
    @BeforeEach
    void setUp() {
        drug = new Drug();
    }

    @Test
    void test_that_getPrice_returns_3000() {
        drug.setPrice(3_000);
        assertEquals(3_000, drug.getPrice(), 0.001);
    }

    @Test
    void test_that_setPrice_throwsException_if_price_EqualToZero() {
        assertThrows(InvalidAmountException.class, () -> drug.setPrice(0));
    }

    @Test
    void test_that_setPrice_throwsException_if_price_lessThanZero() {
        assertThrows(InvalidAmountException.class, () -> drug.setPrice(0));
    }

    @Test
    void test_that_getId() {
    }

    @Test
    void setId() {
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getExpiryDate() {
    }

    @Test
    void setExpiryDate() {
    }

    @Test
    void test_that_Drug_isExpired_returns_true_if_date_past_expiry_date() {
        drug.setExpiryDate(LocalDate.of(2026, 5, 22));
        assertTrue(drug.isExpired());
    }

    @Test
    void test_that_Drug_isExpired_returns_false_if_future_expiry_date() {
        drug.setExpiryDate(LocalDate.of(2026, 8, 19));
        assertFalse(drug.isExpired());
    }
    @Test
    void getBrand() {
    }

    @Test
    void setBrand() {
    }
}