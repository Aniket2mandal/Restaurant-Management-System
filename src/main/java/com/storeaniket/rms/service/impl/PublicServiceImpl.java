package com.storeaniket.rms.service.impl;

import com.storeaniket.rms.dto.*;
import com.storeaniket.rms.model.*;
import com.storeaniket.rms.repository.*;
import com.storeaniket.rms.service.PublicService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublicServiceImpl implements PublicService {
    MenuRepository menuRepository;
    SizeRepository sizeRepository;
    MenuSizeRepository menuSizeRepository;
    MenuOptionRepository menuOptionRepository;
    OptionRepository optionRepository;
    SizeGroupOptionGroupRepository sizeGroupOptionGroupRepository;
    OptionGroupRepository optionGroupRepository;

    public PublicServiceImpl(MenuRepository menuRepository, SizeRepository sizeRepository,
                             MenuSizeRepository menuSizeRepository, MenuOptionRepository menuOptionRepository
            , OptionRepository optionRepository, OptionGroupRepository optionGroupRepository, SizeGroupOptionGroupRepository sizeGroupOptionGroupRepository) {
        this.menuRepository = menuRepository;
        this.sizeRepository = sizeRepository;
        this.menuSizeRepository = menuSizeRepository;
        this.menuOptionRepository = menuOptionRepository;
        this.optionRepository = optionRepository;
        this.optionGroupRepository = optionGroupRepository;
        this.sizeGroupOptionGroupRepository = sizeGroupOptionGroupRepository;
    }

    @Override
    public MenuDTO getMenuInfo(Long id) {
        MenuDB menuDBlist = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(menuDBlist.getId());
        menuDTO.setName(menuDBlist.getName());
        menuDTO.setBasePrice(menuDBlist.getBasePrice());

        List<SizeDB> sizeDBList = sizeRepository.findBySizeGroupId(menuDBlist.getSizeGroupId());
        List<SizeDTO> sizeDTOList = new ArrayList<>();
        for (SizeDB sizeDB : sizeDBList) {
            SizeDTO sizeDTO = new SizeDTO();
            sizeDTO.setId(sizeDB.getId());
            sizeDTO.setName(sizeDB.getName());
            List<MenuSizeDB> menuSizeDBList = menuSizeRepository.findBySizeId(sizeDB.getId());
            for (MenuSizeDB menuSizeDB : menuSizeDBList) {
                sizeDTO.setPrice(menuSizeDB.getPrice());
            }
            sizeDTOList.add(sizeDTO);
        }
        menuDTO.setSizes(sizeDTOList);

        List<MenuOptionDB> menuOptionDBList = menuOptionRepository.findByMenuId(menuDTO.getId());
        List<OptionDTO> menuOptionDTOList = new ArrayList<>();
        for (MenuOptionDB menuOptionDB : menuOptionDBList) {

            OptionDTO optionDTO = new OptionDTO();
            OptionDB optionDBList = optionRepository.findById(menuOptionDB.getOptionId())
                    .orElseThrow(() -> new RuntimeException("Option not found"));
            optionDTO.setOptionId(optionDBList.getId());
            optionDTO.setName(optionDBList.getName());

            menuOptionDTOList.add(optionDTO);
        }
        menuDTO.setMenuOptions(menuOptionDTOList);

        List<SizeGroupOptionGroupDB> sizeGroupOptionGroupDBList = sizeGroupOptionGroupRepository.findBySizeGroupId(menuDBlist.getSizeGroupId());

        List<OptionGroupDTO> optionGroupDBList = new ArrayList<>();

        for (SizeGroupOptionGroupDB sizeGroupOptionGroupDB : sizeGroupOptionGroupDBList) {

            OptionGroupDB optionGroupDB = optionGroupRepository.findById(sizeGroupOptionGroupDB.getOptionGroupId())
                    .orElseThrow(() -> new RuntimeException("OptionGroup not found"));

            OptionGroupDTO optionGroupDTO = new OptionGroupDTO();
            optionGroupDTO.setId(optionGroupDB.getId());
            optionGroupDTO.setName(optionGroupDB.getName());

            List<OptionDB> optionList = optionRepository.findByOptionGroupId(optionGroupDB.getId());
            List<OptionDTO> optionDTOList = new ArrayList<>();

            for (OptionDB optionDB : optionList) {
                OptionDTO optionDTO = new OptionDTO();
                optionDTO.setId(optionDB.getId());
                optionDTO.setName(optionDB.getName());
                optionDTOList.add(optionDTO);
            }
            optionGroupDTO.setOptions(optionDTOList);
            optionGroupDBList.add(optionGroupDTO);
        }
        menuDTO.setOptionGroups(optionGroupDBList);


        return menuDTO;
    }
}
