package org.movie.finder.repository;

import org.movie.finder.domain.ContentTagLink;
import org.movie.finder.domain.ContentTagPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Repository} used for manipulating data with {@link ContentTagLink}.
 */
@Repository
public interface ContentTagLinksRepository extends JpaRepository<ContentTagLink, ContentTagPK> {

}
