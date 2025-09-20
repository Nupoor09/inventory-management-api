package com.invemtory.inventory_management.controller;

import com.invemtory.inventory_management.entity.Book;
import com.invemtory.inventory_management.repository.BookRepository;
import com.invemtory.inventory_management.repository.InventoryQuantityRepository;
import com.invemtory.inventory_management.entity.InventoryQuantity;
import com.invemtory.inventory_management.service.SubmitBookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class InventoryController {

   @Autowired
   private SubmitBookservice bookservice;

   @Autowired
   private BookRepository bookRepository;

   @Autowired
   private InventoryQuantityRepository inventoryQuantityRepository;

    @PostMapping("submitBook")
    public ResponseEntity<InventoryQuantity> addBookDetails(@RequestBody InventoryQuantity inventoryQuantity) {
        InventoryQuantity iq = this.bookservice.submitBookservice(inventoryQuantity);
        return new ResponseEntity<InventoryQuantity>(iq, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(this.bookRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("getBookDetails/{bookId}")
    public ResponseEntity<Map<String, Object>> getBookDetails(@PathVariable Long bookId) {
        Map<String, Object> iq = this.bookservice.getBookDetailsByAvailableLocation(bookId);

        return new ResponseEntity<Map<String, Object>>(iq, HttpStatus.OK);

    }


}
