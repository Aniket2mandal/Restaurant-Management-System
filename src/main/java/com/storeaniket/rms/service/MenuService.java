package com.storeaniket.rms.service;

import com.storeaniket.rms.dto.MenuDTO;
import com.storeaniket.rms.model.MenuDB;

import java.util.List;

public interface MenuService {
    public String createMenu(MenuDTO menuRequest);
    public String updateMenu(MenuDTO menuRequest);
    public String deleteMenu(Long menuId);
    public MenuDTO getMenu(Long menuId);
    public List<MenuDTO> getAllMenu();
}
