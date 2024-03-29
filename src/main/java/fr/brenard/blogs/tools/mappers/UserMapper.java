package fr.brenard.blogs.tools.mappers;

import fr.brenard.blogs.models.DTOs.UserDTO;
import fr.brenard.blogs.models.entities.User;

public class UserMapper {

    public static UserDTO fromEntity(User entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .emailAddress(entity.getEmailAddress())
                .registerDate(entity.getRegisterDate())
                .build();
    }

}
