package com.volkankaytmaz.backendproject2.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    private String name;

    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL, orphanRemoval = true)
    List <CustomerTreatment> customerTreatments;

}
