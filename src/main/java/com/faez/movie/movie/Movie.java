package com.faez.movie.movie;

import com.faez.movie.quote.Quote;
import com.faez.movie.utils.auditable.Auditable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "movie", schema = "public")
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Movie extends Auditable {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String title;
    private String year;
    private String genre;
    private String length;
    private String poster;
    private String actors;
    private String awards;
    private String boxOffice;
    private String country;
    private String dvd;
    private String director;
    private String language;
    private String metaScore;
    private String plot;
    private String production;
    private String rated;
    private String released;
    private String runtime;
    private String type;
    private String website;
    private String writer;
    private String imdbID;
    private String imdbRating;
    private String imdbVotes;

    @OneToMany(fetch = EAGER)
    private Set<Ratings> ratings;

    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    @OneToMany(fetch = EAGER,
            targetEntity = Quote.class,
            mappedBy = "show")
    private Set<Quote> quotes;


    public Movie(String title, String year, String genre, String length) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.length = length;
    }


}
