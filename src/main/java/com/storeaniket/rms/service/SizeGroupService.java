package com.storeaniket.rms.service;

import com.storeaniket.rms.dto.SizeGroupDTO;
import com.storeaniket.rms.model.SizeGroupDB;

import java.util.List;

public interface SizeGroupService {
    public String createSizeGroup(SizeGroupDTO sizeGroupRequest);
    public String updateSizeGroup(SizeGroupDTO sizeGroupRequest);
    public String deleteSizeGroup(Long sizeGroupId);
    public SizeGroupDB getSizeGroup(Long sizeGroupId);
    public List<SizeGroupDB> getAllSizeGroups();
}
