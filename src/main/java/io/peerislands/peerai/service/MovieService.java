package io.peerislands.peerai.service;

import io.peerislands.peerai.model.Movie;
import io.peerislands.peerai.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Retrieves all movies.
     *
     * @return a list of all movies
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Retrieves a movie by its ID.
     *
     * @param id the ID of the movie
     * @return the movie with the specified ID, or null if not found
     */
    public Movie getMovieById(String id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }

    /**
     * Creates a new movie.
     *
     * @param movie the movie to create
     * @return the created movie
     */
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * Updates an existing movie.
     *
     * @param id    the ID of the movie to update
     * @param movie the updated movie data
     * @return the updated movie, or null if not found
     */
    public Movie updateMovie(String id, Movie movie) {
        Optional<Movie> existingMovie = movieRepository.findById(id);
        if (existingMovie.isPresent()) {
            movie.setId(id);
            return movieRepository.save(movie);
        }
        return null;
    }

    /**
     * Deletes a movie by its ID.
     *
     * @param id the ID of the movie to delete
     */
    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }

    /**
     * Searches for movies based on optional fields.
     *
     * @param title     the title of the movie (optional)
     * @param rating    the rating of the movie (optional)
     * @param category  the category of the movie (optional)
     * @return a list of movies matching the search criteria
     */
    public List<Movie> searchMovies(String title, String rating, String category) {
        Query query = new Query();

        if (title != null && !title.isEmpty()) {
            query.addCriteria(Criteria.where("title").regex(title, "i"));
        }

        if (rating != null && !rating.isEmpty()) {
            query.addCriteria(Criteria.where("rating").is(rating));
        }

        if (category != null && !category.isEmpty()) {
            query.addCriteria(Criteria.where("categories.name").is(category));
        }

        return mongoTemplate.find(query, Movie.class);
    }
}