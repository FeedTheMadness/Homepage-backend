package com.ftmnet.homepage.dto.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@FunctionalInterface
public interface URIMapper<T extends IdMapping> {

    /**
     *
     * @return the base URI with a trailing /
     */
    String baseURI();

    default String toURI(T t) {
        return baseURI() + t.getId();
    }

    default Stream<String> toURIStream(Stream<T> tStream) {
        tStream = Objects.requireNonNullElseGet(tStream, Stream::empty);
        return tStream.map(this::toURI);
    }

    default Stream<String> toURIStream(List<T> tList) {
        tList = Objects.requireNonNullElseGet(tList, Collections::emptyList);
        return toURIStream(tList.stream());
    }

    default List<String> toURIs(Stream<T> tStream) {
        return toURIStream(tStream).toList();
    }

    default List<String> toURIs(List<T> tList) {
        return toURIStream(tList).toList();
    }
}
