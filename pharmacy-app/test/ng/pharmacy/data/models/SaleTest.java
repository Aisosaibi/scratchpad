package ng.pharmacy.data.models;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaleTest {
    @Test
    public void testSale_getTotalAmount() {
        SaleItem item1 =  new SaleItem();
        item1.setQuantity(1); item1.setUnitPriceAtSale(500);
        SaleItem item2 =  new SaleItem();
        item2.setQuantity(2); item2.setUnitPriceAtSale(650);
        List<SaleItem> items = new ArrayList<>();
        items.add(item1); items.add(item2);
        Sale sale = new Sale();
        sale.setItems(items);
        assertEquals(1800, sale.getTotalAmount());
    }
}
