package com.faez.demo.repositories;

import com.faez.demo.models.Movie;
import com.faez.demo.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Quote findByRole(String role);
    Quote findByShow(Movie show);

}
