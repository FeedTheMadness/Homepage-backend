package com.ftmnet.homepage.dto.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@FunctionalInterface
public interface DTOEntityMapper<T extends EntityObject, DTO extends EntityDTO<T>> {

    DTO toDTO(T t);

    default Stream<DTO> toDTOStream(Stream<T> tStream) {
        tStream = Objects.requireNonNullElseGet(tStream, Stream::empty);
        return tStream.map(this::toDTO);
    }

    default Stream<DTO> toDTOStream(List<T> tList) {
        tList = Objects.requireNonNullElseGet(tList, Collections::emptyList);
        return toDTOStream(tList.stream());
    }

    default List<DTO> toDTOs(Stream<T> tStream) {
        return toDTOStream(tStream).toList();
    }

    default List<DTO> toDTOs(List<T> tList) {
        return toDTOStream(tList).toList();
    }
}
