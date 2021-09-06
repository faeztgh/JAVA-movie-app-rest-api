package com.faez.demo.movie;

import javassist.NotFoundException;

import java.util.List;

public interface IMovieService {
    Movie saveMovie(Movie movie);

    Movie getMovie(String title);

    List<Movie> getMovies();

    Movie getMovieById(Long id) throws NotFoundException;

    Movie updateMovie(Long id, UpdateMovieDto movieDto) throws NotFoundException;

    Movie deleteMovie(Long id) throws NotFoundException;
}
