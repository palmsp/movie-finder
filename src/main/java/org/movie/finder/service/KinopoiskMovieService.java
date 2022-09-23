package org.movie.finder.service;

import java.util.List;

import org.movie.finder.client.KinopoiskClient;
import org.movie.finder.domain.Content;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}.
 */
@Service
public class KinopoiskMovieService implements ExternalMovieService {

    private KinopoiskClient kinopoiskClient;

    public KinopoiskMovieService(KinopoiskClient kinopoiskClient) {
        this.kinopoiskClient = kinopoiskClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Content> findExternalMovies() {
        //kinopoiskClient.getMovies();
        return null;
    }

}
