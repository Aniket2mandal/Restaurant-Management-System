package com.storeaniket.rms.repository;

import com.storeaniket.rms.model.SizeDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SizeRepository extends JpaRepository<SizeDB, Long> {
    List<SizeDB> findBySizeGroupId(Long sizeGroupId);
}
