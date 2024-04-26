package com.ftmnet.homepage.dto.category;

import com.ftmnet.homepage.dto.mapper.EntityDTO;
import com.ftmnet.homepage.entity.Category;
import lombok.Builder;

import java.util.List;
import java.util.Optional;

@Builder
public record CategoryDTO(long id,
                          Optional<String> name,
                          List<String> itemURIs) implements EntityDTO<Category> {
}
