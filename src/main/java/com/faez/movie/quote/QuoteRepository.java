package com.faez.movie.quote;

import com.faez.movie.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Quote findByRole(String role);
    Quote findByShow(Movie show);

}
