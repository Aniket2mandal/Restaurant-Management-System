package com.storeaniket.rms.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="menu_size")
public class MenuSizeDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false, referencedColumnName = "id")
    private MenuDB menu;

    // Optional: if you want to access just the FK column
    @Column(name = "menu_id", insertable = false, updatable = false)
    private Long menuId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="size_id",nullable = false,referencedColumnName = "id")
    private SizeDB size;

    @Column(name="size_id",insertable = false,updatable = false)
    private Long sizeId;

    public MenuSizeDB() {
        this.price = 0.0;
    }

}
