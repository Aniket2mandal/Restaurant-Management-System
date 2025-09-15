package com.storeaniket.rms.service;

import com.storeaniket.rms.dto.MenuSizeDTO;

import java.util.List;

public interface MenuSizeService {
    public String createMenuSize(MenuSizeDTO menuSizeDTO);
    public String updateMenuSize(Long id,MenuSizeDTO menuSizeDTO);
    public String deleteMenuSize(Long menuSizeId);
    public MenuSizeDTO getMenuSize(Long id);
    public List<MenuSizeDTO> getAllMenuSize();

}
