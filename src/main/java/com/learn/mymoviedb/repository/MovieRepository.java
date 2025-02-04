package com.learn.mymoviedb.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.learn.mymoviedb.models.Movies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = LoggerFactory.getLogger(MovieRepository.class);

        public List<Movies> getAllMovies() {
            List<Movies> movieDetails = new ArrayList<Movies>();
            String query = "SELECT * from movies";

            try {
                Connection connection = dataSource.getConnection();

                PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Movies movie = new Movies();
                    movie.setId(rs.getLong("id"));
                    movie.setTitle(rs.getString("title"));
                    movie.setDescription(rs.getString("description"));
                    movie.setTrailerUrl(rs.getString("region"));
                    movie.setTrailerUrl(rs.getString("language"));
                    movie.setTrailerUrl(rs.getString("trailer_url"));
                    movieDetails.add(movie);
                }

            } catch(Exception e) {
                logger.error("Error fetcting movies", e);
            }
            return movieDetails;
        }

    public boolean addMovie(Movies movie) {

        String query = "INSERT INTO movies (title, description, language, region, trailer_url) VALUES (?, ?, ?, ?, ?)";

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDescription());
            stmt.setString(3, movie.getLanguage());
            stmt.setString(4, movie.getRegion());
            stmt.setString(5, movie.getTrailerUrl());


            int rowInserted = stmt.executeUpdate();

            return rowInserted > 0;
        } catch(Exception e) {
            logger.error("Error while updating movie", e);
            return false;
        }
    }


    public boolean deleteMovieById(long id) {
        String query = "DELETE FROM movies WHERE id = ?";

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setLong(1, id);

            int rowDeleted = stmt.executeUpdate();

            return rowDeleted > 0;
        } catch (Exception e) {
            logger.error("Error while deleting movie", e);
            return false;
        }
    }

    public boolean updateMovieById(long id, Movies movie) {
        String query = "UPDATE movies SET title = ?, description = ?, trailer_url = ? WHERE id = ?";

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDescription());
            stmt.setString(3, movie.getTrailerUrl());
            stmt.setLong(4, id);

            int updatedRows = stmt.executeUpdate();
            return updatedRows > 0;
        } catch (Exception e) {
            logger.error("Error while updating movies");
            return false;
        }
    }

    public List<Movies> getMovieByID(long id) {
        List<Movies> movieDetails = new ArrayList<Movies>();
        String query = "SELECT * FROM movies WHERE id = ? UNION ";
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Movies movie = new Movies();
                movie.setId(rs.getLong("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDescription(rs.getString("description"));
                movie.setTrailerUrl(rs.getString("trailer_url"));
                movieDetails.add(movie);
            }
            else {
                logger.error("Invalid ID");
            }

        } catch(Exception e) {
            logger.error("Error fetcting movies", e);
        }
        return movieDetails;
    }
}
