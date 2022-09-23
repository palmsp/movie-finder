package org.movie.finder.repository;

import java.util.Collection;
import java.util.List;

import org.movie.finder.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * {@link Repository} used for manipulating data with {@link Content}.
 */
@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findContentsByReleaseYearBetween(int fromYear, int toYear);

    List<Content> findContentsByRateIsGreaterThanEqual(Double rate);

    List<Content> findContentsByRateIsGreaterThanEqualAndReleaseYearIsLessThanEqual(Double rate, int year);

    @Query("select c from Content c left join ContentTagLink ct on c.id = ct.id.contentId where ct.id.tagId in (:tagIds)")
    List<Content> findContentsByTags(@Param("tagIds") Collection<Long> tagIds);

    @Query("select c from Content c left join ContentTagLink ct on c.id = ct.id.contentId " +
            "join Tag t on ct.id.tagId = t.id where t.name in (:tags)")
    List<Content> findContentsByTagNames(@Param("tags") Collection<String> tags);

    @Query("select c from Content c left join ContentGenreLink cg on c.id = cg.id.contentId where cg.id.genreId in (:genreIds)")
    List<Content> findContentsByGenres(@Param("genreIds") Collection<Long> genreIds);

    @Query("select c from Content c left join ContentGenreLink cg on c.id = cg.id.contentId " +
            "join Genre g on cg.id.genreId = g.id where g.name in (:genres)")
    List<Content> findContentsByGenreNames(@Param("genres") Collection<String> genres);

    @Query("select c from Content c left join ContentTagLink ct on c.id = ct.id.contentId where ct.id.tagId is null")
    List<Content> findContentWithoutTags();
}
