package com.zhe.spring_movie_theater.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*; //wow, nice feature!
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="movie")
@Table(name ="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_ru")
    private String name_ru;

    @Column(name = "name_ua")
    private String name_ua;

    @Column(name = "name_en")
    private String name_en;

    @Column(name = "description_ru")
    private String description_ru;

    @Column(name = "description_en")
    private String description_en;

    @Column(name = "description_ua")
    private String description_ua;

    @OneToMany(mappedBy = "movie")
    private List<Screening> screeningList;

    public Date fromDate(){
        Date from = screeningList.get(0).getTime();
        for (Screening i: screeningList) {
            if (i.getTime().before(from)) {
                from = i.getTime();
            }
        }
        return from;
    }

    public Date toDate(){
        Date to = screeningList.get(0).getTime();
        for (Screening i: screeningList) {
            if (i.getTime().after(to)) {
                to = i.getTime();
            }
        }
        return to;
    }
}
