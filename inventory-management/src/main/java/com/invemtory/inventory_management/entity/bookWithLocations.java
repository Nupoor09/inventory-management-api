package com.invemtory.inventory_management.entity;

import java.util.List;

public class bookWithLocations {

    private Long id;
    private String name;
    private List<Location> locations;


    // Constructors
    public bookWithLocations(Long id, String name, List<Location> locations) {
        this.id = id;
        this.name = name;
        this.locations = locations;
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

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getName() {
        return name;
    }

    public List<Location> getLocations() {
        return locations;
    }
}

