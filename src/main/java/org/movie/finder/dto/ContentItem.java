package org.movie.finder.dto;

import static java.util.Optional.ofNullable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.movie.finder.domain.Content;
import org.movie.finder.domain.ContentGenreLink;
import org.movie.finder.domain.ContentTagLink;
import org.movie.finder.domain.ContentType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentItem {

    private Long id;

    private String name;

    private String type;

    private Integer typeId;

    private String desc;

    private Integer releaseYear;

    private Double rate;

    private List<TagItem> tags;

    private List<GenreItem> genres;

    private Boolean seen;

    /**
     * @param content {@link Content}
     * @return {@link ContentItem}
     */
    public static ContentItem from(Content content) {
        final List<TagItem> tagItems = ofNullable(content.getTagLinks())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(ContentTagLink::getTag)
                .map(TagItem::from)
                .collect(Collectors.toList());
        final List<GenreItem> genreItems = ofNullable(content.getGenreLinks())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(ContentGenreLink::getGenre)
                .map(GenreItem::from)
                .collect(Collectors.toList());

        return ContentItem.builder()
                .id(content.getId())
                .name(content.getName())
                .desc(content.getDescription())
                .typeId(content.getTypeId())
                .type(ContentType.contentTypeById(content.getTypeId()).getTypeName())
                .releaseYear(content.getReleaseYear())
                .rate(content.getRate())
                .seen(content.getSeen())
                .tags(tagItems)
                .genres(genreItems)
                .build();
    }
}
