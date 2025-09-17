package com.storeaniket.rms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SizeDTO {


    private Long id;

    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotNull(message = "SizeGroupId cannot be null")
    private Long sizeGroupId;

//    FOR GETING PRICE FROM MENUSIZES
    private double price;

}
