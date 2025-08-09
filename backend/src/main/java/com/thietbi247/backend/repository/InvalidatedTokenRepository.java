package com.thietbi247.backend.repository;

import com.thietbi247.backend.entity.Device;
import com.thietbi247.backend.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}
