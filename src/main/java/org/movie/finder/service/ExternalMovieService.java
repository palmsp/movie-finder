package org.movie.finder.service;

import org.movie.finder.domain.ContentFilter;
import org.movie.finder.domain.ContentResult;

/**
 * Service for work with External movies.
 */
public interface ExternalMovieService {

    /**
     * Find movies in external source.
     *
     * @param filter filter
     * @return list of {@link ContentResult}
     */
    ContentResult findExternalMovies(ContentFilter filter);

}
