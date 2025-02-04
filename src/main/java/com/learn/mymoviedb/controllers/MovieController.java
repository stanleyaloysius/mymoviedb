package com.learn.mymoviedb.controllers;

import java.util.List;

import com.learn.mymoviedb.models.Movies;
import com.learn.mymoviedb.security.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public List<Movies> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/search/{id}")
    public List<Movies> getMovieByID(@PathVariable("id") long id) {
        return movieService.getMovieByID(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public String addMovie(@RequestBody Movies movie) {
        boolean success = movieService.addMovie(movie);

        if (success) {
            return "Successfully added movie";
        }
        else {
            return "Couldn't add movie, check the order";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable("id") long id) {
        boolean success = movieService.deleteMovieById(id);
        if (success) {
            return ResponseEntity.ok("Movie Deleted Successfully");
        } else {
            return ResponseEntity.status(404).body("Invalid movie id or failed to delete movie");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable("id") long id, @RequestBody Movies movie) {
        boolean success = movieService.updateMovieById(id, movie);

        if (success) {
            return ResponseEntity.ok("Movie updated successfully");
        } else {
            return ResponseEntity.status(404).body("Invalid ID, or Failed to Update movie");
        }
    }


}
