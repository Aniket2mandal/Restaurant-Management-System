package com.storeaniket.rms.controller;

import com.storeaniket.rms.dto.SizeGroupDTO;
import com.storeaniket.rms.model.SizeGroupDB;
import com.storeaniket.rms.service.SizeGroupService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sizegroup")
public class SizeGroupController {

    SizeGroupService sizeGroupService;
    public SizeGroupController(SizeGroupService sizeGroupService) {
        this.sizeGroupService = sizeGroupService;
    }


    @GetMapping
    public List<SizeGroupDB> sizeGroup() {
        return sizeGroupService.getAllSizeGroups();
    }

    @GetMapping("/{Id}")
    public SizeGroupDB getSizeGroup(@PathVariable (value="Id") Long Id) {
        return sizeGroupService.getSizeGroup(Id);
    }

    @PostMapping("/create")
    public String createSizeGroup(@Valid @RequestBody SizeGroupDTO sizeGroupRequest) {
        return sizeGroupService.createSizeGroup(sizeGroupRequest);
    }

    @PutMapping("/update")
    public String updateSizeGroup(@Valid @RequestBody SizeGroupDTO sizeGroupRequest) {
        return sizeGroupService.updateSizeGroup(sizeGroupRequest);
    }

    @DeleteMapping("/{Id}")
    public String deleteSizeGroup(@PathVariable (value="Id") Long Id) {
        return sizeGroupService.deleteSizeGroup(Id);
    }
}
