package com.storeaniket.rms.service;

import com.storeaniket.rms.dto.CategoryDTO;
import com.storeaniket.rms.dto.MenuDTO;
import com.storeaniket.rms.dto.SizeGroupDTO;
import com.storeaniket.rms.model.SizeGroupDB;
import com.storeaniket.rms.repository.SizeGroupRepository;

import java.util.List;

public interface FoodService {

    public String createSizeGroup(SizeGroupDTO sizeGroupRequest);

    public String updateSizeGroup(SizeGroupDTO sizeGroupRequest);

    public SizeGroupDTO getSizeGroup(Long sizeGroupId);

    public List<SizeGroupDTO> getAllSizeGroups();

    public String createMenu(MenuDTO menuRequest);

    public String updateMenu(MenuDTO menuRequest);

    public List<MenuDTO> getMenuWithMenuSizes();

    public List<CategoryDTO> getAllCategoriesWithMenus();
}
