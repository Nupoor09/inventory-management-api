package com.invemtory.inventory_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    private Long id;
    private String name;

    public Book() {}
    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
