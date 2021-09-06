package com.faez.demo.movie;

import com.faez.demo.quote.Quote;
import com.faez.demo.utils.auditable.Auditable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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
    private String posterUrl;

    @Column(nullable = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
    @OneToMany(fetch = FetchType.EAGER,
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
