package com.storeaniket.rms.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="menu_option")
public class MenuOptionDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false, referencedColumnName = "id")
    private OptionDB option;

    @Column(name = "option_id", insertable = false, updatable = false)
    private Long optionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false, referencedColumnName = "id")
    private MenuDB menu;

    @Column(name = "menu_id", insertable = false, updatable = false)
    private Long menuId;

    public MenuOptionDB() {}

}
