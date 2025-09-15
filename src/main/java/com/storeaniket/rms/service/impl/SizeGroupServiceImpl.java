package com.storeaniket.rms.service.impl;

import com.storeaniket.rms.dto.SizeDTO;
import com.storeaniket.rms.dto.SizeGroupDTO;
import com.storeaniket.rms.model.SizeDB;
import com.storeaniket.rms.model.SizeGroupDB;
import com.storeaniket.rms.repository.SizeGroupRepository;
import com.storeaniket.rms.repository.SizeRepository;
import com.storeaniket.rms.service.SizeGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeGroupServiceImpl implements SizeGroupService {

    SizeGroupRepository sizeGroupRepository;
    SizeRepository sizeRepository;
    public SizeGroupServiceImpl(SizeGroupRepository sizeGroupRepository, SizeRepository sizeRepository) {
        this.sizeGroupRepository = sizeGroupRepository;
        this.sizeRepository = sizeRepository;
    }

    @Override
    public String createSizeGroup(SizeGroupDTO dto){
        SizeGroupDB sizeGroup = new SizeGroupDB();
//        sizeGroup.setId(dto.getId());
        sizeGroup.setName(dto.getName());

        SizeGroupDB savedGroup =sizeGroupRepository.save(sizeGroup);

        for(SizeDTO sizeDTO :dto.getSizes()){
            SizeDB savedSize = new SizeDB();
            savedSize.setName(sizeDTO.getName());
            savedSize.setSizeGroup(savedGroup);
            sizeRepository.save(savedSize);
        }

        return "Success";
    }

    @Override
    public String updateSizeGroup(SizeGroupDTO dto){
        SizeGroupDB sizeGroup = sizeGroupRepository.findById(dto.getId())
                .orElseThrow(()->new RuntimeException("Size group not found"));
//        sizeGroup.setId(dto.getId());
        sizeGroup.setName(dto.getName());

        SizeGroupDB savedGroup =sizeGroupRepository.save(sizeGroup);

        sizeRepository.deleteAll(sizeRepository.findBySizeGroupId(dto.getId()));

        for(SizeDTO sizeDTO :dto.getSizes()){
            SizeDB savedSize = new SizeDB();
            savedSize.setName(sizeDTO.getName());
            savedSize.setSizeGroup(savedGroup);
            sizeRepository.save(savedSize);
        }

        return "Success";
    }

    @Override
    public String deleteSizeGroup(Long sizeGroupId){
        sizeGroupRepository.deleteById(sizeGroupId);
        return "success";
    }

    @Override
    public SizeGroupDB getSizeGroup(Long sizeGroupId){

        return sizeGroupRepository.findById(sizeGroupId).get();
    }

    @Override
    public List<SizeGroupDB> getAllSizeGroups(){

        return sizeGroupRepository.findAll();
    }
}
