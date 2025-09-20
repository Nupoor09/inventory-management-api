package com.invemtory.inventory_management.service;

import com.invemtory.inventory_management.entity.Book;
import com.invemtory.inventory_management.entity.Location;
import com.invemtory.inventory_management.repository.BookRepository;
import com.invemtory.inventory_management.repository.InventoryQuantityRepository;
import com.invemtory.inventory_management.entity.InventoryQuantity;
import com.invemtory.inventory_management.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubmitBookservice {

    @Autowired
    private InventoryQuantityRepository inventoryQuantityRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LocationRepository locationRepository;

    public InventoryQuantity submitBookservice(InventoryQuantity inventoryQuantity) {
        Optional<Book> book = this.bookRepository.findById(inventoryQuantity.getBookId());
        if (!book.isPresent()) {
            throw new RuntimeException("Book not found");
        }
        Optional<Location> location = this.locationRepository.findById(inventoryQuantity.getLocationId());
        if (!location.isPresent()) {
            throw new RuntimeException("Location not found");
        }
        inventoryQuantityRepository.save(inventoryQuantity);
        return inventoryQuantity;
    }


    public Map<String, Object> getBookDetailsByAvailableLocation(Long bookId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        List<InventoryQuantity> inventoryList = inventoryQuantityRepository.findByBookId(bookId)
                .stream()
                .filter(iq -> iq.getQuantity() > 0)
                .collect(Collectors.toList());

        List<Long> locationIds = inventoryList.stream()
                .map(InventoryQuantity::getLocationId)
                .distinct()
                .collect(Collectors.toList());

        List<Location> locations = locationRepository.findByIdIn(locationIds);

        Map<Long, Location> locationMap = locations.stream()
                .collect(Collectors.toMap(Location::getId, loc -> loc));

        List<Map<String, Object>> locationResponse = inventoryList.stream().map(iq -> {
            Location location = locationMap.get(iq.getLocationId());

            Map<String, Object> locMap = new LinkedHashMap<>();
            locMap.put("id", location.getId());
            locMap.put("name", location.getName());
            locMap.put("availableQty", iq.getQuantity());

            return locMap;
        }).collect(Collectors.toList());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", book.getId());
        response.put("name", book.getName());
        response.put("locations", locationResponse);

        return response;
    }



}
