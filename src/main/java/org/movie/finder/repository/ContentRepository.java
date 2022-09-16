package org.movie.finder.repository;

import java.util.Collection;
import java.util.List;
import org.movie.finder.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findContentsByGenreIn(List<String> genre);

    List<Content> findContentsByReleaseYearBetween(int fromYear, int toYear);

    List<Content> findContentsByRateIsGreaterThanEqual(Double rate);

    List<Content> findContentsByRateIsGreaterThanEqualAndGenreIsAndReleaseYearIsLessThanEqual(Double rate, String genre, int year);

    @Query("select m from Content m left join ContentTag mt on m.id = mt.id.contentId where mt.id.tagId in (:tagIds)")
    List<Content> findContentsByTags(@Param("tagIds") Collection<Long> tagIds);

    @Query("select m from Content m left join ContentTag mt on m.id = mt.id.contentId join Tag t on mt.id.tagId = t.id where t.name in (:tags)")
    List<Content> findContentsByTagsName(@Param("tags") Collection<String> tags);
}
