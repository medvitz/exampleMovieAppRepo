package com.techelevator.MovieApp.controllers;

import com.techelevator.MovieApp.dao.JdbcMovieDao;
import com.techelevator.MovieApp.dao.Movie;
import com.techelevator.MovieApp.dao.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String greeting() {
        return "<H1>Hello World!</H1>";
    }

    @RequestMapping(path = "/movies", method = RequestMethod.GET)
    public List<Movie> getAllMovies(@RequestParam(required = false) String actor) {
        if (actor == null) {
            return movieDao.getAllMovies();
        }
        return movieDao.getMoviesByActor(actor);
    }

    @RequestMapping(path = "/movies/coolio", method = RequestMethod.GET)
    public List<Movie> awesomeMovies() {
        System.out.println("Getting the movies that are super coolio!");
        return null;
    }

    @RequestMapping(path = "/publish", method = RequestMethod.GET)
    public String message(@RequestParam(required = false) String message) {
        System.out.println(message);
        return "Published!";
    }
}
