package com.storeaniket.rms.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="size_group_option_group")
public class SizeGroupOptionGroupDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "optionGroup_id", nullable = false, referencedColumnName = "id")
    private OptionGroupDB optionGroup;

    @Column(name = "optionGroup_id", insertable = false, updatable = false)
    private Long optionGroupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sizeGroup_id", nullable = false, referencedColumnName = "id")
    private SizeGroupDB sizeGroup;

    @Column(name = "sizeGroup_id", insertable = false, updatable = false)
    private Long sizeGroupId;

    public SizeGroupOptionGroupDB() {}
}
