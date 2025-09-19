package com.invemtory.inventory_management.controller;

import com.invemtory.inventory_management.entity.Book;
import com.invemtory.inventory_management.entity.InventoryQuantity;
import com.invemtory.inventory_management.service.SubmitBookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.xpath.XPathVariableResolver;
import java.util.HashMap;
import java.util.Map;

@RestController
public class InventoryController {

   @Autowired
   private SubmitBookservice submitBookservice;

    @GetMapping("test")
    public ResponseEntity<String> createBookRecord() {
        return new ResponseEntity<>("Welcome to the app", HttpStatus.OK);
    }

    @PostMapping("submitBook/{locationID}/{bookID}/{quantity}")
    public ResponseEntity<InventoryQuantity> addBookDetails(@RequestBody Book book, @PathVariable("locationId") Long locationId,
                                                            @PathVariable Long bookID, @PathVariable Long quantity) {
        InventoryQuantity iq = this.submitBookservice.submitBookservice(locationId,bookID,quantity);
        return new ResponseEntity<InventoryQuantity>(iq, HttpStatus.OK);
    }
}
