package ng.pharmacy.data.models;

import ng.pharmacy.exceptions.InvalidAmountException;

public class DispensedDrug {
    private long id;
    private Drug drug;
    private int quantity;

    public DispensedDrug(Drug drug, int quantity) {
        if (quantity <= 0) {
            throw new InvalidAmountException("Quantity must be greater than 0");
        }
        this.drug = drug;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    public Drug getDrug() {
        return drug;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return drug.getPrice() * quantity;
    }
}
