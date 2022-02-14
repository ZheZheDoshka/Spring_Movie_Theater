package com.zhe.spring_movie_theater.service.impl;

import com.zhe.spring_movie_theater.model.entity.Screening;
import com.zhe.spring_movie_theater.repository.ScreeningRepository;
import com.zhe.spring_movie_theater.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeeningServiceImpl implements ScreeningService {
    @Autowired
    private ScreeningRepository screeningRepository;

    @Override
    public void save(Screening screening) {
        screeningRepository.save(screening);
    }

    @Override
    public Page<Screening> findAllScreenings(String sort, Pageable pageable) {
        return screeningRepository.findAll(pageable);
    }

    @Override
    public List<Screening> findAllScreenings() {
        return screeningRepository.findAll();
    }

    @Override
    public Screening findById(Long id) {
        return screeningRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        screeningRepository.deleteById(id);
    }
}
