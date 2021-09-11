package com.faez.demo.movie;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Ratings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String source;
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
