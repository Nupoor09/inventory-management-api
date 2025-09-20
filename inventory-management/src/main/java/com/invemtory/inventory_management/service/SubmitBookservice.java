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
//        Book book = bookRepository.findById(bookId)
//                .orElseThrow(() -> new RuntimeException("Book not found"));
//
//        List<InventoryQuantity> inventoryList = inventoryQuantityRepository.findByBookId(bookId);
//        List<Location> locations = this.locationRepository.findByIdIn(inventoryList.stream().map(InventoryQuantity::getLocationId).collect(Collectors.toList()));
//        Map<String,Object> map = new HashMap<>();
//        map.put("id",book.getId());
//        map.put("name",book.getName());
//
//        map.put("locations",locations);
//
//        return map;
//        Book book = bookRepository.findById(bookId)
//                .orElseThrow(() -> new RuntimeException("Book not found"));
//
//// Fetch all inventory records for the book
//        List<InventoryQuantity> inventoryList = inventoryQuantityRepository.findByBookId(bookId);
//
//// Filter only those with quantity > 0
//        inventoryList = inventoryList.stream()
//                .filter(iq -> iq.getQuantity() > 0)
//                .collect(Collectors.toList());
//
//// Construct the response
//        Map<String, Object> response = new HashMap<>();
//        response.put("id", book.getId());
//        response.put("name", book.getName());
//
//// Build the locations list
//        List<Map<String, Object>> locationList = inventoryList.stream().map(iq -> {
//            Map<String, Object> locMap = new HashMap<>();
//            locMap.put("id", iq.getLocation().getId());
//            locMap.put("name", iq.getLocation().getName());
//            locMap.put("availableQty", iq.getQuantity());
//            return locMap;
//        }).collect(Collectors.toList());
//
//        response.put("locations", locationList);
//
//        return response;

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

// Fetch inventory entries for the book
        List<InventoryQuantity> inventoryList = inventoryQuantityRepository.findByBookId(bookId)
                .stream()
                .filter(iq -> iq.getQuantity() > 0)
                .collect(Collectors.toList());

// Get list of location IDs from inventory
        List<Long> locationIds = inventoryList.stream()
                .map(InventoryQuantity::getLocationId)
                .distinct()
                .collect(Collectors.toList());

// Fetch all locations for those IDs
        List<Location> locations = locationRepository.findByIdIn(locationIds);

// Create a map of locationId -> Location for easy lookup
        Map<Long, Location> locationMap = locations.stream()
                .collect(Collectors.toMap(Location::getId, loc -> loc));

// Construct the location list for response
        List<Map<String, Object>> locationResponse = inventoryList.stream().map(iq -> {
            Location location = locationMap.get(iq.getLocationId());

            Map<String, Object> locMap = new LinkedHashMap<>();
            locMap.put("id", location.getId());
            locMap.put("name", location.getName());
            locMap.put("availableQty", iq.getQuantity());

            return locMap;
        }).collect(Collectors.toList());

// Final response
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("id", book.getId());
        response.put("name", book.getName());
        response.put("locations", locationResponse);

        return response;
    }



}
