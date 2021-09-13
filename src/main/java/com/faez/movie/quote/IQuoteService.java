package com.faez.movie.quote;

import java.util.List;


public interface IQuoteService {
    Quote getQuoteByRole(String role);

    Quote saveQuote(Quote quote);

    Quote getQuoteByShow(String show);

    List<Quote> getQuotes();

    Quote getQuoteById(Long id) ;

    Quote updateQuote(Long id, UpdateQuoteDto quoteDto) ;

    Quote deleteQuote(Long id) ;
}
