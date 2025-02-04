package com.learn.mymoviedb.security.service;


import java.util.List;

import com.learn.mymoviedb.models.Movies;
import com.learn.mymoviedb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public boolean addMovie(Movies movie) {

        return movieRepository.addMovie(movie);
    }

    public List<Movies> getAllMovies() {

        return movieRepository.getAllMovies();
    }

    public boolean deleteMovieById(long id) {
        return movieRepository.deleteMovieById(id);
    }

    public boolean updateMovieById(long id, Movies movie) {
        return movieRepository.updateMovieById(id, movie);
    }

    public List<Movies> getMovieByID(long id) {
        return movieRepository.getMovieByID(id);
    }
}

