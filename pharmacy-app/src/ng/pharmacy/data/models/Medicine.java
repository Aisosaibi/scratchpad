package ng.pharmacy.data.models;

import ng.pharmacy.exceptions.InsufficientStockException;
import ng.pharmacy.exceptions.ValidationException;

import java.time.LocalDate;

public class Medicine {
    private long id;
    private String brandName;
    private String genericName;
    private String strength;
    private String dosageForm;
    private String manufacturer;
    private String batchNumber;
    private LocalDate expiryDate;
    private int unitPrice;
    private int quantityInStock;

    public boolean isExpired() {
        if (expiryDate == null) {
            return false;
        }
//        return !LocalDate.now().isBefore(expiryDate);
        return LocalDate.now().isAfter(expiryDate);
    }

    public boolean matchesSearch(String query) {
        String pattern = query.toLowerCase();
        return nameContains(genericName, pattern) || nameContains(brandName, pattern);
    }

    private boolean nameContains(String field, String pattern) {
        return field != null && field.toLowerCase().contains(pattern);
    }

    public boolean hasSufficientStock(int quantity) {
        return quantity <= quantityInStock;
    }

    private void validateDispenseQty(int quantity) {
        if (quantity < 0) throw new ValidationException("Quantity is less than 0");
    }

    public void reduceStock(int quantity) {
        validateDispenseQty(quantity);
        if (!hasSufficientStock(quantity)) {
            throw new InsufficientStockException(
                    "Insufficient stock. Available: " + quantityInStock + ", Requested: " + quantity);
        }
        quantityInStock -= quantity;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        if (expiryDate == null) {
            throw new ValidationException("Drug must have an expiry date");
        }
        this.expiryDate = expiryDate;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        if (unitPrice <= 0) { throw new ValidationException("Unit price cannot be 0 or less."); }
        this.unitPrice = unitPrice;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", genericName='" + genericName + '\'' +
                ", strength='" + strength + '\'' +
                ", dosageForm='" + dosageForm + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                ", expiryDate=" + expiryDate +
                ", unitPrice=" + unitPrice +
                ", quantityInStock=" + quantityInStock +
                '}';
    }

//    @Override
//    public String toString() {
//        return String.format("[%d] %s (%s) %s %s - Rs%d - Stock: %d",
//                id, brandName, genericName, strength, dosageForm, unitPrice, quantityInStock);
//    }
}
