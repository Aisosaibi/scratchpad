package ng.pharmacy.data.models;

import ng.pharmacy.exceptions.ValidationException;

public class SaleItem {
    private long id;
    private String medicineName;
    private int unitPriceAtSale;
    private int quantity;

    public int getUnitPriceAtSale() {
        return unitPriceAtSale;
    }

    public void setUnitPriceAtSale(int unitPriceAtSale) {
        if (unitPriceAtSale > 0) {
            this.unitPriceAtSale = unitPriceAtSale;
        } else { throw new ValidationException("Selling Price cannot be less than 0"); }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) { throw new ValidationException("Quantity cannot be 0 or less."); }
        this.quantity = quantity;
    }

    public int getLineTotal(){
        return unitPriceAtSale *  quantity;
    }
}