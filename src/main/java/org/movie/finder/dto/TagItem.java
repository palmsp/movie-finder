package org.movie.finder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.movie.finder.domain.Tag;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagItem {

    private Long id;
    private String name;
    private String category;

    /**
     * @param tag {@link Tag}
     * @return {@link TagItem}
     */
    public static TagItem from(Tag tag) {
        return TagItem.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
}
