package com.faez.demo.services;

import com.faez.demo.dto.UpdateMovieDto;
import com.faez.demo.exceptions.ApiRequestException;
import com.faez.demo.models.Movie;
import com.faez.demo.repositories.MovieRepository;
import com.faez.demo.services.interfaces.IMovieService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements IMovieService {

    private final MovieRepository movieRepository;

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovie(String title) {
        return movieRepository.findByTitle(title);
    }


    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) throws NotFoundException {
        return movieRepository.findById(id).orElseThrow(() -> new ApiRequestException("Movie Not Found"));
    }

    @Override
    @Transactional
    public Movie updateMovie(Long id, UpdateMovieDto movieDto) throws NotFoundException {
        Movie updatedMovie = this.getMovieById(id);
        updatedMovie.setTitle(movieDto.getTitle());
        updatedMovie.setYear(movieDto.getYear());
        updatedMovie.setGenre(movieDto.getGenre());
        updatedMovie.setLength(movieDto.getLength());
        updatedMovie.setPosterUrl(movieDto.getPosterUrl());

        return updatedMovie;
    }

    @Override
    public Movie deleteMovie(Long id) throws NotFoundException {
        Movie movie = this.getMovieById(id);
        movieRepository.delete(movie);
        return movie;
    }


}
