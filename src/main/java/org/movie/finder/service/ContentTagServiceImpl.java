package org.movie.finder.service;

import static java.util.Collections.emptyList;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ContentTagServiceImpl implements ContentTagService {

    private final ContentRepository contentRepository;
    private final ContentTagLinksRepository contentTagLinksRepository;

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
    @Transactional(readOnly = true)
    public List<ContentItem> findContentWithoutTags() {
        List<Content> contentList = contentRepository.findContentWithoutTags();
        List<ContentItem> contentItems = contentList.stream().map(ContentItem::from).collect(Collectors.toList());
        return contentItems;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTagsToContent(List<ContentTagItem> contentTags) {
        log.info("Adding tags to content started");
        List<ContentTagLink> contentTagsEntityLinks = contentTags.stream().map(ContentTagItem::from)
                .collect(Collectors.toList());
        contentTagLinksRepository.saveAll(contentTagsEntityLinks);
        log.info("Adding tags to content finished");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContentItem> findContentByTags(List<TagItem> tags) {
        log.info("Searching content by tags started");
        List<Content> contentList = getContent(tags);
        if (isNotEmpty(contentList)) {
            List<ContentItem> contentItems = contentList.stream()
                    .map(ContentItem::from)
                    .collect(Collectors.toList());
            return contentItems;
        }
        log.info("Searching content by tags finished");
        return emptyList();
    }

    private List<Content> getContent(List<TagItem> tags) {
        List<Long> tagIds = tags.stream().map(TagItem::getId).filter(Objects::nonNull).collect(Collectors.toList());
        List<String> tagNames = tags.stream().map(TagItem::getName).collect(Collectors.toList());
        List<Content> contentList = isNotEmpty(tagIds) ? contentRepository.findContentByTags(tagIds) :
                contentRepository.findContentByTagNames(tagNames);
        return contentList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContentItem> findNotSeenMoviesByTags(List<TagItem> tags) {
        List<Content> contentList = getContent(tags);
        if (isNotEmpty(contentList)) {
            List<ContentItem> contentItems = contentList.stream().filter(Predicate.not(Content::getSeen))
                    .map(ContentItem::from)
                    .collect(Collectors.toList());
            return contentItems;
        }
        return emptyList();
    }
}
