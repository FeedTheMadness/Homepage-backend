package com.ftmnet.homepage.dto.mapper;

import com.ftmnet.homepage.entity.Category;
import com.ftmnet.homepage.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class URIMapperStore {

    public static final URIMapper<Category> CATEGORY_URI_MAPPER = () -> "/categories/";

    public static final URIMapper<Item> ITEM_URI_MAPPER = () -> "/items/";

    public URIMapper<Category> categoryURIMapper() {
        return CATEGORY_URI_MAPPER;
    }

    public URIMapper<Item> itemURIMapper() {
        return ITEM_URI_MAPPER;
    }
}
