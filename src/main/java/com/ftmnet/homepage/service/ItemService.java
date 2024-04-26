package com.ftmnet.homepage.service;

import com.ftmnet.homepage.entity.Item;
import com.ftmnet.homepage.entity.User;
import com.ftmnet.homepage.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item getItem(User user, long id) {
        //TODO cleanup checks
        return itemRepository.findById(id)
                .filter(item -> item.getCategory().getUser().getId() == user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item with id %d was not found".formatted(id)));
    }

    @Transactional
    public Stream<Item> getUserItemStream(User user) {
        if (Objects.isNull(user)) {
            return Stream.empty();
        }
        return itemRepository.streamAllByCategoryUserOrCategoryUserNull(user);
    }

    @Transactional
    public List<Item> getUserItems(User user) {
        return getUserItemStream(user).toList();
    }

    public Item addItem(User user, Item item) {
        //TODO add checks
        return itemRepository.save(item);
    }
}
