package org.movie.finder.repository;

import org.movie.finder.domain.ContentTag;
import org.movie.finder.domain.ContentTagPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentTagsRepository extends JpaRepository<ContentTag, ContentTagPK> {


}
