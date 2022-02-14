package com.zhe.spring_movie_theater.service.impl;

import com.zhe.spring_movie_theater.model.entity.Hall;
import com.zhe.spring_movie_theater.repository.HallRepository;
import com.zhe.spring_movie_theater.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {
    @Autowired
    private HallRepository hallRepository;

    @Override
    public void save(Hall hall) {
        hallRepository.save(hall);
    }

    @Override
    public Page<Hall> findAllHalls(String sort, Pageable pageable) {
        return hallRepository.findAll(pageable);
    }

    @Override
    public List<Hall> findAllHalls() {
        return hallRepository.findAll();
    }

    @Override
    public Hall findById(Long id) {
        return hallRepository.findById(id).get();
    }
}
