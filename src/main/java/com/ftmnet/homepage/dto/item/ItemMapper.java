package com.ftmnet.homepage.dto.item;

import com.ftmnet.homepage.configuration.annotation.DTOMapper;
import com.ftmnet.homepage.dto.mapper.DTOEntityMapper;
import com.ftmnet.homepage.dto.mapper.URIMapperStore;
import com.ftmnet.homepage.entity.Item;
import com.ftmnet.homepage.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@DTOMapper
@RequiredArgsConstructor
public class ItemMapper implements DTOEntityMapper<Item, ItemDTO> {

    private final CategoryRepository categoryRepository;
    private final URIMapperStore uriMapperStore;

    public Item fromDTO(ItemEditDTO itemDTO) {
        if (itemDTO.categoryId().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "categoryId not set");
        }
        Item.ItemBuilder builder = Item.builder();
        builder.name(itemDTO.name().orElse(Item.DEFAULT_NAME));
        itemDTO.url()
                .ifPresent(builder::url);
        itemDTO.imageUrl()
                .ifPresent(builder::imageUrl);
        categoryRepository.findById(itemDTO.categoryId().getAsLong())
                .ifPresent(builder::category);
        return builder.build();
    }

    public ItemDTO toDTO(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .name(Optional.of(item.getName()))
                .url(Optional.ofNullable(item.getUrl()))
                .imageUrl(Optional.ofNullable(item.getImageUrl()))
                .categoryURI(Optional.ofNullable(uriMapperStore.categoryURIMapper().toURI(item.getCategory())))
                .build();
    }
}
