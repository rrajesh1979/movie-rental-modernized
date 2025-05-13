package io.peerislands.peerai.controller;

import io.peerislands.peerai.model.Movie;
import io.peerislands.peerai.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Retrieves all movies.
     *
     * @return a list of all movies
     */
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    /**
     * Retrieves a movie by its ID.
     *
     * @param id the ID of the movie
     * @return the movie with the specified ID, or 404 Not Found if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Creates a new movie.
     *
     * @param movie the movie to create
     * @return the created movie
     */
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    /**
     * Updates an existing movie.
     *
     * @param id    the ID of the movie to update
     * @param movie the updated movie data
     * @return the updated movie, or 404 Not Found if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable String id, @RequestBody Movie movie) {
        Movie updatedMovie = movieService.updateMovie(id, movie);
        if (updatedMovie != null) {
            return ResponseEntity.ok(updatedMovie);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Deletes a movie by its ID.
     *
     * @param id the ID of the movie to delete
     * @return 204 No Content if the movie is successfully deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Searches for movies based on optional fields.
     *
     * @param title     the title of the movie (optional)
     * @param rating    the rating of the movie (optional)
     * @param category  the category of the movie (optional)
     * @return a list of movies matching the search criteria
     */
    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String rating,
            @RequestParam(required = false) String category) {
        List<Movie> movies = movieService.searchMovies(title, rating, category);
        return ResponseEntity.ok(movies);
    }
}
