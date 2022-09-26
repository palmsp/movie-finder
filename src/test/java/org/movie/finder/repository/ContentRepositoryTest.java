package org.movie.finder.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.movie.finder.data.ContentGenerator;
import org.movie.finder.domain.Content;
import org.movie.finder.domain.ContentGenreLink;
import org.movie.finder.domain.ContentGenrePK;
import org.movie.finder.domain.ContentTagLink;
import org.movie.finder.domain.ContentTagPK;
import org.movie.finder.domain.Genre;
import org.movie.finder.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class ContentRepositoryTest {

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ContentTagLinksRepository contentTagLinksRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ContentGenreLinksRepository contentGenreLinksRepository;

    @BeforeAll
    private void setUp() {
        Content content1 = ContentGenerator.content1Builder().build();
        Content content2 = ContentGenerator.content2Builder().build();
        Content content3 = ContentGenerator.content3Builder().build();

        List<Content> contentList = contentRepository.saveAll(List.of(content1, content2, content3));
        Content movieId1 = contentList.stream().filter(m -> m.getName().equals(content1.getName())).findFirst()
                .orElse(null);
        Content movieId2 = contentList.stream().filter(m -> m.getName().equals(content2.getName())).findFirst()
                .orElse(null);
        Content movieId3 = contentList.stream().filter(m -> m.getName().equals(content3.getName())).findFirst()
                .orElse(null);

        Tag tag1 = Tag.builder().name("sad").build();
        Tag tag2 = Tag.builder().name("autumn").build();
        Tag tag3 = Tag.builder().name("funny").build();
        Tag tag4 = Tag.builder().name("winter").build();

        List<Tag> tags = tagRepository.saveAll(List.of(tag1, tag2, tag3, tag4));

        Long tagId1 = tags.stream().filter(t -> t.getName().equals(tag1.getName())).map(Tag::getId).findFirst()
                .orElse(null);
        Long tagId2 = tags.stream().filter(t -> t.getName().equals(tag2.getName())).map(Tag::getId).findFirst()
                .orElse(null);
        Long tagId3 = tags.stream().filter(t -> t.getName().equals(tag3.getName())).map(Tag::getId).findFirst()
                .orElse(null);
        Long tagId4 = tags.stream().filter(t -> t.getName().equals(tag4.getName())).map(Tag::getId).findFirst()
                .orElse(null);

        Genre genre1 = Genre.builder().name("comedy").build();
        Genre genre2 = Genre.builder().name("drama").build();
        List<Genre> genres = genreRepository.saveAll(List.of(genre1, genre2));

        Long genreId1 = genres.stream().filter(g -> g.getName().equals(genre1.getName())).map(Genre::getId).findFirst()
                .orElse(null);
        Long genreId2 = genres.stream().filter(g -> g.getName().equals(genre2.getName())).map(Genre::getId).findFirst()
                .orElse(null);

        //movie 1
        ContentTagLink contentTagLink1 = ContentTagLink.builder()
                .id(ContentTagPK.builder()
                        .tagId(tagId1)
                        .contentId(movieId1.getId())
                        .build())
                .build();
        ContentTagLink contentTagLink2 = ContentTagLink.builder()
                .id(ContentTagPK.builder()
                        .tagId(tagId4)
                        .contentId(movieId1.getId())
                        .build())
                .build();
        ContentGenreLink genreLink1 = ContentGenreLink.builder()
                .id(ContentGenrePK.builder()
                        .contentId(movieId1.getId())
                        .genreId(genreId1)
                        .build())
                .build();
        //movie 2
        ContentTagLink contentTagLink3 = ContentTagLink.builder()
                .id(ContentTagPK.builder()
                        .tagId(tagId2)
                        .contentId(movieId2.getId())
                        .build())
                .build();
        ContentGenreLink genreLink2 = ContentGenreLink.builder()
                .id(ContentGenrePK.builder()
                        .contentId(movieId2.getId())
                        .genreId(genreId1)
                        .build())
                .build();
        //movie 3
        ContentTagLink contentTagLink4 = ContentTagLink.builder()
                .id(ContentTagPK.builder()
                        .tagId(tagId3)
                        .contentId(movieId3.getId())
                        .build())
                .build();
        ContentGenreLink genreLink3 = ContentGenreLink.builder()
                .id(ContentGenrePK.builder()
                        .contentId(movieId3.getId())
                        .genreId(genreId2)
                        .build())
                .build();

        contentTagLinksRepository.saveAll(List.of(contentTagLink1, contentTagLink2, contentTagLink3, contentTagLink4));
        contentGenreLinksRepository.saveAll(List.of(genreLink1, genreLink2, genreLink3));
    }

    @AfterAll
    void tearDown() {
        contentTagLinksRepository.deleteAll();
        tagRepository.deleteAll();
        contentRepository.deleteAll();
    }

    @Test
    public void shouldFindMoviesByRate() {
        List<Content> contentList = contentRepository.findContentsByRateIsGreaterThanEqual(7.5);

        assertThat(contentList).hasSize(1);
        assertThat(contentList).extracting(Content::getName).containsExactlyInAnyOrder("movie3");
    }

    @Test
    public void shouldFindMoviesByReleaseYear() {
        List<Content> movies = contentRepository.findContentsByReleaseYearBetween(2000, 2001);

        assertThat(movies).hasSize(2);
        assertThat(movies).extracting(Content::getName).containsExactlyInAnyOrder("movie1", "movie2");
    }

    @Test
    public void shouldFindMoviesByProperties() {
        List<Content> contentList = contentRepository.findContentsByRateIsGreaterThanEqualAndReleaseYearIsLessThanEqual(
                7.0,
                2000);

        assertThat(contentList).hasSize(2);
        assertThat(contentList).extracting(Content::getName).containsExactlyInAnyOrder("movie2", "movie3");
    }


    @Test
    public void shouldSaveMovie() {
        Content content = Content.builder()
                .name("movie4")
                .description("some desc")
                .rate(9.0)
                .releaseYear(2022)
                .build();
        contentRepository.save(content);

        List<Content> contentList = contentRepository.findAll();
        assertThat(contentList).hasSize(4);
        assertThat(contentList).extracting(Content::getName).contains("movie4");
    }


    @Test
    void findMoviesByTagNames() {
        List<Content> contentList = contentRepository.findContentByTagNames(List.of("sad", "autumn"));

        assertThat(contentList).hasSize(2);
        assertThat(contentList).extracting(Content::getName).containsExactlyInAnyOrder("movie1", "movie2");
    }

    @Test
    void findMoviesByGenreNames() {
        List<Content> contentList = contentRepository.findContentByGenreNames(List.of("comedy"));

        assertThat(contentList).hasSize(2);
        assertThat(contentList).flatExtracting(Content::getGenreLinks)
                .map(ContentGenreLink::getGenre)
                .map(Genre::getName)
                .containsExactlyInAnyOrder("comedy", "comedy");
    }

    @Test
    void findMoviesWithoutTags() {
        Content content = Content.builder()
                .name("movie5")
                .description("some desc")
                .rate(9.0)
                .releaseYear(2022)
                .build();
        contentRepository.save(content);

        List<Content> contentList = contentRepository.findContentWithoutTags();

        assertThat(contentList).hasSize(1);
        assertThat(contentList).extracting(Content::getName).containsExactlyInAnyOrder(content.getName());
    }
}
