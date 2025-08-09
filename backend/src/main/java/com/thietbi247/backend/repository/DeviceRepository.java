package com.thietbi247.backend.repository;

import com.thietbi247.backend.entity.Device;
import com.thietbi247.backend.entity.ReturnDevice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, String> {
    List<Device> findByProductNameContainingIgnoreCase(String productName);

    Optional<Device> findByProductName(String productName);



}
