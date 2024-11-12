package org.example.portierdigital.General.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name of template should not be empty")
    private String name;

    @NotEmpty(message = "Subject should not be empty")
    private String subject;

    @NotEmpty(message = "Message body should not be empty")
    @Lob
    private String content;

}
