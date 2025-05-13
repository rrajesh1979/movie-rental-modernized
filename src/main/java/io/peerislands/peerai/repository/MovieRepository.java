package io.peerislands.peerai.repository;

import io.peerislands.peerai.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    /**
     * Finds a movie by its title.
     *
     * @param title the title of the movie
     * @return the movie with the specified title, or null if not found
     */
    Movie findByTitle(String title);
}