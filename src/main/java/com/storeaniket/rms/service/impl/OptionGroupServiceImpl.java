package com.storeaniket.rms.service.impl;

import com.storeaniket.rms.dto.MenuDTO;
import com.storeaniket.rms.dto.OptionDTO;
import com.storeaniket.rms.dto.OptionGroupDTO;
import com.storeaniket.rms.dto.SizeGroupOptionGroupDTO;
import com.storeaniket.rms.model.*;
import com.storeaniket.rms.repository.*;
import com.storeaniket.rms.service.OptionGroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OptionGroupServiceImpl implements OptionGroupService {

    private final SizeGroupRepository sizeGroupRepository;
    private final MenuOptionRepository menuOptionRepository;
    OptionGroupRepository optionGroupRepository;
    OptionRepository optionRepository;
    SizeGroupOptionGroupRepository sizeGroupOptionGroupRepository;
    MenuRepository menuRepository;

    public OptionGroupServiceImpl(OptionGroupRepository optionGroupRepository, OptionRepository optionRepository
            , SizeGroupOptionGroupRepository sizeGroupOptionGroupRepository, SizeGroupRepository sizeGroupRepository
            , MenuRepository menuRepository, MenuOptionRepository menuOptionRepository) {
        this.optionGroupRepository = optionGroupRepository;
        this.optionRepository = optionRepository;
        this.sizeGroupOptionGroupRepository = sizeGroupOptionGroupRepository;
        this.sizeGroupRepository = sizeGroupRepository;
        this.menuRepository = menuRepository;
        this.menuOptionRepository = menuOptionRepository;
    }


    public String createOptionGroupWithOptions(OptionGroupDTO optionGroupDTO) {

        OptionGroupDB optionGroupDB = new OptionGroupDB();
        optionGroupDB.setName(optionGroupDTO.getName());
        OptionGroupDB savedOptionGroup = optionGroupRepository.save(optionGroupDB);

        for (OptionDTO optionDTO : optionGroupDTO.getOptions()) {
            OptionDB optionDB = new OptionDB();
            optionDB.setName(optionDTO.getName());
            optionDB.setOptionGroup(savedOptionGroup);
            optionRepository.save(optionDB);
        }
        return "success";
    }

    public String updateOptionGroupWithOptions(OptionGroupDTO optionGroupDTO) {
        OptionGroupDB optionGroupDBList = optionGroupRepository.findById(optionGroupDTO.getId())
                .orElseThrow(() -> new RuntimeException("Option group not found"));
        optionGroupDBList.setName(optionGroupDTO.getName());
        OptionGroupDB savedOptionGroup = optionGroupRepository.save(optionGroupDBList);

        List<OptionDB> optionDBList = optionRepository.findByOptionGroupId(optionGroupDBList.getId());
        for (OptionDTO optionDTO : optionGroupDTO.getOptions()) {
            for (OptionDB optionDB : optionDBList) {
                if (optionDTO.getId().equals(optionDB.getId())) {
                    optionDB.setName(optionDTO.getName());
                    optionDB.setOptionGroup(savedOptionGroup);
                    optionRepository.save(optionDB);
                }
            }
        }
        return "success";
    }

    public String deleteOptionGroupWithOptions(OptionGroupDTO optionGroupDTO) {
        return null;
    }

    public OptionGroupDTO getOptionGroupWithOptions(Long id) {
        return null;
    }

    public List<OptionGroupDTO> getAllOptionGroupsWithOptions() {
        List<OptionGroupDB> optionGroupDBList = optionGroupRepository.findAll();
        List<OptionGroupDTO> optionGroupDTOList = new ArrayList<>();
        for (OptionGroupDB optionGroupDB : optionGroupDBList) {
            OptionGroupDTO optionGroupDTO = new OptionGroupDTO();
            optionGroupDTO.setId(optionGroupDB.getId());
            optionGroupDTO.setName(optionGroupDB.getName());

            List<OptionDB> optionDBList = optionRepository.findByOptionGroupId(optionGroupDB.getId());
            List<OptionDTO> optionDTOList = new ArrayList<>();
            for (OptionDB optionDB : optionDBList) {
                OptionDTO optionDTO = new OptionDTO();
                optionDTO.setId(optionDB.getId());
                optionDTO.setName(optionDB.getName());
                optionDTO.setOptionGroupId(optionGroupDB.getId());
                optionDTOList.add(optionDTO);
            }
            optionGroupDTO.setOptions(optionDTOList);
            optionGroupDTOList.add(optionGroupDTO);
        }
        return optionGroupDTOList;
    }


    //    FOR SIZEGROUP OPTIONGROUP
    @Override
    public String createSizeGroupOptionGroup(SizeGroupOptionGroupDTO sizeGroupOptionGroupDTO) {
        SizeGroupDB sizeGroupDB = sizeGroupRepository.findById(sizeGroupOptionGroupDTO.getSizeGroupId())
                .orElseThrow(() -> new RuntimeException("Size group not found"));

        OptionGroupDB optionGroupDB = optionGroupRepository.findById(sizeGroupOptionGroupDTO.getOptionGroupId())
                .orElseThrow(() -> new RuntimeException("Option group not found"));

        SizeGroupOptionGroupDB savedSizeGroupOptionGroupDB = new SizeGroupOptionGroupDB();
        savedSizeGroupOptionGroupDB.setSizeGroup(sizeGroupDB);
        savedSizeGroupOptionGroupDB.setOptionGroup(optionGroupDB);
        sizeGroupOptionGroupRepository.save(savedSizeGroupOptionGroupDB);
        return "success";
    }

    @Override
    public String deleteSizeGroupOptionGroup(Long id) {
        sizeGroupOptionGroupRepository.deleteById(id);
        return "success";
    }

    @Override
    public String linkMenuOption(MenuDTO menuDTO) {

        MenuDB menuDB = menuRepository.findById(menuDTO.getId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        if (menuDTO.getOptions() != null && !menuDTO.getOptions().isEmpty()) {
            for (OptionDTO optionDTO : menuDTO.getOptions()) {
                OptionDB optionDB = optionRepository.findById(optionDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Option not found"));

                List<MenuOptionDB> existing = menuOptionRepository.findByMenuIdAndOptionId(menuDB.getId(), optionDB.getId());
//                if (!existing.isEmpty()) {
//                    menuOptionRepository.deleteAll(existing);
//                }
                if (existing.isEmpty()) {
                    MenuOptionDB menuOptionDB = new MenuOptionDB();
                    menuOptionDB.setMenu(menuDB);
                    menuOptionDB.setOption(optionDB);
                    menuOptionRepository.save(menuOptionDB);
                }
            }
        }
        if (menuDTO.getRemoveOptions() != null && !menuDTO.getRemoveOptions().isEmpty()) {
            for (OptionDTO optionDTO : menuDTO.getRemoveOptions()) {
                OptionDB optionDB = optionRepository.findById(optionDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Option not found"));

                List<MenuOptionDB> existingOptionAndMenu = menuOptionRepository.findByMenuIdAndOptionId(menuDB.getId(), optionDB.getId());
                if (!existingOptionAndMenu.isEmpty()) {
                    menuOptionRepository.deleteAll(existingOptionAndMenu);
                }
            }
        }
        return "success";
    }

    @Override
    public List<OptionDTO> getMenuOptions(Long id) {
        List<MenuOptionDB> menuOptionDBList = menuOptionRepository.findByMenuId(id);

        List<OptionDTO> optionDTOList = new ArrayList<>();
        for (MenuOptionDB menuOptionDB : menuOptionDBList) {
            OptionDB option = menuOptionDB.getOption();
            OptionDTO optionDTO = new OptionDTO();
            optionDTO.setId(option.getId());
            optionDTO.setName(option.getName());
            optionDTO.setOptionGroupId(option.getOptionGroupId());
            optionDTOList.add(optionDTO);
        }
        return optionDTOList;
    }

    @Override
    public MenuDTO getMenuWithOptions(Long id) {

        MenuDB menuDB = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        List<SizeGroupOptionGroupDB> sizeGroupOptionGroupDBList =
                sizeGroupOptionGroupRepository.findBySizeGroupId(menuDB.getSizeGroupId());


        List<OptionDB> allOptions = new ArrayList<>();
        for (SizeGroupOptionGroupDB sizeGroupOptionGroupDB : sizeGroupOptionGroupDBList) {
            allOptions.addAll(optionRepository.findByOptionGroupId(sizeGroupOptionGroupDB.getOptionGroupId()));
        }

        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(id);

        List<OptionDTO> optionDTOList = new ArrayList<>();
        List<MenuOptionDB> menuOptionDBList = menuOptionRepository.findByMenuId(menuDB.getId());

        for (OptionDB optionDB : allOptions) {
            OptionDTO optionDTO = new OptionDTO();
            optionDTO.setId(optionDB.getId());
            optionDTO.setName(optionDB.getName());

            boolean isSelected = false;
            for (MenuOptionDB menuOptionDB : menuOptionDBList) {
                if (menuOptionDB.getOption().getId().equals(optionDB.getId())) {
//                    optionDTO.setSelected(true);
                    isSelected = true;
                    break;
                }
            }
            optionDTO.setSelected(isSelected);
            optionDTOList.add(optionDTO);
        }
        menuDTO.setOptions(optionDTOList);
        return menuDTO;

    }

    @Override
    public String createMenuOptionUsingSelected(MenuDTO menuDTO) {

        MenuDB menuDB = menuRepository.findById(menuDTO.getId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        if (menuDTO.getOptions() != null && !menuDTO.getOptions().isEmpty()) {
            for (OptionDTO optionDTO : menuDTO.getOptions()) {
                OptionDB optionDB = optionRepository.findById(optionDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Option not found"));

                List<MenuOptionDB> existing = menuOptionRepository.findByMenuIdAndOptionId(menuDB.getId(), optionDB.getId());

                if (existing.isEmpty()) {
                    if (optionDTO.isSelected()) {
                        MenuOptionDB menuOptionDB = new MenuOptionDB();
                        menuOptionDB.setMenu(menuDB);
                        menuOptionDB.setOption(optionDB);
                        menuOptionRepository.save(menuOptionDB);
                    }
                } else {
                    if (optionDTO.isSelected() == false) {
                        for (MenuOptionDB menuOptionDB : existing) {
                            menuOptionRepository.delete(menuOptionDB);
                        }
                    }
                }
            }
        }
        return "success";
    }

}
