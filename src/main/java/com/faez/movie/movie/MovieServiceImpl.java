package com.faez.movie.movie;

import com.faez.movie.exceptions.ApiRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new ApiRequestException("Movie Not Found"));
    }

    /**
     *
     * @param id
     * @param movieDto
     * @return Movie
     */
    @Override
    @Transactional
    public Movie updateMovie(Long id, @RequestBody UpdateMovieDto movieDto) {
        Movie updatedMovie = this.getMovieById(id);
        updatedMovie.setTitle(movieDto.getTitle());
        updatedMovie.setYear(movieDto.getYear());
        updatedMovie.setGenre(movieDto.getGenre());
        updatedMovie.setLength(movieDto.getLength());
        updatedMovie.setPoster(movieDto.getPosterUrl());

        return updatedMovie;
    }

    /**
     * @param id
     */
    @Override
    public Movie deleteMovie(Long id) {
        Movie movie = this.getMovieById(id);
        movieRepository.delete(movie);
        return movie;
    }


}
