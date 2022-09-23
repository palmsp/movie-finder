package org.movie.finder.data;

import java.util.List;

import org.movie.finder.domain.Content;

public class ContentGenerator {

    public static Content.ContentBuilder content1Builder() {
        return Content.builder()
                .name("movie1")
                .description("some desc")
                .rate(7.0)
                .releaseYear(2001);
    }

    public static Content.ContentBuilder content2Builder() {
        return Content.builder()
                .name("movie2")
                .description("some desc")
                .rate(7.0)
                .releaseYear(2000);
    }

    public static Content.ContentBuilder content3Builder() {
        return Content.builder()
                .name("movie3")
                .description("some desc")
                .rate(7.5)
                .releaseYear(1999);
    }

    public static List<Content> contentList() {
        return List.of(
                content1Builder().build(), content2Builder().build(), content2Builder().build()
        );
    }
}
