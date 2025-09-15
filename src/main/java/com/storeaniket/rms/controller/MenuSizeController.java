package com.storeaniket.rms.controller;

import com.storeaniket.rms.dto.MenuSizeDTO;
import com.storeaniket.rms.service.MenuSizeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menusize")
public class MenuSizeController {

    MenuSizeService menuSizeService;

    public MenuSizeController(MenuSizeService menuSizeService) {
        this.menuSizeService = menuSizeService;
    }


    @GetMapping
    public List<MenuSizeDTO> getAllMenuSize(){
        return menuSizeService.getAllMenuSize();
    }

    @GetMapping("/{id}")
    public MenuSizeDTO getMenuSizeById(@PathVariable(value="id") Long id){
        return menuSizeService.getMenuSize(id);
    }

    @PostMapping("/create")
    public String createMenuSize(@Valid @RequestBody MenuSizeDTO menuSizeDTO){
        return menuSizeService.createMenuSize(menuSizeDTO);
    }

    @PutMapping("/update/{id}")
    public String updateMenuSize(@PathVariable(value="id") Long id,@Valid @RequestBody MenuSizeDTO menuSizeDTO){
        return menuSizeService.updateMenuSize(id,menuSizeDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteMenuSize(@PathVariable(value="id") Long id){
        return menuSizeService.deleteMenuSize(id);
    }
}
