package org.example.portierdigital.AdminPart.AController.Admins;

import org.example.portierdigital.General.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static List<UserDto> toUserDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getDateOfRegistration()));
        }
        return userDtos;
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getDateOfRegistration());
    }
}
