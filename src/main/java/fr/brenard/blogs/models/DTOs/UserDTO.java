package fr.brenard.blogs.models.DTOs;

import lombok.*;

import java.util.Date;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String username;
    private String emailAddress;
    private Date registerDate;


}
