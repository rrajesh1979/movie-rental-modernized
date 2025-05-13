package io.peerislands.peerai.repository;

import io.peerislands.peerai.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testFindByTitle() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movieRepository.save(movie);

        Movie foundMovie = movieRepository.findByTitle("Test Movie");

        assertThat(foundMovie).isNotNull();
        assertThat(foundMovie.getTitle()).isEqualTo("Test Movie");
    }
}