package com.storeaniket.rms.service.impl;

import com.storeaniket.rms.dto.*;
import com.storeaniket.rms.model.*;
import com.storeaniket.rms.repository.*;
import com.storeaniket.rms.service.FoodService;
import com.storeaniket.rms.service.MenuService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final MenuService menuService;
    SizeGroupRepository sizeGroupRepository;
    SizeRepository sizeRepository;
    CategoryRepository categoryRepository;
    MenuRepository menuRepository;
    MenuSizeRepository menuSizeRepository;

    public FoodServiceImpl(SizeGroupRepository sizeGroupRepository,
                           SizeRepository sizeRepository,
                           CategoryRepository categoryRepository,
                           MenuRepository menuRepository,
                           MenuSizeRepository menuSizeRepository, MenuService menuService) {
        this.sizeGroupRepository = sizeGroupRepository;
        this.sizeRepository = sizeRepository;
        this.categoryRepository = categoryRepository;
        this.menuRepository = menuRepository;
        this.menuSizeRepository = menuSizeRepository;
        this.menuService = menuService;
    }

    // FOR SIZE GROUP
    @Override
    public String createSizeGroup(SizeGroupDTO dto) {
        SizeGroupDB sizeGroup = new SizeGroupDB();
//        sizeGroup.setId(dto.getId());
        sizeGroup.setName(dto.getName());

        SizeGroupDB savedGroup = sizeGroupRepository.save(sizeGroup);

        for (SizeDTO sizeDTO : dto.getSizes()) {
            SizeDB savedSize = new SizeDB();
            savedSize.setName(sizeDTO.getName());
            savedSize.setSizeGroup(savedGroup);
            sizeRepository.save(savedSize);
        }

        return "Success";
    }

    @Override
    public String updateSizeGroup(SizeGroupDTO dto) {
        SizeGroupDB sizeGroup = sizeGroupRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Size group not found"));
        sizeGroup.setName(dto.getName());
        SizeGroupDB savedGroup = sizeGroupRepository.save(sizeGroup);

        List<SizeDB> sizes = sizeRepository.findBySizeGroupId(sizeGroup.getId());
        for (SizeDTO sizeDTO : dto.getSizes()) {
            for (SizeDB sizeDB : sizes) {
                if (sizeDB.getId().equals(sizeDTO.getId())) {
                    sizeDB.setName(sizeDTO.getName());
                    sizeDB.setSizeGroup(savedGroup);
                    sizeRepository.save(sizeDB);
                }
            }
        }
        return "success";
    }


    @Override
    public SizeGroupDTO getSizeGroup(Long id) {
        return sizeGroupRepository.findById(id)
                .map(groups -> {
                    SizeGroupDTO dto = new SizeGroupDTO();
                    dto.setId(groups.getId());
                    dto.setName(groups.getName());
                    List<SizeDTO> sizes = sizeRepository.findBySizeGroupId(groups.getId())
                            .stream()
                            .map(size -> {
                                SizeDTO sizeDTO = new SizeDTO();
                                sizeDTO.setId(size.getId());
                                sizeDTO.setName(size.getName());
                                return sizeDTO;
                            })
                            .toList();
                    dto.setSizes(sizes);
                    return dto;
                }).orElse(null);

//        return ;
    }

    @Override
    public List<SizeGroupDTO> getAllSizeGroups() {
        List<SizeGroupDB> groups = sizeGroupRepository.findAll();

        return groups.stream().map(group -> {
            SizeGroupDTO dto = new SizeGroupDTO();
            dto.setId(group.getId());
            dto.setName(group.getName());

            List<SizeDTO> sizes = sizeRepository.findBySizeGroupId(group.getId())
                    .stream()
                    .map(size -> {
                        SizeDTO sizeDTO = new SizeDTO();
                        sizeDTO.setId(size.getId());
                        sizeDTO.setName(size.getName());
                        sizeDTO.setSizeGroupId(group.getId());
                        return sizeDTO;
                    })
                    .toList();

            dto.setSizes(sizes);
            return dto;
        }).toList();
    }


    //    FOR MENU
    @Override
    public String createMenu(MenuDTO dto) {
        //  Create and save the Menu
        MenuDB menu = new MenuDB();
        menu.setName(dto.getName());
        menu.setBasePrice(dto.getBasePrice());
        CategoryDB category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        menu.setCategory(category);

//  Get SizeGroup
        SizeGroupDB sizeGroup = sizeGroupRepository.findById(dto.getSizeGroupId())
                .orElseThrow(() -> new RuntimeException("SizeGroup not found"));
        menu.setSizeGroup(sizeGroup);
        MenuDB savedMenu = menuRepository.save(menu);

//       naming
        List<SizeDB> sizes = sizeRepository.findBySizeGroupId(sizeGroup.getId());

        List<MenuSizeDB> menuSize = new ArrayList<>();
        for (SizeDB size : sizes) {
            MenuSizeDB menuSizeDB = new MenuSizeDB();
            menuSizeDB.setMenu(savedMenu);
            menuSizeDB.setSize(size);
//                menuSizeDB.setPrice(sizeDTO.getPrice());
            menuSize.add(menuSizeDB);
        }
        menuSizeRepository.saveAll(menuSize);

        return "success";
    }

    @Override
    public String updateMenu(MenuDTO dto) {
        MenuDB existingMenu = menuRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("menu not found"));
        existingMenu.setName(dto.getName());
        existingMenu.setBasePrice(dto.getBasePrice());

        CategoryDB existingCategory = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existingMenu.setCategory(existingCategory);

        SizeGroupDB existingSizeGroup = sizeGroupRepository.findById(dto.getSizeGroupId())
                .orElseThrow(() -> new RuntimeException("SizeGroup not found"));
        existingMenu.setSizeGroup(existingSizeGroup);

        MenuDB savedMenu = menuRepository.save(existingMenu);

        List<MenuSizeDB> menuSizeDBList = menuSizeRepository.findByMenuId(existingMenu.getId());
