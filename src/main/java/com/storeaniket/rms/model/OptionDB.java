package com.storeaniket.rms.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="options")
public class OptionDB {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "optiongroup_id", nullable = false, referencedColumnName = "id")
    private OptionGroupDB optionGroup;

    @Column(name = "optiongroup_id", insertable = false, updatable = false)
    private Long optionGroupId;

    public OptionDB() {}

}
