package com.invemtory.inventory_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "InventoryQuantity")
public class InventoryQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     private Long bookId;
     private Long locationId;
     private Long quantity;

    public InventoryQuantity() {}

    public void setId(Long id) { this.id = id;}

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public Long getQuantity() {
        return quantity;
    }


    public InventoryQuantity(Long id, Long bookId, Long locationId, Long quantity) {
        this.id = id;
        this.bookId = bookId;
        this.locationId = locationId;
        this.quantity = quantity;
    }
}
