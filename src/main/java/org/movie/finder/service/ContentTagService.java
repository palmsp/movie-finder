package org.movie.finder.service;

import java.util.List;

import org.movie.finder.dto.ContentItem;
import org.movie.finder.dto.ContentTagItem;
import org.movie.finder.dto.TagItem;

/**
 * Service for work with Content tags.
 */
public interface ContentTagService {

    /**
     * Find content items without tags.
     *
     * @return list of {@link ContentItem}
     */
    List<ContentItem> findContentWithoutTags();

    /**
     * Add tags to content.
     *
     * @param contentTags list of tags
     */
    void addTagsToContent(List<ContentTagItem> contentTags);

    /**
     * Find content items by tags.
     *
     * @param tags list of {@link TagItem}
     * @return list of {@link ContentItem}
     */
    List<ContentItem> findContentByTags(List<TagItem> tags);
}
