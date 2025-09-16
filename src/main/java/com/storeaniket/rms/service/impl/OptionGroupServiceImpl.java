package com.storeaniket.rms.service.impl;

import com.storeaniket.rms.dto.OptionDTO;
import com.storeaniket.rms.dto.OptionGroupDTO;
import com.storeaniket.rms.dto.SizeGroupOptionGroupDTO;
import com.storeaniket.rms.model.OptionDB;
import com.storeaniket.rms.model.OptionGroupDB;
import com.storeaniket.rms.model.SizeGroupDB;
import com.storeaniket.rms.model.SizeGroupOptionGroupDB;
import com.storeaniket.rms.repository.OptionGroupRepository;
import com.storeaniket.rms.repository.OptionRepository;
import com.storeaniket.rms.repository.SizeGroupOptionGroupRepository;
import com.storeaniket.rms.repository.SizeGroupRepository;
import com.storeaniket.rms.service.OptionGroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OptionGroupServiceImpl implements OptionGroupService {

    private final SizeGroupRepository sizeGroupRepository;
    OptionGroupRepository optionGroupRepository;
    OptionRepository optionRepository;
    SizeGroupOptionGroupRepository sizeGroupOptionGroupRepository;

    public OptionGroupServiceImpl(OptionGroupRepository optionGroupRepository, OptionRepository optionRepository
    , SizeGroupOptionGroupRepository sizeGroupOptionGroupRepository, SizeGroupRepository sizeGroupRepository) {
        this.optionGroupRepository = optionGroupRepository;
        this.optionRepository = optionRepository;
        this.sizeGroupOptionGroupRepository = sizeGroupOptionGroupRepository;
        this.sizeGroupRepository = sizeGroupRepository;
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
    public String createSizeGroupOptionGroup(SizeGroupOptionGroupDTO sizeGroupOptionGroupDTO){
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
}
