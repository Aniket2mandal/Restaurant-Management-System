package com.storeaniket.rms.controller;

import com.storeaniket.rms.dto.MenuDTO;
import com.storeaniket.rms.model.MenuDB;
import com.storeaniket.rms.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

MenuService menuService;
public MenuController(MenuService menuService) {
    this.menuService = menuService;
}

    @GetMapping("/{menuId}")
    public MenuDTO getMenu(@PathVariable(value="menuId") Long menuId) {
        return menuService.getMenu(menuId);
    }

    @GetMapping
    public List<MenuDTO> getAllMenu() {
        return menuService.getAllMenu();
    }

    @PostMapping("/create")
//    WHEN WE CREATE DTO WE HAVE TO WRITE DTO FILE NAME IN PARAMETER INSTEAD OF MODEL NAME
//    public String createStoreVendor(@Valid @RequestBody StoreVendor storeVendor) {
    public String createMenu(@Valid @RequestBody MenuDTO menuRequest) {
        return menuService.createMenu(menuRequest);
    }

    @PutMapping("/update")
    public String updateMenu( @Valid @RequestBody MenuDTO menuRequest) {
        return  menuService.updateMenu(menuRequest);
    }

    @DeleteMapping("/{menuId}")
    public String deleteStoreVendor(@PathVariable(value="menuId") Long menuId) {
        return menuService.deleteMenu(menuId);
    }

}
