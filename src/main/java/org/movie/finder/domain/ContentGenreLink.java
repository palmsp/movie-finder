package org.movie.finder.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {@link Entity} for Content Genre Link.
 */
@Entity
@Table(name = "content_genre_link", schema = "content")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ContentGenreLink {

    @EmbeddedId
    private ContentGenrePK id;

    @ManyToOne
    @JoinColumn(name = "content_id", insertable = false, updatable = false)
    private Content content;

    @ManyToOne
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private Genre genre;
}
