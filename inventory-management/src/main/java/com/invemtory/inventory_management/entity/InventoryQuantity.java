package com.invemtory.inventory_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory_quantity")
public class InventoryQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "id")
     private Long id;
     @Column(name = "book_id")
     private Long bookId;
     @Column(name = "location_id")
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
