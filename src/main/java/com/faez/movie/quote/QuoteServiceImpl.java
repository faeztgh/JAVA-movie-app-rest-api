package com.faez.movie.quote;

import com.faez.movie.exceptions.ApiRequestException;
import com.faez.movie.movie.Movie;
import com.faez.movie.movie.MovieServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class QuoteServiceImpl implements IQuoteService {
    private final QuoteRepository quoteRepository;
    private final MovieServiceImpl movieService;

    @Override
    public Quote getQuoteByRole(String role) {
        return quoteRepository.findByRole(role);
    }

    @Override
    public Quote saveQuote(Quote quote) {
        Movie show = movieService.getMovie(quote.getShow().getTitle());
        quote.setShow(show);
        return quoteRepository.save(quote);
    }

    @Override
    public Quote getQuoteByShow(String show) {
        Movie foundShow = movieService.getMovie(show);
        return quoteRepository.findByShow(foundShow);
    }

    @Override
    public List<Quote> getQuotes() {
        return quoteRepository.findAll();
    }

    @Override
    public Quote getQuoteById(Long id) {
        return quoteRepository.findById(id).orElseThrow(() -> new ApiRequestException("Quote Not Found!"));
    }

    @Override
    public Quote updateQuote(Long id, UpdateQuoteDto quoteDto) {
        Movie show = movieService.getMovie(quoteDto.getShow());
        Quote updatedQuote = new Quote();
        updatedQuote.setRole(quoteDto.getRole());
        updatedQuote.setActor(quoteDto.getActor());
        updatedQuote.setQuote(quoteDto.getQuote());
        if (show != null) {
            updatedQuote.setShow(show);
        }

        return updatedQuote;
    }

    @Override
    public Quote deleteQuote(Long id) {
        Quote deletedQuote = quoteRepository.findById(id).orElseThrow(() -> new ApiRequestException("Cannot delete the quote. Quote Not Found!"));
        quoteRepository.delete(deletedQuote);
        return deletedQuote;
    }
}
