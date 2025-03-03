package com.movies.Limepay.Controller;

import com.movies.Limepay.DTO.DirectorResponseDTO;
import com.movies.Limepay.DTO.MovieResponseDTO;
import com.movies.Limepay.Service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class MovieController {


    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<MovieResponseDTO> getMovies(@RequestParam(defaultValue = "1")int page) {
        return ResponseEntity.ok(movieService.getMovies(page));
    }

    @GetMapping("/directors")
    public ResponseEntity<DirectorResponseDTO> getDirectors(@RequestParam int threshold) {
        return ResponseEntity.ok(movieService.getDirectors(threshold));
    }

}
