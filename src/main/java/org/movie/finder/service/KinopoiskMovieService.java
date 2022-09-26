package org.movie.finder.service;

import static java.util.Optional.ofNullable;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
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
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}.
 */
@Service
@Slf4j
public class KinopoiskMovieService implements ExternalMovieService {

    private final KinopoiskClient kinopoiskClient;

    public KinopoiskMovieService(KinopoiskClient kinopoiskClient) {
        this.kinopoiskClient = kinopoiskClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContentResult findExternalMovies(ContentFilter contentFilter) {
        log.info("Searching kinopoisk movies started");
        final ContentResult contentResult = new ContentResult();
        ContentSearchFilter search = contentFilter.getFilter();
        KinopoiskMovies kinopoiskMovies = kinopoiskClient.getMovies(
                search.getRateFrom(),
                search.getRateTo(),
                search.getYearFrom(),
                search.getYearTo(),
                search.getTypeId(),
                search.getGenre(),
                search.getPage(),
                search.getLimit());
        if (moviesExist(kinopoiskMovies)) {
            List<Content> contentList = kinopoiskMovies.getDocs().stream().map(this::from).collect(Collectors.toList());
            log.info("Found kinopoisk movies amount={}", contentList.size());
            contentResult.setContentList(contentList);
            final PagesSummary pagesSummary = PagesSummary.builder()
                    .page(kinopoiskMovies.getPage())
                    .pages(kinopoiskMovies.getPages())
                    .total(kinopoiskMovies.getTotal())
                    .limitPerPage(kinopoiskMovies.getLimit())
                    .build();
            contentResult.setPagesSummary(pagesSummary);
        }
        log.info("Searching kinopoisk movies finished");
        return contentResult;
    }

    private boolean moviesExist(KinopoiskMovies movies) {
        return movies != null && movies.getDocs() != null && !(movies.getDocs().isEmpty());
    }

    private Content from(DocsItem item) {
        return Content.builder()
                .externalId(Long.valueOf(item.getId()))
                .name(item.getName())
                .description(item.getDescription())
                .typeId(ContentType.contentTypeByName(item.getType()).getTypeId())
                .releaseYear(item.getYear())
                .rate(ofNullable(item.getRating()).map(Rating::getKp).orElse(null))
                .build();
    }
}
