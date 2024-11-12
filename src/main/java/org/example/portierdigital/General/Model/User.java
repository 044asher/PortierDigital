package org.example.portierdigital.General.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty(message = "Username can not be empty!")
    @Size(min = 2, max = 30, message = "Username should be between 2 and 30 characters")
    private String username;

    private String password;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email can not be empty!")
    private String email;

    private String role;

    private LocalDateTime dateOfRegistration;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = "ROLE_UNCHECKED";
    }
}
