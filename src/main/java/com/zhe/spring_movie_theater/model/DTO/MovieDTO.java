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
}
