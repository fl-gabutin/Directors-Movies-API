package com.movies.Limepay.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.Limepay.DTO.DirectorResponseDTO;
import com.movies.Limepay.DTO.MovieDTO;
import com.movies.Limepay.DTO.MovieResponseDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private static final String URL = "https://wiremock.dev.eroninternational.com/api/movies/search?page=";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Cacheable(value = "moviesCache", key = "#page")
    public MovieResponseDTO getMovies(int page){
        String jsonResponse = restTemplate.getForObject(URL + page, String.class);
        try {
            JsonNode response = objectMapper.readTree(jsonResponse);

            List<MovieDTO> movies = new ArrayList<>();
            for (JsonNode movie : response.get("data")) {
                movies.add(new MovieDTO(
                        movie.get("Title").asText(),
                        movie.get("Year").asText(),
                        movie.get("Rated").asText(),
                        movie.get("Released").asText(),
                        movie.get("Runtime").asText(),
                        movie.get("Genre").asText(),
                        movie.get("Director").asText(),
                        movie.get("Writer").asText(),
                        movie.get("Actors").asText()
                ));
            }

            return new MovieResponseDTO(
                    response.get("page").asInt(),
                    response.get("per_page").asInt(),
                    response.get("total").asInt(),
                    response.get("total_pages").asInt(),
                    movies
            );

        } catch (Exception e) {
            throw new RuntimeException("Error parsing JSON response", e);
        }
    }

    @Cacheable(value = "directorsCache", key = "#threshold")
    public DirectorResponseDTO getDirectors(int threshold) {
        Map<String, Integer> directorCount = new HashMap<>();
        int currentPage = 1;
        int totalPages = 1;

        try {
            while (currentPage <= totalPages) {
                MovieResponseDTO response = getMovies(currentPage);

                if (currentPage == 1) {
                    totalPages = response.getTotalPages();
                }

                for (MovieDTO movie : response.getMovies()) {
                    String director = movie.getDirector();
                    directorCount.put(director, directorCount.getOrDefault(director, 0) + 1);
                }
                currentPage++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> directors = directorCount.entrySet().stream()
                .filter(entry -> entry.getValue() > threshold)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());

        return new DirectorResponseDTO(directors);
    }
}
