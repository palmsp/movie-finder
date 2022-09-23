package org.movie.finder.service;

import static java.util.Collections.emptyList;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.movie.finder.domain.Content;
import org.movie.finder.domain.ContentTagLink;
import org.movie.finder.dto.ContentItem;
import org.movie.finder.dto.ContentTagItem;
import org.movie.finder.dto.TagItem;
import org.movie.finder.repository.ContentRepository;
import org.movie.finder.repository.ContentTagLinksRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@inheritDoc}.
 */
@Service
public class ContentTagServiceImpl implements ContentTagService {

    private ContentRepository contentRepository;
    private ContentTagLinksRepository contentTagLinksRepository;

    public ContentTagServiceImpl(
            ContentRepository contentRepository,
            ContentTagLinksRepository contentTagLinksRepository) {
        this.contentRepository = contentRepository;
        this.contentTagLinksRepository = contentTagLinksRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<ContentItem> findContentWithoutTags() {
        List<Content> contents = contentRepository.findContentWithoutTags();
        List<ContentItem> contentItems = contents.stream().map(ContentItem::from).collect(Collectors.toList());
        return contentItems;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTagsToContent(List<ContentTagItem> contentTags) {
        List<ContentTagLink> contentTagsEntityLinks = contentTags.stream().map(ContentTagItem::from)
                .collect(Collectors.toList());
        contentTagLinksRepository.saveAll(contentTagsEntityLinks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<ContentItem> findContentByTags(List<TagItem> tags) {
        List<Content> contents = getContents(tags);
        if (isNotEmpty(contents)) {
            List<ContentItem> contentItems = contents.stream()
                    .map(ContentItem::from)
                    .collect(Collectors.toList());
            return contentItems;
        }
        return emptyList();
    }

    private List<Content> getContents(List<TagItem> tags) {
        List<Long> tagIds = tags.stream().map(TagItem::getId).filter(Objects::nonNull).collect(Collectors.toList());
        List<String> tagNames = tags.stream().map(TagItem::getName).collect(Collectors.toList());
        List<Content> contents = isNotEmpty(tagIds) ? contentRepository.findContentsByTags(tagIds) :
                contentRepository.findContentsByTagNames(tagNames);
        return contents;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<ContentItem> findNotSeenMoviesByTags(List<TagItem> tags) {
        List<Content> contents = getContents(tags);
        if (isNotEmpty(contents)) {
            List<ContentItem> contentItems = contents.stream().filter(Predicate.not(Content::getSeen))
                    .map(ContentItem::from)
                    .collect(Collectors.toList());
            return contentItems;
        }
        return emptyList();
    }
}
