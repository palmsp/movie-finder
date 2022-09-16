package org.movie.finder.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movie.finder.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @BeforeEach
    void setUp() {
        Tag tag1 = Tag.builder().name("sad").build();
        Tag tag2 = Tag.builder().name("funny").build();
        Tag tag3 = Tag.builder().name("autumn").build();
        Tag tag4 = Tag.builder().name("winter").build();

        tagRepository.saveAll(List.of(tag1, tag2, tag3, tag4));
    }

    @AfterEach
    void tearDown() {
        tagRepository.deleteAll();
    }

    @Test
    void shouldFindTagsByName() {
        List<Tag> tags = tagRepository.findTagsByNameIn(List.of("sad", "funny"));

        assertThat(tags).hasSize(2);
    }

    @Test
    void shouldFindTagByName() {
        Tag tag = tagRepository.findTagByName("sad");

        assertThat(tag).isNotNull();
    }
}