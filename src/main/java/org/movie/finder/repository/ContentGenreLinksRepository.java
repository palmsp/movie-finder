package org.movie.finder.repository;

import org.movie.finder.domain.ContentGenreLink;
import org.movie.finder.domain.ContentGenrePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Repository} used for manipulating data with {@link ContentGenreLink}.
 */
@Repository
public interface ContentGenreLinksRepository extends JpaRepository<ContentGenreLink, ContentGenrePK> {

}
