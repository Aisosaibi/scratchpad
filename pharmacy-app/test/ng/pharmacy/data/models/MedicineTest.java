package ng.pharmacy.data.models;

import ng.pharmacy.exceptions.InsufficientStockException;
import ng.pharmacy.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

public class MedicineTest {
    @Test
    public void testMedicine_isExpiredReturnsTrue() {
        Medicine medicine = new Medicine();
//        medicine.setExpiryDate(LocalDate.of(2026, Month.JULY, 27));
//        medicine.setExpiryDate(LocalDate.parse("2026-07-27"));
        medicine.setExpiryDate(LocalDate.now().minusDays(1));
        assertTrue(medicine.isExpired());
    }

    @Test
    public void testMedicine_isExpiredReturnsFalse() {
        Medicine medicine = new Medicine();
        medicine.setExpiryDate(LocalDate.of(2026, Month.DECEMBER, 25));
        assertFalse(medicine.isExpired());
    }

    @Test
    public void testMedicine_matchesSearchReturnsTrue_ifBrandNameMatchesCompletely() {
        Medicine medicine = new Medicine();
        medicine.setBrandName("TestBrand");
        medicine.setGenericName("TestGenericName");
        assertTrue(medicine.matchesSearch("TestBrand"));
    }

    @Test
    public void testMedicine_matchesSearchReturnsTrue_ifBrandNameMatchesPartially() {
        Medicine medicine = new Medicine();
        medicine.setBrandName("TestBrand");
        medicine.setGenericName("TestGenericName");
        assertTrue(medicine.matchesSearch("TestB"));
    }

    @Test
    public void testMedicine_matchesSearchReturnsTrue_ifGenericNameMatchesCompletely() {
        Medicine medicine = new Medicine();
        medicine.setBrandName("TestBrand");
        medicine.setGenericName("TestGenericName");
        assertTrue(medicine.matchesSearch("TestGenericName"));
    }

    @Test
    public void testMedicine_matchesSearchReturnsTrue_ifGenericNameMatchesPartially() {
        Medicine medicine = new Medicine();
        medicine.setBrandName("TestBrand");
        medicine.setGenericName("TestGenericName");
        assertTrue(medicine.matchesSearch("TestGen"));
    }

    @Test
    public void testMedicine_matchesSearchReturnsFalse_ifBrandNameOrGenericNameDoesNotMatch() {
        Medicine medicine = new Medicine();
        medicine.setBrandName("TestBrand");
        medicine.setGenericName("TestGenericName");
        assertFalse(medicine.matchesSearch("Program"));
    }

    @Test
    public void testMedicine_hasSufficientStockReturnsTrue() {
        Medicine medicine = new Medicine();
        medicine.setQuantityInStock(50);
        assertTrue(medicine.hasSufficientStock(50));
    }

    @Test
    public void testMedicine_reduceStockReducesStockBy10() {
        Medicine medicine = new Medicine();
        medicine.setQuantityInStock(50);
        medicine.reduceStock(10);
        assertEquals(40, medicine.getQuantityInStock());
    }

    @Test
    public void testMedicine_reduceStock_throwsValidationExceptionForQtyLessThan0() {
        Medicine medicine = new Medicine();
        medicine.setQuantityInStock(50);
        assertThrows(ValidationException.class, () -> medicine.reduceStock(-10));
    }

    @Test
    public void testMedicine_reduceStock_throwsInsufficientStockExceptionForQtyLessThan0() {
        Medicine medicine = new Medicine();
        medicine.setQuantityInStock(50);
        assertThrows(InsufficientStockException.class, () -> medicine.reduceStock(60));
    }
}
