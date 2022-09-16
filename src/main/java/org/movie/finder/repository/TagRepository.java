package org.movie.finder.repository;

import java.util.List;
import org.movie.finder.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findTagsByNameIn(List<String> names);

    Tag findTagByName(String name);
}
