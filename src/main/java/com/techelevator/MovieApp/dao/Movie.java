package com.techelevator.MovieApp.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

// Domain Object -- holds a movie object in Java
public class Movie {

    @JsonIgnore
    private int movieId;

    private String title;
    private LocalDate releaseDate;
    private int lengthInMinutes;

    public Movie() { }

    public Movie(int movieId, String title, LocalDate releaseDate, int lengthInMinutes) {
        this.movieId = movieId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.lengthInMinutes = lengthInMinutes;
    }

    @Override
    public String toString() {
        return title + " (" + releaseDate + ", " + lengthInMinutes + " minutes)";
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }
}
