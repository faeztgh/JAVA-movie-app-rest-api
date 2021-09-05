package com.faez.demo.controllers;

import com.faez.demo.dto.UpdateMovieDto;
import com.faez.demo.models.Movie;
import com.faez.demo.services.MovieServiceImpl;
import javassist.NotFoundException;
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
public class MovieController {
    private final MovieServiceImpl movieService;

    @GetMapping(MOVIES_API)
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok().body(movieService.getMovies());
    }

    @GetMapping(GET_MOVIE_BY_ID_API)
    public Movie getMovie(@PathVariable Long id) throws NotFoundException {
        return movieService.getMovieById(id);
    }

    @PostMapping(MOVIES_API)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Movie> createMovie(@Validated @RequestBody Movie movie) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(MOVIES_API).toUriString());
        return ResponseEntity.created(uri).body(movieService.saveMovie(movie));
    }

    @PutMapping(UPDATE_MOVIE_API)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Movie updateMovie(@PathVariable Long id, @Validated @RequestBody UpdateMovieDto movieDto) throws NotFoundException {
        return movieService.updateMovie(id, movieDto);
    }

    @DeleteMapping(DELETE_MOVIE_API)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Movie deleteMovie(@PathVariable Long id) throws NotFoundException {
        return movieService.deleteMovie(id);
    }

}


