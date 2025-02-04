package com.learn.mymoviedb.controllers;

import com.learn.mymoviedb.models.Movies;
import com.learn.mymoviedb.security.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageRenderController {

    @Autowired
    private MovieService movieService;

    @GetMapping({"", "/"})
    public String homepage(Model model) {
        List<Movies> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
