package com.storeaniket.rms.service;

import com.storeaniket.rms.dto.SizeDTO;
import com.storeaniket.rms.model.SizeDB;

import java.util.List;

public interface SizeService {

    public String createSize(SizeDTO sizeRequest);
    public String updateSize(Long id,SizeDTO sizeRequest);
    public String deleteSize(Long sizeId);
    public List<SizeDTO> getAllSize();
    public SizeDTO getSize(Long sizeId);
}
