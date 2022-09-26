package org.movie.finder.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.movie.finder.client.KinopoiskClient;
import org.movie.finder.domain.Content;
import org.movie.finder.domain.ContentFilter;
import org.movie.finder.domain.ContentResult;
import org.movie.finder.domain.ContentSearchFilter;
import org.movie.finder.domain.ContentType;
import org.movie.finder.domain.PagesSummary;
import org.movie.finder.dto.kinopoisk.DocsItem;
import org.movie.finder.dto.kinopoisk.KinopoiskMovies;
import org.movie.finder.dto.kinopoisk.Rating;

@ExtendWith(MockitoExtension.class)
class KinopoiskMovieServiceTest {

    @InjectMocks
    private KinopoiskMovieService movieService;
    @Mock
    private KinopoiskClient kinopoiskClient;

    @Test
    void shouldFindExternalMovies() {
        ContentSearchFilter searchFilter = ContentSearchFilter.builder()
                .genre("comedy")
                .rateFrom("7")
                .rateTo("8")
                .yearFrom(1999)
                .yearTo(2000)
                .typeId(1)
                .page(1)
                .limit(10)
                .build();
        ContentFilter filter = ContentFilter.builder()
                .filter(searchFilter)
                .build();

        DocsItem docsItem1 = DocsItem.builder()
                .id(1)
                .name("movie1")
                .description("desc1")
                .type("movies")
                .year(2000)
                .rating(Rating.builder().kp(7.0).build())
                .build();
        DocsItem docsItem2 = DocsItem.builder()
                .id(2)
                .name("movie2")
                .description("desc2")
                .build();
        List<DocsItem> docsItems = List.of(docsItem1, docsItem2);

        KinopoiskMovies kinopoiskMovies = KinopoiskMovies.builder()
                .docs(docsItems)
                .limit(searchFilter.getLimit())
                .page(searchFilter.getPage())
                .pages(5)
                .total(10)
                .build();

        when(kinopoiskClient.getMovies(searchFilter.getRateFrom(), searchFilter.getRateTo(),
                searchFilter.getYearFrom(), searchFilter.getYearTo(), searchFilter.getTypeId(),
                searchFilter.getGenre(), searchFilter.getPage(), searchFilter.getLimit()))
                .thenReturn(kinopoiskMovies);

        ContentResult result = movieService.findExternalMovies(filter);

        assertThat(result).isNotNull();

        List<Content> contentList = result.getContentList();
        assertThat(contentList).hasSize(2);

        Content content1 = contentList.stream().filter(content -> content.getExternalId().equals(1L)).findFirst()
                .orElse(null);
        Content content2 = contentList.stream().filter(content -> content.getExternalId().equals(2L)).findFirst()
                .orElse(null);

        assertThat(content1.getName()).isEqualTo(docsItem1.getName());
        assertThat(content1.getDescription()).isEqualTo(docsItem1.getDescription());
        assertThat(content1.getTypeId()).isEqualTo(ContentType.MOVIE.getTypeId());
        assertThat(content1.getReleaseYear()).isEqualTo(docsItem1.getYear());
        assertThat(content1.getRate()).isEqualTo(docsItem1.getRating().getKp());

        assertThat(content2.getName()).isEqualTo(docsItem2.getName());
        assertThat(content2.getDescription()).isEqualTo(docsItem2.getDescription());
        assertThat(content2.getTypeId()).isEqualTo(ContentType.MOVIE.getTypeId());
        assertThat(content2.getReleaseYear()).isEqualTo(docsItem2.getYear());
        assertThat(content2.getRate()).isEqualTo(null);

        PagesSummary pagesSummary = result.getPagesSummary();

        assertThat(pagesSummary.getPages()).isEqualTo(kinopoiskMovies.getPages());
        assertThat(pagesSummary.getPage()).isEqualTo(kinopoiskMovies.getPage());
        assertThat(pagesSummary.getTotal()).isEqualTo(kinopoiskMovies.getTotal());
        assertThat(pagesSummary.getLimitPerPage()).isEqualTo(kinopoiskMovies.getLimit());
    }
}