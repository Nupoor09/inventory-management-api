package com.invemtory.inventory_management.controller;

import com.invemtory.inventory_management.entity.Book;
import com.invemtory.inventory_management.entity.InventoryQuantity;
import com.invemtory.inventory_management.repository.BookRepository;
import com.invemtory.inventory_management.repository.InventoryQuantityRepository;
import com.invemtory.inventory_management.service.SubmitBookservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

public class TestInventoryController {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SubmitBookservice bookservice;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private InventoryQuantityRepository inventoryQuantityRepository;

    private InventoryQuantity inventoryQuantity;

    @BeforeEach
    void setUp() {
        inventoryQuantity = new InventoryQuantity();
        inventoryQuantity.setBookId(1L);
        inventoryQuantity.setLocationId(10L);
        inventoryQuantity.setQuantity(5L);
    }

    @Test
    void testSubmitBook() throws Exception {
        Mockito.when(bookservice.submitBookservice(Mockito.any(InventoryQuantity.class))).thenReturn(inventoryQuantity);

        String json = "{ \"bookId\": 1, \"locationId\": 10, \"quantity\": 5 }";

        mockMvc.perform(post("/submitBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(1))
                .andExpect(jsonPath("$.locationId").value(10))
                .andExpect(jsonPath("$.quantity").value(5));
    }

    @Test
    void testGetAllBooks() throws Exception {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setName("Book A");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setName("Book B");

        List<Book> books = Arrays.asList(book1, book2);
        Mockito.when(bookRepository.findAll()).thenReturn(books);

        mockMvc.perform(get("/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Book A"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Book B"));
    }

    @Test
    void testGetBookDetails() throws Exception {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("id", 1L);
        responseMap.put("name", "Book A");

        List<Map<String, Object>> locations = new ArrayList<>();
        Map<String, Object> loc = new HashMap<>();
        loc.put("id", 10L);
        loc.put("name", "Location A");
        loc.put("availableQty", 5);
        locations.add(loc);
        responseMap.put("locations", locations);

        Mockito.when(bookservice.getBookDetailsByAvailableLocation(1L)).thenReturn(responseMap);

        mockMvc.perform(get("/getBookDetails/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Book A"))
                .andExpect(jsonPath("$.locations[0].id").value(10))
                .andExpect(jsonPath("$.locations[0].name").value("Location A"))
                .andExpect(jsonPath("$.locations[0].availableQty").value(5));
    }
}


