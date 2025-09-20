package com.invemtory.inventory_management.repository;

import com.invemtory.inventory_management.entity.InventoryQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryQuantityRepository extends JpaRepository<InventoryQuantity, Long> {

    List<InventoryQuantity> findByBookId(Long bookId);
}
