package com.ftmnet.homepage.dto.item;

import com.ftmnet.homepage.dto.mapper.EntityDTO;
import com.ftmnet.homepage.entity.Item;
import lombok.Builder;

import java.util.Optional;

@Builder
public record ItemDTO(long id,
                      Optional<String> name,
                      Optional<String> url,
                      Optional<String> imageUrl,
                      Optional<String> categoryURI) implements EntityDTO<Item> {
}
