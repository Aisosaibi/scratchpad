package ng.pharmacy.data.models;

import java.time.LocalDateTime;
import java.util.List;

public class Sale {
    private long id;
    private LocalDateTime dateTime;
    private String username;
    private List<SaleItem> items;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

    public int getTotalAmount() {
        int totalAmount = 0;
        for (SaleItem item : items) {
            totalAmount += item.getLineTotal();
        }
        return totalAmount;
    }
}