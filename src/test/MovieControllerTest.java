package io.peerislands.peerai.controller;

import io.peerislands.peerai.model.Movie;
import io.peerislands.peerai.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    public void testGetAllMovies() throws Exception {
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        when(movieService.getAllMovies()).thenReturn(movies);

        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetMovieById() throws Exception {
        Movie movie = new Movie();
        movie.setId("1");
        when(movieService.getMovieById("1")).thenReturn(movie);

        mockMvc.perform(get("/api/movies/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void testCreateMovie() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        when(movieService.createMovie(any(Movie.class))).thenReturn(movie);

        mockMvc.perform(post("/api/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Movie\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Test Movie"));
    }

    @Test
    public void testUpdateMovie() throws Exception {
        Movie movie = new Movie();
        movie.setId("1");
        movie.setTitle("Updated Movie");
        when(movieService.updateMovie(eq("1"), any(Movie.class))).thenReturn(movie);

        mockMvc.perform(put("/api/movies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Movie\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("Updated Movie"));
    }

    @Test
    public void testDeleteMovie() throws Exception {
        mockMvc.perform(delete("/api/movies/1"))
                .andExpect(status().isNoContent());

        verify(movieService, times(1)).deleteMovie("1");
    }
}