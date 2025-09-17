package com.storeaniket.rms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDTO {


    private Long id;
//
//    @NotBlank(message = "Name is required")
//    @Length(min = 3, max = 100)
    private String name;

    private Double basePrice;

//    @NotNull(message = "Category cannot be null")
    private Long categoryId;

    private Long sizeGroupId;

    private List<MenuSizeDTO> menuSizes;

    private List<OptionDTO> options;

    private List<OptionDTO> removeOptions;

//    private String categoryName;

    public MenuDTO() {

    }
}
