package org.movie.finder.repository;

import org.movie.finder.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Repository} used for manipulating data with {@link Genre}.
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
