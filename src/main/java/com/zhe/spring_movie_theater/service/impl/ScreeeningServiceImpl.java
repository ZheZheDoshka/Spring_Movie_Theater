package com.zhe.spring_movie_theater.service.impl;

import com.zhe.spring_movie_theater.model.entity.Screening;
import com.zhe.spring_movie_theater.repository.ScreeningRepository;
import com.zhe.spring_movie_theater.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;

public class ScreeeningServiceImpl implements ScreeningService {
    @Autowired
    ScreeningRepository screeningRepository;

    @Override
    public void save(Screening screening) {
        screeningRepository.save(screening);
    }
}
