package com.faez.demo.services.interfaces;

import com.faez.demo.dto.UpdateQuoteDto;
import com.faez.demo.models.Quote;
import javassist.NotFoundException;

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
