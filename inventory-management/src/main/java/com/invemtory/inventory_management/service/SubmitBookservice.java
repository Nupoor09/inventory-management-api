package com.invemtory.inventory_management.service;

import com.invemtory.inventory_management.Repository.InventoryQuantityRepository;
import com.invemtory.inventory_management.entity.InventoryQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SubmitBookservice {

    @Autowired
    private InventoryQuantityRepository inventoryQuantityRepository;

    public InventoryQuantity submitBookservice(Long locationId, Long bookID, Long quantity) {
        InventoryQuantity iq = new InventoryQuantity();
        iq.setQuantity(quantity);
        iq.setLocationId(locationId);
        iq.setBookId(bookID);

        inventoryQuantityRepository.save(iq);
        return iq;
    }


}
