package com.storeaniket.rms.repository;

import com.storeaniket.rms.model.OptionDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OptionRepository extends JpaRepository<OptionDB, Long> {
    List<OptionDB> findByOptionGroupId(Long id);
}
