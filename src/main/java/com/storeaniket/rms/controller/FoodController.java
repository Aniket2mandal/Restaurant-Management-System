package com.storeaniket.rms.controller;

import com.storeaniket.rms.dto.CategoryDTO;
import com.storeaniket.rms.dto.MenuDTO;
import com.storeaniket.rms.dto.SizeGroupDTO;
import com.storeaniket.rms.repository.SizeGroupRepository;
import com.storeaniket.rms.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // FOR SIZEAGROUP
    @PostMapping("/sizeGroup/create")
    public String createSizeGroup(@Valid @RequestBody SizeGroupDTO sizeGroupDTO) {
        return foodService.createSizeGroup(sizeGroupDTO);
    }

    @GetMapping("/sizeGroup")
    public List<SizeGroupDTO> getSizeGroups() {
        return foodService.getAllSizeGroups();
    }

    @GetMapping("/sizeGroupById/{id}")
    public SizeGroupDTO getSizeGroupById(@PathVariable(value = "id") Long id) {
        return foodService.getSizeGroup(id);
    }

    @PutMapping("/update/sizeGroup")
    public String updateSizeGroup(@Valid @RequestBody SizeGroupDTO sizeGroupDTO) {
        return foodService.updateSizeGroup(sizeGroupDTO);
    }


//    FOR MENU

    @PostMapping("/menu/create")
    public String createMenu(@Valid @RequestBody MenuDTO menuDTO) {
        return foodService.createMenu(menuDTO);
    }


    @GetMapping("/MenuWithMenuSizes")
    public List<MenuDTO> getMenuWithMenuSizes() {
        return foodService.getMenuWithMenuSizes();
    }


    @GetMapping("/CategoryWithMenus")
    public List<CategoryDTO> getCategoryWithMenus() {
        return foodService.getAllCategoriesWithMenus();
    }
}
