package ng.pharmacy.data.models;

import ng.pharmacy.exceptions.InvalidAmountException;

import java.time.LocalDate;

public class Drug {
    private int price;
    private String name;
    private long id;
    private LocalDate expiryDate;
    private String brand;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        boolean invalidPrice = price <= 0;
        if (invalidPrice) throw new InvalidAmountException("Price must be greater than 0");
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
