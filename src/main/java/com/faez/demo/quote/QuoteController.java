package com.faez.demo.quote;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.faez.demo.routes.ApiRoute.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteServiceImpl quoteService;

    @GetMapping(QUOTES_API)
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Quote>> getQuotes() {
        return ResponseEntity.ok().body(quoteService.getQuotes());
    }

    @GetMapping(GET_QUOTE_BY_ID_API)
    @PreAuthorize("permitAll()")
    public Quote getQuote(@PathVariable Long id) {
        return quoteService.getQuoteById(id);
    }


    @PostMapping(QUOTES_API)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Quote> createQuote(@Validated @RequestBody Quote quote) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(QUOTES_API).toUriString());
        return ResponseEntity.created(uri).body(quoteService.saveQuote(quote));
    }

    @PutMapping(UPDATE_QUOTE_API)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Quote updateQuote(@PathVariable Long id, @Validated @RequestBody UpdateQuoteDto quoteDto) {
        return quoteService.updateQuote(id, quoteDto);
    }

    @DeleteMapping(DELETE_QUOTE_API)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Quote deleteQuote(@PathVariable Long id) {
        return quoteService.deleteQuote(id);
    }

}
