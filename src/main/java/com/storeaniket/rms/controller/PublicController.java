package com.storeaniket.rms.controller;

import com.storeaniket.rms.dto.MenuDTO;
import com.storeaniket.rms.service.PublicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
PublicService publicService;

    public PublicController(PublicService publicService) {
        this.publicService = publicService;
    }

    @GetMapping("/menuInfo/{id}")
    public MenuDTO getMenuInfo(@PathVariable(value="id") Long id) {
        return publicService.getMenuInfo(id);
    }
}
