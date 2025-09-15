package com.storeaniket.rms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class CategoryDTO {


    private Long id;

    @NotBlank(message="Name is required")
    @Length(min = 3,max=100)
    private String name;

    private List<MenuDTO> menus;

}
