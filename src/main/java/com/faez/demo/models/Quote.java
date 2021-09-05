package com.faez.demo.models;

import com.faez.demo.utils.auditable.Auditable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static javax.persistence.GenerationType.AUTO;


@Entity
@Getter
@ToString
@RequiredArgsConstructor
@Table(name = "quote")


public class Quote extends Auditable {

    @Id
    @GeneratedValue(strategy = AUTO)
    @JsonProperty(access = READ_ONLY)
    private Long id;

    private String role;

    private String actor;

    private String quote;

    @JoinColumn(nullable = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
    @ManyToOne(optional = false,
            fetch = FetchType.EAGER,
            targetEntity = Movie.class,
            cascade = CascadeType.ALL)
    private Movie show;

    public Quote(String role, String actor, String quote, Movie show) {
        this.role = role;
        this.actor = actor;
        this.quote = quote;
        this.show = show;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setShow(Movie show) {
        this.show = show;
    }
}
