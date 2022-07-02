package com.zhe.spring_movie_theater.service.impl;

import com.zhe.spring_movie_theater.model.entity.Row;
import com.zhe.spring_movie_theater.model.entity.Screening;
import com.zhe.spring_movie_theater.model.entity.Ticket;
import com.zhe.spring_movie_theater.repository.ScreeningRepository;
import com.zhe.spring_movie_theater.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<Integer[][]> hallSeats(List<Ticket> tickets, List<Row> rows) {
        List<Integer[][]> seats = new ArrayList<Integer[][]>();
        for (Row i:rows) {
            Integer[][] row_seats = new Integer[i.getSeat_capacity()][2];
            Integer[] index = new Integer[i.getSeat_capacity()];
            for (int j = 0; j<i.getSeat_capacity()-1; j++) {
                row_seats[j][0] = 0;
                row_seats[j][1] = j;
            }
            for (Ticket k:tickets) {
                if (k.getNum_row().getNumber() == i.getNumber()){
                    row_seats[k.getSeat()][0] = 1;
                }
            }
            seats.add(row_seats);
        }

        return seats;
    }
}
