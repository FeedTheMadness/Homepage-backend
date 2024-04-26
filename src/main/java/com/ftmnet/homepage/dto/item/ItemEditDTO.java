package com.ftmnet.homepage.dto.item;

import lombok.Builder;

import java.util.Optional;
import java.util.OptionalLong;

@Builder
public record ItemEditDTO(long id,
                          Optional<String> name,
                          Optional<String> url,
                          Optional<String> imageUrl,
                          OptionalLong categoryId) {
}
