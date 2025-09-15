package com.storeaniket.rms.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="option_group")
public class OptionGroupDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public OptionGroupDB() {}
}
