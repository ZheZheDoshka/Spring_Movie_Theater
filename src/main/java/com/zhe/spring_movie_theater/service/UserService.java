package com.zhe.spring_movie_theater.service;

import com.zhe.spring_movie_theater.model.entity.User;
import java.util.List;


public interface UserService {

    void save(User user);

    User findByUsername(String username);

}
