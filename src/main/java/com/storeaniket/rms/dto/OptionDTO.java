package com.storeaniket.rms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionDTO {

    private Long id;

    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotNull(message = "SizeGroupId cannot be null")
    private Long optionGroupId;

    private boolean isSelected;
}
