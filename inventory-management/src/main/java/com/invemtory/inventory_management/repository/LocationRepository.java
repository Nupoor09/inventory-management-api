package com.invemtory.inventory_management.repository;

import com.invemtory.inventory_management.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByIdIn(List<Long> locationIds);
}
