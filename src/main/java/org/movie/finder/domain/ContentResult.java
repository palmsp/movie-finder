package org.movie.finder.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentResult {

    private List<Content> contentList;
    private PagesSummary pagesSummary;
}
