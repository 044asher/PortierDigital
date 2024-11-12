package org.example.portierdigital.General.Model.Portfolio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyWorks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String company;
    @NotNull
    private String description;
    @NotNull
    private String position;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
