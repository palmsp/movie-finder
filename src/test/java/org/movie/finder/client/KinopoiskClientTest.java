package org.movie.finder.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.movie.finder.dto.kinopoisk.KinopoiskMovie;
import org.movie.finder.dto.kinopoisk.KinopoiskMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class KinopoiskClientTest {

    @Autowired
    private KinopoiskClient kinopoiskClient;

    //@Test
    public void shouldFindMovie() {
        Long id = 326L;
        KinopoiskMovie movie = kinopoiskClient.getMovieById(id);

        assertThat(movie).isNotNull();
        assertThat(movie.getName()).isEqualTo("Побег из Шоушенка");
    }

    //@Test
    public void shouldFindMovies() {
        String rateFrom = "7";
        String rateTo = "10";
        Integer yearFrom = 1990;
        Integer yearTo = 2000;
        Integer type = 1;
        Integer page = 1;
        Integer limit = 20;
        String genre = "комедия";

        KinopoiskMovies result = kinopoiskClient.getMovies(rateFrom, rateTo, yearFrom, yearTo, type, genre, page,
                limit);

        assertThat(result).isNotNull();
        assertThat(result.getLimit()).isEqualTo(limit);
        assertThat(result.getPage()).isEqualTo(page);
    }
}