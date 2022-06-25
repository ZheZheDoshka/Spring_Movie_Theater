package com.zhe.spring_movie_theater.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MovieDTO {
    private Long id;
    private String name_ru;
    private String name_ua;
    private String name_en;
    private String description_ru;
    private String description_ua;
    private String description_en;


    public MovieDTO(String name_ru, String name_ua, String name_en, String description_ru, String description_ua, String description_en) {
        this.name_ru = name_ru;
        this.name_ua = name_ua;
        this.name_en = name_en;
        this.description_ru = description_ru;
        this.description_ua = description_ua;
        this.description_en = description_en;
    }
}
