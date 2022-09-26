package org.movie.finder.domain;

import static java.util.Optional.ofNullable;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Enum for Content types.
 */
public enum ContentType {

    MOVIE(1, "movie"),
    TV_SERIES(2, "tv-series");

    private static Map<Integer, ContentType> contentTypeIdMap = Arrays.stream(values())
            .collect(Collectors.toMap(ContentType::getTypeId, Function.identity()));

    private static Map<String, ContentType> contentTypeMap = Arrays.stream(values())
            .collect(Collectors.toMap(ContentType::getTypeName, Function.identity()));

    private int typeId;
    private String typeName;

    ContentType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public static ContentType contentTypeById(Integer typeId) {
        return ofNullable(contentTypeIdMap.get(typeId)).orElse(ContentType.MOVIE);
    }

    public static ContentType contentTypeByName(String typeName) {
        return ofNullable(contentTypeMap.get(typeName)).orElse(ContentType.MOVIE);
    }

    public Integer getTypeId() {
        return this.typeId;
    }

    public String getTypeName() {
        return this.typeName;

    }
}
