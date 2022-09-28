package org.movie.finder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@Table(name = "content_filter", schema = "content")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class ContentFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Type(type = "jsonb")
    @Column(name = "filter", columnDefinition = "jsonb")
    private ContentSearchFilter filter;

    @Column(name = "current_page")
    private int currentPage;

    @Column(name = "pages")
    private int pages;

    @Column(name = "total")
    private int total;

    @Column(name = "limit_per_page")
    private int limitPerPage;

    @Version
    @Column(name = "version")
    private Integer version;
}
