package org.movie.finder.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagesSummary {

    private int page;
    private int pages;
    private int total;
    private int limitPerPage;
}
