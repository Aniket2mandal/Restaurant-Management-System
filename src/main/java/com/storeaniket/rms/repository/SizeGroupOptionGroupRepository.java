package com.storeaniket.rms.repository;

import com.storeaniket.rms.model.SizeGroupOptionGroupDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SizeGroupOptionGroupRepository extends JpaRepository<SizeGroupOptionGroupDB, Long> {
    List<SizeGroupOptionGroupDB> findBySizeGroupId(Long sizeGroupId);
}
