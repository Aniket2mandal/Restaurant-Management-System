package com.storeaniket.rms.repository;

import com.storeaniket.rms.model.MenuDB;
import com.storeaniket.rms.model.MenuSizeDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuDB, Long> {
    List<MenuDB> findByCategoryId(Long categoryId);
    List<MenuDB> findBySizeGroupId(Long sizeGroupId);
}
