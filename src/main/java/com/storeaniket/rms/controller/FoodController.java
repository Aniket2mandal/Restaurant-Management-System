package com.storeaniket.rms.controller;

import com.storeaniket.rms.dto.*;
import com.storeaniket.rms.repository.MenuOptionRepository;
import com.storeaniket.rms.repository.SizeGroupRepository;
import com.storeaniket.rms.service.FoodService;
import com.storeaniket.rms.service.OptionGroupService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    FoodService foodService;
    OptionGroupService optionGroupService;

    public FoodController(FoodService foodService, OptionGroupService optionGroupService) {
        this.foodService = foodService;
        this.optionGroupService = optionGroupService;
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

    @PutMapping("/MenuWithMenuSizes/update")
    public String updateMenu(@Valid @RequestBody MenuDTO menuDTO) {
        return foodService.updateMenu(menuDTO);
    }


    @GetMapping("/MenuWithMenuSizes")
    public List<MenuDTO> getMenuWithMenuSizes() {
        return foodService.getMenuWithMenuSizes();
    }


    @GetMapping("/CategoryWithMenus")
    public List<CategoryDTO> getCategoryWithMenus() {
        return foodService.getAllCategoriesWithMenus();
    }




//    FOR OPTIONS AND OPTIONGROUP
    @PostMapping("/create/OptionGroupWithOption")
    public String createOptionGroup(@Valid @RequestBody OptionGroupDTO optionGroupDTO) {
        return optionGroupService.createOptionGroupWithOptions(optionGroupDTO);
    }

    @PutMapping("/update/OptionGroupWithOption")
    public String updateOptionGroup(@Valid @RequestBody OptionGroupDTO optionGroupDTO) {
        return optionGroupService.updateOptionGroupWithOptions(optionGroupDTO);
    }

    @GetMapping("/OptionGroupWithOption")
    public List<OptionGroupDTO> getOptionGroupWithOption() {
        return optionGroupService.getAllOptionGroupsWithOptions();
    }

    @GetMapping("/OptionGroupWithOption/{id}")
    public OptionGroupDTO getOptionGroupById(@PathVariable(value = "id") Long id) {
        return optionGroupService.getOptionGroupWithOptions(id);
    }



//    FOR SIZEGROUP OPTIONGROUP
    @PutMapping("/LinkSizeAndOptionGroup")
    public String linkSizeAndOptionGroup(@Valid @RequestBody SizeGroupOptionGroupDTO sizeGroupOptionGroupDTO) {
        return optionGroupService.createSizeGroupOptionGroup(sizeGroupOptionGroupDTO);
    }

    @DeleteMapping("/UnLinkSizeAndOptionGroup/{id}")
    public String unlinkSizeAndOptionGroup(@PathVariable(value="id") Long id) {
        return optionGroupService.deleteSizeGroupOptionGroup(id);
    }

    @PutMapping("/handleMenuOptions")
    public String handleMenuOptions(@Valid @RequestBody MenuDTO menuDTO) {
        return optionGroupService.linkMenuOption(menuDTO);
    }

    @GetMapping("/MenuOptions/{id}")
    public List<OptionDTO> getMenuOptions(@PathVariable(value = "id") Long id) {
        return optionGroupService.getMenuOptions(id);
    }

    @GetMapping("/MenuOptionWithOption/{id}")
    public MenuDTO getMenuOptionWithOption(@PathVariable(value = "id") Long id) {
        return optionGroupService.getMenuWithOptions(id);
    }

    @PostMapping("/MenuOptionUsingSelected")
    public String menuOptionUsingSelected(@Valid @RequestBody MenuDTO menuDTO) {
        return optionGroupService.createMenuOptionUsingSelected(menuDTO);
    }
}
