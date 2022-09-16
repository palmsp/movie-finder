package org.movie.finder.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.movie.finder.domain.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class ContentRepositoryTest {

    @Autowired
    private ContentRepository contentRepository;

    @BeforeAll
    private void setUp() {
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
        contentRepository.saveAll(List.of(content1, content2, content3));
    }

    @AfterAll
    void tearDown() {
        contentRepository.deleteAll();
    }


    @Test
    public void shouldFindMoviesByGenreIn() {
        List<Content> contents = contentRepository.findContentsByGenreIn(List.of("comedy"));

        assertThat(contents).hasSize(2);
        assertThat(contents).extracting(Content::getGenre).containsExactlyInAnyOrder("comedy", "comedy");
    }

    @Test
    public void shouldFindMoviesByRate() {
        List<Content> contents = contentRepository.findContentsByRateIsGreaterThanEqual(7.5);

        assertThat(contents).hasSize(1);
        assertThat(contents).extracting(Content::getName).containsExactlyInAnyOrder("movie3");
    }

    @Test
    public void shouldFindMoviesByReleaseYear() {
        List<Content> movies = contentRepository.findContentsByReleaseYearBetween(2000, 2001);

        assertThat(movies).hasSize(2);
        assertThat(movies).extracting(Content::getName).containsExactlyInAnyOrder("movie1", "movie2");
    }

    @Test
    public void shouldFindMoviesByProperties() {
        List<Content> contents = contentRepository.findContentsByRateIsGreaterThanEqualAndGenreIsAndReleaseYearIsLessThanEqual(
                7.0,
                "comedy", 2000);

        assertThat(contents).hasSize(1);
        assertThat(contents).extracting(Content::getName).containsExactlyInAnyOrder("movie2");
    }


    @Test
    public void shouldSaveMovie() {
        Content content = Content.builder()
                .name("movie4")
                .desc("some desc")
                .rate(9.0)
                .releaseYear(2022)
                .genre("documentary")
                .build();
        contentRepository.save(content);

        List<Content> contents = contentRepository.findAll();
        assertThat(contents).hasSize(4);
        assertThat(contents).extracting(Content::getName).contains("movie4");
    }

}
