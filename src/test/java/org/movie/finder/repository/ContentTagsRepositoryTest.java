package org.movie.finder.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.movie.finder.domain.Content;
import org.movie.finder.domain.ContentTag;
import org.movie.finder.domain.ContentTagPK;
import org.movie.finder.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
class ContentTagsRepositoryTest {

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ContentTagsRepository contentTagsRepository;

    @BeforeAll
    void setUp() {
        Content content1 = Content.builder()
                .name("movie1")
                .desc("some desc")
                .rate(7.0)
                .releaseYear(2001)
                .genre("comedy")
                .build();
        Content content2 = Content.builder()
                .name("movie2")
                .desc("some desc")
                .rate(7.0)
                .releaseYear(2000)
                .genre("comedy")
                .build();
        Content content3 = Content.builder()
                .name("movie3")
                .desc("some desc")
                .rate(7.5)
                .releaseYear(1999)
                .genre("drama")
                .build();

        List<Content> contents = contentRepository.saveAll(List.of(content1, content2, content3));
        Long movieId1 = contents.stream().filter(m -> m.getName().equals(content1.getName())).map(Content::getId).findFirst()
                .orElse(null);
        Long movieId2 = contents.stream().filter(m -> m.getName().equals(content2.getName())).map(Content::getId).findFirst()
                .orElse(null);
        Long movieId3 = contents.stream().filter(m -> m.getName().equals(content3.getName())).map(Content::getId).findFirst()
                .orElse(null);

        Tag tag1 = Tag.builder().name("sad").build();
        Tag tag2 = Tag.builder().name("autumn").build();
        Tag tag3 = Tag.builder().name("funny").build();
        Tag tag4 = Tag.builder().name("winter").build();

        List<Tag> tags = tagRepository.saveAll(List.of(tag1, tag2, tag3, tag4));

        Long tagId1 = tags.stream().filter(t -> t.getName().equals(tag1.getName())).map(Tag::getId).findFirst().orElse(null);
        Long tagId2 = tags.stream().filter(t -> t.getName().equals(tag2.getName())).map(Tag::getId).findFirst().orElse(null);
        Long tagId3 = tags.stream().filter(t -> t.getName().equals(tag3.getName())).map(Tag::getId).findFirst().orElse(null);
        Long tagId4 = tags.stream().filter(t -> t.getName().equals(tag4.getName())).map(Tag::getId).findFirst().orElse(null);


        //movie 1
        ContentTag contentTag1 = ContentTag.builder()
                .id(ContentTagPK.builder()
                        .tagId(tagId1)
                        .contentId(movieId1)
                        .build())
                .build();
        ContentTag contentTag2 = ContentTag.builder()
                .id(ContentTagPK.builder()
                        .tagId(tagId4)
                        .contentId(movieId1)
                        .build())
                .build();
        //movie 2
        ContentTag contentTag3 = ContentTag.builder()
                .id(ContentTagPK.builder()
                        .tagId(tagId2)
                        .contentId(movieId2)
                        .build())
                .build();
        //movie 3
        ContentTag contentTag4 = ContentTag.builder()
                .id(ContentTagPK.builder()
                        .tagId(tagId3)
                        .contentId(movieId3)
                        .build())
                .build();

        contentTagsRepository.saveAll(List.of(contentTag1, contentTag2, contentTag3, contentTag4));
    }

    @AfterAll
    void tearDown() {
        contentTagsRepository.deleteAll();
        tagRepository.deleteAll();
        contentRepository.deleteAll();
    }

    @Test
    void findMoviesByTagsName() {
        List<Content> contents = contentRepository.findContentsByTagsName(List.of("sad", "autumn"));

        assertThat(contents).hasSize(2);
        assertThat(contents).extracting(Content::getName).containsExactlyInAnyOrder("movie1", "movie2");
    }
}