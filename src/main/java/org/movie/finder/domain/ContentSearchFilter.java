package org.movie.finder.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ContentSearchFilter implements Serializable {

    private String rateFrom;
    private String rateTo;
    private int yearFrom;
    private int yearTo;
    private int typeId;
    private int page;
    private int limit;
    private String genre;
}
