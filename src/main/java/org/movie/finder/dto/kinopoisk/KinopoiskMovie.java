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
public class KinopoiskMovie {

    private Long id;
    private String type;
    private String name;
    private String description;
    private int year;
    private KinopoiskRating rating;
    private List<KinopoiskGenre> genres;
}
