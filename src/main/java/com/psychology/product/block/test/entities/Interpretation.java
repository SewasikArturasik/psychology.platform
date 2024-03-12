package com.psychology.product.block.test.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "interpretations")

public class Interpretation {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "interpretation_id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "interpretation_text")
    private String interpretationText;

}
