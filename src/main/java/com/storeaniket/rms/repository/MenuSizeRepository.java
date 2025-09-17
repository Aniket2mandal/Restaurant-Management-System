package com.storeaniket.rms.repository;

import com.storeaniket.rms.model.MenuSizeDB;
import com.storeaniket.rms.model.SizeDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuSizeRepository extends JpaRepository<MenuSizeDB, Long> {
    List<MenuSizeDB> findByMenuId(Long menuId);
    List<MenuSizeDB> findBySizeId(Long sizeId);
}
