package com.ftmnet.homepage.dto.category;

import com.ftmnet.homepage.configuration.annotation.DTOMapper;
import com.ftmnet.homepage.dto.mapper.DTOEntityMapper;
import com.ftmnet.homepage.dto.mapper.URIMapperStore;
import com.ftmnet.homepage.entity.Category;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Optional;

@DTOMapper
@RequiredArgsConstructor
public class CategoryMapper implements DTOEntityMapper<Category, CategoryDTO> {

    private final URIMapperStore uriMapperStore;

    public Category fromDTO(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.id())
                .name(categoryDTO.name().orElse(Category.DEFAULT_NAME))
                .items(Collections.emptyList())
                .build();
    }

    public CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(Optional.of(category.getName()))
                .itemURIs(uriMapperStore.itemURIMapper().toURIs(category.getItems()))
                .build();
    }
}
