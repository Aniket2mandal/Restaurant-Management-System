package com.storeaniket.rms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SizeDTO {


    private Long id;

    @NotBlank(message="Name cannot be null")
    private String name;

    @NotNull(message="SizeGroupId cannot be null")
    private Long sizeGroupId;

}
