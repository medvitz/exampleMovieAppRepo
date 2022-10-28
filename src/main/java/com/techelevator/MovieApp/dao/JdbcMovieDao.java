package com.techelevator.MovieApp.dao;

//import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMovieDao implements MovieDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> getAllMovies() {
        String sql = "SELECT movie_id, title, release_date, length_minutes FROM movie; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        List<Movie> allMovies = new ArrayList<>(); // empty list of movies
        while (results.next()) { // while there are more movies to process
            allMovies.add(mapOneRowToOneMovieObject(results));   // add one movie to the list
        }

        return allMovies;
    }

    @Override
    public List<Movie> getMoviesByActor(String actor) {

        String sql = "SELECT movie_id, title, release_date, length_minutes " +
                     "FROM movie INNER JOIN movie_actor USING(movie_id) " +
                     "INNER JOIN person ON movie_actor.actor_id = person.person_id " +
                     "WHERE person_name ILIKE ?";

        SqlRowSet movieResults = jdbcTemplate.queryForRowSet(sql, "%" + actor + "%");
        List<Movie> matchingMovies = new ArrayList<>(); // empty list of movies
        while (movieResults.next()) { // while there are more movies to process
            matchingMovies.add(mapOneRowToOneMovieObject(movieResults));   // add one movie to the list
        }

        return matchingMovies;
    }

    private Movie mapOneRowToOneMovieObject(SqlRowSet movieRows) { // assuming movieRows has a cursor pointing to a valid row
        Movie newMovieObject = new Movie(); // create one empty movie object

        // fill it with data (from the results of the query)
        newMovieObject.setMovieId(movieRows.getInt("movie_id"));
        newMovieObject.setTitle(movieRows.getString("title"));
        newMovieObject.setLengthInMinutes(movieRows.getInt("length_minutes"));
        if (movieRows.getDate("release_date") != null) {
            newMovieObject.setReleaseDate(movieRows.getDate("release_date").toLocalDate());
        }

        return newMovieObject;
    }
}
