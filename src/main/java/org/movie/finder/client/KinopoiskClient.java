package org.movie.finder.client;

import org.movie.finder.config.FeignConfig;
import org.movie.finder.dto.kinopoisk.KinopoiskMovie;
import org.movie.finder.dto.kinopoisk.KinopoiskMovies;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client for Kinopoisk service.
 */
@FeignClient(name = "kinopoiskClient", url = "${finder.feign-clients.kinopoisk.url}", configuration = FeignConfig.class)
public interface KinopoiskClient {

    /**
     * Get movie by movie id.
     *
     * @param id movie id.
     * @return {@link KinopoiskMovie}
     */
    @GetMapping("/movie?field=id&search={id}&token=" + "${finder.feign-clients.kinopoisk.token}")
    KinopoiskMovie getMovieById(@PathVariable(value = "id") Long id);

    /**
     * Get list of movies by properties.
     *
     * @param rateFrom rate from
     * @param rateTo rate to
     * @param yearFrom year from
     * @param yearTo year to
     * @param typeId type id
     * @param genreType genre
     * @param pageNumber page
     * @param pageLimit results for one page
     * @return list of movies in {@link KinopoiskMovies}
     */
    @GetMapping(
            "/movie?field=rating.kp&search={rateFrom}-{rateTo}&field=year&search={yearFrom}-{yearTo}" +
                    "&field=typeNumber&search={typeId}" +
                    "&field=genres.name&search={genreType}" +
                    "&page={pageNumber}&limit={pageLimit}" +
                    "&sortField=year&sortType=1&token=" +
                    "${finder.feign-clients.kinopoisk.token}")
    @SuppressWarnings("checkstyle:parameternumber")
    KinopoiskMovies getMovies(
            @PathVariable(value = "rateFrom") String rateFrom,
            @PathVariable(value = "rateTo") String rateTo,
            @PathVariable(value = "yearFrom") Integer yearFrom,
            @PathVariable(value = "yearTo") Integer yearTo,
            @PathVariable(value = "typeId") Integer typeId,
            @PathVariable(value = "genreType") String genreType,
            @PathVariable(value = "pageNumber") Integer pageNumber,
            @PathVariable(value = "pageLimit") Integer pageLimit);
}
