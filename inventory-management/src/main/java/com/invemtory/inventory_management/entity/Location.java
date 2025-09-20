package com.invemtory.inventory_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "location") // 🔥 Best to use lowercase for H2
public class Location {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    public Location() {}

    public Location(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
