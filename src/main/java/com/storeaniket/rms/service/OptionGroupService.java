package com.storeaniket.rms.service;

import com.storeaniket.rms.dto.MenuDTO;
import com.storeaniket.rms.dto.OptionDTO;
import com.storeaniket.rms.dto.OptionGroupDTO;
import com.storeaniket.rms.dto.SizeGroupOptionGroupDTO;
import jakarta.validation.constraints.AssertFalse;

import java.util.List;

public interface OptionGroupService {
    public String createOptionGroupWithOptions(OptionGroupDTO optionGroupDTO);

    public String updateOptionGroupWithOptions(OptionGroupDTO optionGroupDTO);

    public String deleteOptionGroupWithOptions(OptionGroupDTO optionGroupDTO);

    public OptionGroupDTO getOptionGroupWithOptions(Long id);

    public List<OptionGroupDTO> getAllOptionGroupsWithOptions();

    public String createSizeGroupOptionGroup(SizeGroupOptionGroupDTO sizeGroupOptionGroupDTO);

    public String deleteSizeGroupOptionGroup(Long id);

    public String linkMenuOption(MenuDTO menuDTO);

    public List<OptionDTO> getMenuOptions(Long id);

    public MenuDTO getMenuWithOptions(Long id);

    public String createMenuOptionUsingSelected(MenuDTO menuDTO);
}
