package org.movie.finder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.movie.finder.domain.Genre;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreItem {

    private Long id;
    private String name;

    /**
     * @param genre {@link Genre}
     * @return {@link GenreItem}
     */
    public static GenreItem from(Genre genre) {
        return GenreItem.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
