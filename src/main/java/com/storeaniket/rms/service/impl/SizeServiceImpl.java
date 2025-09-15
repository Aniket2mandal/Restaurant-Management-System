package com.storeaniket.rms.service.impl;

import com.storeaniket.rms.dto.SizeDTO;
import com.storeaniket.rms.model.SizeDB;
import com.storeaniket.rms.model.SizeGroupDB;
import com.storeaniket.rms.repository.SizeGroupRepository;
import com.storeaniket.rms.repository.SizeRepository;
import com.storeaniket.rms.service.SizeService;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class SizeServiceImpl implements SizeService {

    SizeRepository sizeRepository;
    SizeGroupRepository sizeGroupRepository;

    public SizeServiceImpl(SizeRepository sizeRepository, SizeGroupRepository sizeGroupRepository) {
        this.sizeRepository = sizeRepository;
        this.sizeGroupRepository = sizeGroupRepository;
    }

    @Override
    public String createSize(SizeDTO dto){
        SizeDB size = new SizeDB();
//        size.setId(dto.getId());
        size.setName(dto.getName());

        SizeGroupDB group= sizeGroupRepository.findById(dto.getSizeGroupId())
                .orElseThrow(() -> new RuntimeException("SizeGroup not found"));

        size.setSizeGroup(group);
        sizeRepository.save(size);
        return "Success";
    }

    @Override
    public String updateSize(Long id,SizeDTO dto){
        SizeDB size = sizeRepository.findById(id).orElseThrow(() -> new RuntimeException("Size not found"));
//        size.setId(dto.getId());
        size.setName(dto.getName());

        SizeGroupDB group= sizeGroupRepository.findById(dto.getSizeGroupId())
                .orElseThrow(() -> new RuntimeException("SizeGroup not found"));

        size.setSizeGroup(group);
        sizeRepository.save(size);
        return "Success";
    }

    @Override
    public String deleteSize(Long id){
        sizeRepository.deleteById(id);
        return "success";
    }

    @Override
    public List<SizeDTO> getAllSize(){

//        return sizeRepository.findAll();

        return sizeRepository.findAll().stream()
                .map(size -> {   //size is itself sizeDB
                    SizeDTO dto = new SizeDTO();
                    dto.setId(size.getId());
                    dto.setName(size.getName());
                    dto.setSizeGroupId(size.getSizeGroupId());

//                    This is one SizeDB object from the database.
//                    It has fields like id, name, sizeGroupId, and sizeGroup (the object itself).

                    return dto;
                })
                .toList();
    }

    @Override
    public SizeDTO getSize(Long id){

        return sizeRepository.findById(id)
                .map(size ->{
                    SizeDTO dto = new SizeDTO();
                    dto.setId(size.getId());
                    dto.setName(size.getName());
                    dto.setSizeGroupId(size.getSizeGroupId());
                    return dto;
                })
                .orElse(null)
                ;

    }
}
