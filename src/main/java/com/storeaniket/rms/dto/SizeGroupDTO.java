package com.storeaniket.rms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SizeGroupDTO {


    private Long id;

//    @NotNull(message="Name cannot be null")
    private String name;

    private List<SizeDTO> sizes;
}
