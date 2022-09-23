package org.movie.finder.dto.kinopoisk;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class KinopoiskMovies {

    private Integer total;
    private Integer pages;
    private List<DocsItem> docs;
    private Integer limit;
    private Integer page;
}