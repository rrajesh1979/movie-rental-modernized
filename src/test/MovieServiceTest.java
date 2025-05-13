package io.peerislands.peerai.service;

import io.peerislands.peerai.model.Movie;
import io.peerislands.peerai.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    public void testGetAllMovies() {
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieService.getAllMovies();

        assertThat(result).isEqualTo(movies);
    }

    @Test
    public void testGetMovieById() {
        Movie movie = new Movie();
        movie.setId("1");
        when(movieRepository.findById("1")).thenReturn(Optional.of(movie));

        Movie result = movieService.getMovieById("1");

        assertThat(result).isEqualTo(movie);
    }

    @Test
    public void testCreateMovie() {
        Movie movie = new Movie();
        when(movieRepository.save(movie)).thenReturn(movie);

        Movie result = movieService.createMovie(movie);

        assertThat(result).isEqualTo(movie);
    }

    @Test
    public void testUpdateMovie() {
        Movie existingMovie = new Movie();
        existingMovie.setId("1");
        when(movieRepository.findById("1")).thenReturn(Optional.of(existingMovie));

        Movie updatedMovie = new Movie();
        updatedMovie.setTitle("Updated Movie");
        when(movieRepository.save(existingMovie)).thenReturn(updatedMovie);

        Movie result = movieService.updateMovie("1", updatedMovie);

        assertThat(result).isEqualTo(updatedMovie);
    }

    @Test
    public void testDeleteMovie() {
        movieService.deleteMovie("1");

        verify(movieRepository, times(1)).deleteById("1");
    }
}