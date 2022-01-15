package com.zhe.spring_movie_theater.service.impl;

import com.zhe.spring_movie_theater.model.entity.Hall;
import com.zhe.spring_movie_theater.repository.HallRepository;
import com.zhe.spring_movie_theater.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HallServiceImpl implements HallService {
    @Autowired
    private HallRepository hallRepository;

    @Override
    public void save(Hall hall) {
        hallRepository.save(hall);
    }
}
