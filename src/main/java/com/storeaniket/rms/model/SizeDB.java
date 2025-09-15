package com.storeaniket.rms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="size")
public class SizeDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sizegroup_id", nullable = false, referencedColumnName = "id")
    private SizeGroupDB sizeGroup;

    @Column(name = "sizegroup_id", insertable = false, updatable = false)
    private Long sizeGroupId;

    public SizeDB() {}

    public SizeDB(Long id, String name, SizeGroupDB sizeGroup, Long sizeGroupId) {
        this.id = id;
        this.name = name;
        this.sizeGroup = sizeGroup;
        this.sizeGroupId = sizeGroupId;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SizeGroupDB getSizeGroup() {
        return sizeGroup;
    }

    public Long getSizeGroupId() {
        return sizeGroupId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSizeGroup(SizeGroupDB sizeGroup) {
        this.sizeGroup = sizeGroup;
    }

    public void setSizeGroupId(Long sizeGroupId) {
        this.sizeGroupId = sizeGroupId;
    }
}



