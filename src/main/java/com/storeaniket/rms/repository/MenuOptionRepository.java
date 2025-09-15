package com.storeaniket.rms.repository;

import com.storeaniket.rms.model.MenuOptionDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOptionRepository extends JpaRepository<MenuOptionDB, Long> {
}
