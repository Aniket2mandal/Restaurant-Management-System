package com.storeaniket.rms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MenuSizeDTO {
    private Long id;
    @NotNull(message="price is required")
    private Double price;
    @NotNull(message="menu id is required")
    private Long menuId;
    @NotNull(message="size id is required")
    private Long sizeId;

//    private List<MenuSizeDTO> menuSizes;

    public MenuSizeDTO() {
        this.price = 0.0;
    }

}
