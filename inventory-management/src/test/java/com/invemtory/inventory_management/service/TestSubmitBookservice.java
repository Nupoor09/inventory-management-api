package com.invemtory.inventory_management.service;
import com.invemtory.inventory_management.entity.Book;
import com.invemtory.inventory_management.entity.InventoryQuantity;
import com.invemtory.inventory_management.entity.Location;
import com.invemtory.inventory_management.repository.BookRepository;
import com.invemtory.inventory_management.repository.InventoryQuantityRepository;
import com.invemtory.inventory_management.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestSubmitBookservice {

    @InjectMocks
    private SubmitBookservice submitBookservice;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private InventoryQuantityRepository inventoryQuantityRepository;

    @Mock
    private LocationRepository locationRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetBookDetailsByAvailableLocation() {
        Long bookId = 1L;

        Book book = new Book();
        book.setId(bookId);
        book.setName("Test Book");

        InventoryQuantity iq1 = new InventoryQuantity();
        iq1.setBookId(bookId);
        iq1.setLocationId(10L);
        iq1.setQuantity(5L);

        InventoryQuantity iq2 = new InventoryQuantity();
        iq2.setBookId(bookId);
        iq2.setLocationId(20L);
        iq2.setQuantity(0L);  // should be ignored

        Location loc1 = new Location();
        loc1.setId(10L);
        loc1.setName("Loc A");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(inventoryQuantityRepository.findByBookId(bookId))
                .thenReturn(Arrays.asList(iq1, iq2));
        when(locationRepository.findByIdIn(Collections.singletonList(10L)))
                .thenReturn(Collections.singletonList(loc1));

        Map<String, Object> result = submitBookservice.getBookDetailsByAvailableLocation(bookId);

        assertEquals(bookId, result.get("id"));
        assertEquals("Test Book", result.get("name"));

        List<Map<String, Object>> locations = (List<Map<String, Object>>) result.get("locations");
        assertEquals(1, locations.size());

        Map<String, Object> loc = locations.get(0);
        assertEquals(10L, loc.get("id"));
        assertEquals("Loc A", loc.get("name"));
        assertEquals(5L, loc.get("availableQty"));
    }
}
