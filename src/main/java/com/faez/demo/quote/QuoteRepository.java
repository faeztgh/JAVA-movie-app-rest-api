package com.faez.demo.quote;

import com.faez.demo.movie.Movie;
import com.faez.demo.quote.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Quote findByRole(String role);
    Quote findByShow(Movie show);

}
