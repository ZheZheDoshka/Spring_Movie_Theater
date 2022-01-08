package com.zhe.spring_movie_theater.model.DTO;

import com.zhe.spring_movie_theater.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private Role role;

}
