package org.movie.finder.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.movie.finder.data.ContentGenerator;
import org.movie.finder.domain.Content;
import org.movie.finder.domain.ContentTagLink;
import org.movie.finder.dto.ContentItem;
import org.movie.finder.dto.ContentTagItem;
import org.movie.finder.dto.TagItem;
import org.movie.finder.repository.ContentRepository;
import org.movie.finder.repository.ContentTagLinksRepository;

@ExtendWith(MockitoExtension.class)
class ContentTagServiceTest {

    @InjectMocks
    private ContentTagServiceImpl contentTagService;
    @Mock
    private ContentRepository contentRepository;
    @Mock
    private ContentTagLinksRepository contentTagLinksRepository;

    @Test
    void shouldFindContentWithoutTags() {
        List<Content> contentList = List.of(
                ContentGenerator.content1Builder().build(),
                ContentGenerator.content2Builder().build());
        when(contentRepository.findContentWithoutTags()).thenReturn(contentList);

        List<ContentItem> contentItems = contentTagService.findContentWithoutTags();

        assertThat(contentItems).isNotNull();
        assertThat(contentItems).hasSize(2);
    }

    @Test
    void shouldAddTagsToContent() {
        ContentTagItem tagItem1 = ContentTagItem.builder()
                .contentId(100L)
                .tagId(98L)
                .build();
        ContentTagItem tagItem2 = ContentTagItem.builder()
                .contentId(101L)
                .tagId(99L)
                .build();
        List<ContentTagItem> contentTagItems = List.of(tagItem1, tagItem2);
        List<ContentTagLink> contentTagLinks = contentTagItems.stream()
                .map(ContentTagItem::from)
                .collect(Collectors.toList());
        when(contentTagLinksRepository.saveAll(anyList())).thenReturn(contentTagLinks);

        contentTagService.addTagsToContent(contentTagItems);

        verify(contentTagLinksRepository).saveAll(contentTagLinks);
    }

    @Test
    void shouldFindContentByTagIds() {
        TagItem tagItem1 = TagItem.builder()
                .id(98L)
                .name("winter")
                .build();
        TagItem tagItem2 = TagItem.builder()
                .id(99L)
                .name("sunny")
                .build();
        List<TagItem> tagItems = List.of(tagItem1, tagItem2);
        List<Long> tagIds = tagItems.stream().map(TagItem::getId).collect(Collectors.toList());
        List<Content> contentList = ContentGenerator.contentList();
        when(contentRepository.findContentByTags(tagIds)).thenReturn(contentList);

        List<ContentItem> contentItems = contentTagService.findContentByTags(tagItems);

        assertThat(contentItems).isNotNull();
        assertThat(contentItems).hasSize(3);
    }

    @Test
    void shouldFindContentByTagNames() {
        TagItem tagItem1 = TagItem.builder()
                .id(null)
                .name("winter")
                .build();
        TagItem tagItem2 = TagItem.builder()
                .id(null)
                .name("sunny")
                .build();
        List<TagItem> tagItems = List.of(tagItem1, tagItem2);
        List<String> tags = tagItems.stream().map(TagItem::getName).collect(Collectors.toList());
        List<Content> contentList = ContentGenerator.contentList();
        when(contentRepository.findContentByTagNames(tags)).thenReturn(contentList);

        List<ContentItem> contentItems = contentTagService.findContentByTags(tagItems);

        assertThat(contentItems).isNotNull();
        assertThat(contentItems).hasSize(3);
    }

}