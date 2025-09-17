package com.storeaniket.rms.repository;

import com.storeaniket.rms.model.MenuDB;
import com.storeaniket.rms.model.MenuOptionDB;
import com.storeaniket.rms.model.OptionDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuOptionRepository extends JpaRepository<MenuOptionDB, Long> {
//    List<MenuOptionDB> findByMenuAndOption(MenuDB menu, OptionDB option);
List<MenuOptionDB> findByMenuIdAndOptionId(Long menuId, Long optionId);
    List<MenuOptionDB> findByMenuId(Long id);
}
