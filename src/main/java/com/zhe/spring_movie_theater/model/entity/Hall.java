package com.zhe.spring_movie_theater.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*; //wow, nice feature!
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="hall")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_row")
    private int num_row;

    @Column(name = "name")
    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "hall", fetch = FetchType.LAZY)
    private List<Row> rowList;
}
