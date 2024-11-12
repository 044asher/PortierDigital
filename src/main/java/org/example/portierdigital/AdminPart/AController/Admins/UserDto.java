package org.example.portierdigital.AdminPart.AController.Admins;

import java.time.LocalDateTime;

public record UserDto(Long id, String username, String email, LocalDateTime dateOfRegistration) {
}
