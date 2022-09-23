package org.movie.finder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.movie.finder.domain.ContentTagLink;
import org.movie.finder.domain.ContentTagPK;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentTagItem {

    private Long contentId;
    private Long tagId;

    /**
     * @param contentTagLink {@link ContentTagLink}
     * @return {@link ContentTagItem}
     */
    public static ContentTagItem from(ContentTagLink contentTagLink) {
        return ContentTagItem.builder()
                .tagId(contentTagLink.getId().getTagId())
                .contentId(contentTagLink.getId().getContentId())
                .build();
    }

    /**
     * @param item {@link ContentTagItem}
     * @return {@link ContentTagLink}
     */
    public static ContentTagLink from(ContentTagItem item) {
        return ContentTagLink.builder()
                .id(ContentTagPK.builder()
                        .contentId(item.getContentId())
                        .tagId(item.getTagId())
                        .build()).build();

    }

}
