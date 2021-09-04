package com.faez.demo.models;

import com.faez.demo.utils.auditable.Auditable;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie extends Auditable {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String title;
    private String year;
    private String genre;
    private String length;
    private String posterUrl;


    public Movie(String title, String year, String genre, String length) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.length = length;
    }
}
