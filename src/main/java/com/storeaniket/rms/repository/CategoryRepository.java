package com.storeaniket.rms.repository;

import com.storeaniket.rms.model.CategoryDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryDB, Long> {

}
