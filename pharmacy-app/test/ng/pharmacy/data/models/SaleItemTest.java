package ng.pharmacy.data.models;

import ng.pharmacy.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaleItemTest {
    @Test
    public void testUnitPriceAtSale_throwsValidationException_ifEqualTo0() {
        assertThrows(ValidationException.class, ()-> new SaleItem().setUnitPriceAtSale(0));
    }
}
