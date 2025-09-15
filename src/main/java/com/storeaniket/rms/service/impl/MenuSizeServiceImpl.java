package com.storeaniket.rms.service.impl;

import com.storeaniket.rms.dto.MenuSizeDTO;
import com.storeaniket.rms.model.MenuDB;
import com.storeaniket.rms.model.MenuSizeDB;
import com.storeaniket.rms.model.SizeDB;
import com.storeaniket.rms.repository.MenuRepository;
import com.storeaniket.rms.repository.MenuSizeRepository;
import com.storeaniket.rms.repository.SizeRepository;
import com.storeaniket.rms.service.MenuSizeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuSizeServiceImpl implements MenuSizeService {

    MenuSizeRepository menuSizeRepository;
    MenuRepository menuRepository;
    SizeRepository sizeRepository;

    public MenuSizeServiceImpl(MenuSizeRepository menuSizeRepository, MenuRepository menuRepository, SizeRepository sizeRepository) {
        this.menuSizeRepository = menuSizeRepository;
        this.menuRepository = menuRepository;
        this.sizeRepository = sizeRepository;
    }

    @Override
    public String createMenuSize(MenuSizeDTO dto){
       MenuSizeDB menuSizeDB = new MenuSizeDB();
       menuSizeDB.setPrice(dto.getPrice());

       MenuDB menu = menuRepository.findById(dto.getMenuId())
               .orElseThrow(()-> new RuntimeException("Menu not found"));
       menuSizeDB.setMenu(menu);

      SizeDB size = sizeRepository.findById(dto.getSizeId())
                      .orElseThrow(()-> new RuntimeException("Size not found"));
      menuSizeDB.setSize(size);

       menuSizeRepository.save(menuSizeDB);
       return "success";
    }

    @Override
    public String updateMenuSize(Long id, MenuSizeDTO dto){

        MenuSizeDB menuSizeDB = menuSizeRepository.findById(id).orElseThrow(()-> new RuntimeException("Menu not found"));
        menuSizeDB.setPrice(dto.getPrice());

//        NO UPDATE not needed
        MenuDB menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(()-> new RuntimeException("Menu not found"));
        menuSizeDB.setMenu(menu);

        SizeDB size = sizeRepository.findById(dto.getSizeId())
                .orElseThrow(()-> new RuntimeException("Size not found"));
        menuSizeDB.setSize(size);

        menuSizeRepository.save(menuSizeDB);
        return "success";
    }


    @Override
    public String deleteMenuSize(Long id){
         menuSizeRepository.deleteById(id);
         return "success";
    }

    @Override
    public MenuSizeDTO getMenuSize(Long id){
        return menuSizeRepository.findById(id)
                .map(menuSize->{
                    MenuSizeDTO dto = new MenuSizeDTO();
                    dto.setId(menuSize.getId());
                    dto.setMenuId(menuSize.getMenuId());
                    dto.setSizeId(menuSize.getSizeId());
                    dto.setPrice(menuSize.getPrice());
                    return dto;
                })
                .orElseThrow(()-> new RuntimeException("Menu not found"));
    }

    @Override
    public List<MenuSizeDTO> getAllMenuSize(){
        return menuSizeRepository.findAll().stream()
                .map(menuSize->{
                    MenuSizeDTO dto = new MenuSizeDTO();
                    dto.setId(menuSize.getId());
                    dto.setMenuId(menuSize.getMenuId());
                    dto.setSizeId(menuSize.getSizeId());
                    dto.setPrice(menuSize.getPrice());
                    return dto;
                })
                .toList();
    }
}
