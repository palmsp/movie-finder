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
 * {@link Entity} for Content Tag Link.
 */
@Entity
@Table(name = "content_tag_link", schema = "content")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ContentTagLink {

    @EmbeddedId
    private ContentTagPK id;

    @ManyToOne
    @JoinColumn(name = "content_id", insertable = false, updatable = false)
    private Content content;

    @ManyToOne
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    private Tag tag;
}