//    List<MenuSizeDTO> menuSizeDTOList=new ArrayList<>();
        for (MenuSizeDTO menuSizeDTO : dto.getMenuSizes()) {
            for (MenuSizeDB menuSizeDB : menuSizeDBList) {
                if (menuSizeDB.getId().equals(menuSizeDTO.getId())) {
                    menuSizeDB.setPrice(menuSizeDTO.getPrice());
                    menuSizeDB.setMenu(existingMenu);
                    SizeDB sizes = sizeRepository.findById(menuSizeDTO.getSizeId())
                            .orElseThrow(() -> new RuntimeException("Size not found"));
                    menuSizeDB.setSize(sizes);

                    menuSizeRepository.save(menuSizeDB);
                }
            }
        }
//    menuSizeRepository.saveAll(menuSizeDBList);
//    menuRepository.save(existingMenu);

        return "success";
    }


    @Override
    public List<MenuDTO> getMenuWithMenuSizes() {
        List<MenuDB> menuDBList = menuRepository.findAll();
        List<MenuDTO> menuDTOList = new ArrayList<>();
        for (MenuDB menuDB : menuDBList) {
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setId(menuDB.getId());
            menuDTO.setName(menuDB.getName());
            menuDTO.setBasePrice(menuDB.getBasePrice());
            menuDTO.setCategoryId(menuDB.getCategoryId());
            menuDTO.setSizeGroupId(menuDB.getSizeGroupId());
            menuDTOList.add(menuDTO);

            List<MenuSizeDB> menuSizeDBList = menuSizeRepository.findByMenuId(menuDTO.getId());
            List<MenuSizeDTO> menuSizeDTOList = new ArrayList<>();
            for (MenuSizeDB menuSizeDB : menuSizeDBList) {
                MenuSizeDTO menuSizeDTO = new MenuSizeDTO();
                menuSizeDTO.setId(menuSizeDB.getId());
                menuSizeDTO.setPrice(menuSizeDB.getPrice());
                menuSizeDTO.setSizeId(menuSizeDB.getSizeId());
                menuSizeDTO.setMenuId(menuSizeDB.getMenuId());
                menuSizeDTOList.add(menuSizeDTO);
            }
            menuDTO.setMenuSizes(menuSizeDTOList);
            menuDTOList.add(menuDTO);
        }

        return menuDTOList;
    }

    @Override
    public List<CategoryDTO> getAllCategoriesWithMenus() {
        List<CategoryDB> categories = categoryRepository.findAll();
        return categories.stream().map(category -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());

            List<MenuDTO> menus = menuRepository.findByCategoryId(category.getId())
                    .stream()
                    .map(menu -> {
                        MenuDTO menuDTO = new MenuDTO();
                        menuDTO.setId(menu.getId());
                        menuDTO.setName(menu.getName());
                        menuDTO.setBasePrice(menu.getBasePrice());
                        menuDTO.setCategoryId(category.getId());
                        menuDTO.setSizeGroupId(menu.getSizeGroupId());

                        List<MenuSizeDB> menuSizeDBList = menuSizeRepository.findByMenuId(menu.getId());
//                        CAN DO IT LIKE THIS WITHOUT USE MAP
                        List<MenuSizeDTO> menuSizes = new ArrayList<>();
                        for (MenuSizeDB menuSizeDB : menuSizeDBList) {
                            MenuSizeDTO menuSizeDTO = new MenuSizeDTO();
                            menuSizeDTO.setId(menuSizeDB.getId());
                            menuSizeDTO.setPrice(menuSizeDB.getPrice());
                            menuSizeDTO.setSizeId(menuSizeDB.getSizeId());
                            menuSizeDTO.setMenuId(menuSizeDB.getMenuId());
                            menuSizes.add(menuSizeDTO);
                        }
                        menuDTO.setMenuSizes(menuSizes);
                        return menuDTO;
                    })
                    .toList();

            categoryDTO.setMenus(menus);
            return categoryDTO;
        }).toList();
    }


}
