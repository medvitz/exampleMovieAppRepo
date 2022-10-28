package com.techelevator.MovieApp.dao;

import java.util.List;

public interface MovieDao {

    List<Movie> getAllMovies();
    List<Movie> getMoviesByActor(String actor);

}
