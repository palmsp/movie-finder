package org.movie.finder.dto.kinopoisk;

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
public class DocsItem {

    private Integer year;
    private Rating rating;
    private String name;
    private ExternalId externalId;
    private String description;
    private Votes votes;
    private Integer id;
    private String alternativeName;
    private String type;
    private Poster poster;
}
