package com.storeaniket.rms.controller;

import com.storeaniket.rms.dto.SizeDTO;
import com.storeaniket.rms.model.SizeDB;
import com.storeaniket.rms.service.SizeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/size")
public class SizeController {

    SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping
    public List<SizeDTO> size() {
        return sizeService.getAllSize();
    }

    @GetMapping("/{Id}")
    public SizeDTO getSize(@PathVariable(value="Id") Long Id) {
        return sizeService.getSize(Id);
    }

    @PostMapping("/create")
    public String create(@RequestBody SizeDTO sizeRequest) {
        return sizeService.createSize(sizeRequest);
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable(value="id")Long id ,@Valid @RequestBody SizeDTO sizeRequest) {
        return sizeService.updateSize(id,sizeRequest);
    }

    @DeleteMapping("/{Id}")
    public String delete(@PathVariable(value="Id") Long Id) {
        return sizeService.deleteSize(Id);
    }
}
