package com.movies.Limepay.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDTO {
    private int page;
    private int perPage;
    private int total;
    private int totalPages;
    private List<MovieDTO> movies;
}
