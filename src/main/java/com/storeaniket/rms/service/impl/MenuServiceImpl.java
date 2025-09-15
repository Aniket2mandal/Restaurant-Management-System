package com.storeaniket.rms.service.impl;

import com.storeaniket.rms.dto.MenuDTO;
import com.storeaniket.rms.dto.MenuSizeDTO;
import com.storeaniket.rms.model.*;
import com.storeaniket.rms.repository.*;
import com.storeaniket.rms.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    MenuRepository menuRepository;
    CategoryRepository categoryRepository;
    SizeGroupRepository sizeGroupRepository;
    MenuSizeRepository menuSizeRepository;
    SizeRepository sizeRepository;

    public MenuServiceImpl(MenuRepository menuRepository, CategoryRepository categoryRepository,SizeGroupRepository sizeGroupRepository, MenuSizeRepository menuSizeRepository,SizeRepository sizeRepository) {
        this.menuRepository = menuRepository;
        this.categoryRepository = categoryRepository;
        this.sizeGroupRepository = sizeGroupRepository;
        this.menuSizeRepository = menuSizeRepository;
        this.sizeRepository = sizeRepository;
    }

    @Override
    public String createMenu(MenuDTO dto) {
        MenuDB menu = new MenuDB();
//        menu.setId(dto.getId());
        menu.setName(dto.getName());
        menu.setBasePrice(dto.getBasePrice());

        CategoryDB category=categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        menu.setCategory(category);

        SizeGroupDB sizeGroup=sizeGroupRepository.findById(dto.getSizeGroupId())
                        .orElseThrow(() -> new RuntimeException("SizeGroup not found"));
        menu.setSizeGroup(sizeGroup);
        MenuDB savedMenu= menuRepository.save(menu);
//LIST OF SIZEDB MATCH WITH SIzegroup id
        for(MenuSizeDTO menuSizeDTO:dto.getMenuSizes()){
            MenuSizeDB menuSizeDB=new MenuSizeDB();
            menuSizeDB.setPrice(menuSizeDTO.getPrice());
            menuSizeDB.setMenu(savedMenu);
            SizeDB sizeDB=sizeRepository.findById(menuSizeDTO.getSizeId())
                            .orElseThrow(() -> new RuntimeException("Size not found"));
            menuSizeDB.setSize(sizeDB);
            menuSizeRepository.save(menuSizeDB);
        }

        return "success";
    }
    
    @Override
    public String updateMenu(MenuDTO dto) {
        MenuDB menu = menuRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Menu not found"));
//        MenuDB menu = new MenuDB();
////        menu.setId(dto.getId());
        menu.setName(dto.getName());
        menu.setBasePrice(dto.getBasePrice());

        CategoryDB category=categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        menu.setCategory(category);

        SizeGroupDB sizeGroup=sizeGroupRepository.findById(dto.getSizeGroupId())
                        .orElseThrow(() -> new RuntimeException("SizeGroup not found"));
        menu.setSizeGroup(sizeGroup);
        MenuDB savedMenu= menuRepository.save(menu);

        menuSizeRepository.deleteAll(menuSizeRepository.findByMenuId(dto.getId()));

        for(MenuSizeDTO menuSizeDTO:dto.getMenuSizes()){
            MenuSizeDB menuSizeDB=new MenuSizeDB();
            menuSizeDB.setPrice(menuSizeDTO.getPrice());
            menuSizeDB.setMenu(savedMenu);
            SizeDB sizeDB=sizeRepository.findById(menuSizeDTO.getSizeId())
                            .orElseThrow(() -> new RuntimeException("Size not found"));
            menuSizeDB.setSize(sizeDB);
            menuSizeRepository.save(menuSizeDB);
        }

        return "success";
    }
    
    @Override
    public String deleteMenu(Long menu_id) {
        menuRepository.deleteById(menu_id);
        return "success";
    }
    
    @Override
    public MenuDTO getMenu(Long menu_id) {
        return menuRepository.findById(menu_id)
                .map(menu->{
                    MenuDTO dto = new MenuDTO();
                    dto.setId(menu.getId());
                    dto.setName(menu.getName());
                    dto.setBasePrice(menu.getBasePrice());
                    dto.setCategoryId(menu.getCategoryId());
                    dto.setSizeGroupId(menu.getSizeGroupId());



                    return dto;
                })
                .orElse(null);
    }
    @Override
    public List<MenuDTO> getAllMenu() {

        return menuRepository.findAll().stream()
                .map(menu->{
                    MenuDTO dto=new MenuDTO();
                    dto.setId(menu.getId());
                    dto.setName(menu.getName());
                    dto.setBasePrice(menu.getBasePrice());
                    dto.setCategoryId(menu.getCategoryId());
                    dto.setSizeGroupId(menu.getSizeGroupId());
                    return dto;
                })
                .toList();
    }


}
