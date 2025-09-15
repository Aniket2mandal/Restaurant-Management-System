package com.storeaniket.rms.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="menus")
public class MenuDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double basePrice;
    // Unidirectional ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
    private CategoryDB category;

    // Optional: if you want to access just the FK column
    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sizegroup_id", nullable = false)
    private SizeGroupDB sizeGroup;

    // Optional: just the FK column if you want to access it directly
    @Column(name = "sizegroup_id", insertable = false, updatable = false)
    private Long sizeGroupId;

    public MenuDB() {

    }

}
