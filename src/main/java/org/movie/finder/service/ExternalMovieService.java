package org.movie.finder.service;

import java.util.List;

import org.movie.finder.domain.Content;

/**
 * Service for work with External movies.
 */
public interface ExternalMovieService {

    /**
     * Find movies in external source.
     *
     * @return list of {@link Content}
     */
    List<Content> findExternalMovies();

}
